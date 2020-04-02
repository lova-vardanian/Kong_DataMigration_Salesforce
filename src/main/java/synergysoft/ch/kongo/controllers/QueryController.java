package synergysoft.ch.kongo.controllers;

import kong.unirest.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import synergysoft.ch.kongo.model.Account;
import synergysoft.ch.kongo.services.connector.RestAPIKong;
import synergysoft.ch.kongo.services.StockServices;

@RestController
@RequestMapping("/siebel")
public class QueryController {

    @Autowired
    private StockServices stockServices;

    @Autowired
    private RestAPIKong restAPIKong;

    @Autowired
    private Account account;

    @RequestMapping(path = "/get", produces = "application/json; charset=UTF-8")
    public String getSiebelData(){

        if ( ! stockServices.getExampleSiebel().isSuccess()) {
            return "stockServices.getExampleSiebel().isSuccess() == true";
        }

        JsonNode siebelResponce = stockServices.getExampleSiebel().getBody();

        String firstName = siebelResponce.getArray().getJSONObject(0).getJSONArray("Link").getJSONObject(0).get("name").toString();
        String lastName = siebelResponce.getArray().getJSONObject(0).getJSONArray("Link").getJSONObject(0).get("rel").toString();
        String company = siebelResponce.getArray().getJSONObject(0).getJSONArray("Link").getJSONObject(0).get("href").toString();

        account.setFirstName(firstName).setLastName(lastName).setCompany(company);

        try {
            restAPIKong.connectToSalesforce().createLeads(account).queryLeads().updateLeads(account).deleteLeads().releaseConnection();
        } catch (Exception cpException) {
            cpException.printStackTrace();
            return "Salesforce got an exception, details in java console !";
        }
        
        return siebelResponce.toString();
    }
}
