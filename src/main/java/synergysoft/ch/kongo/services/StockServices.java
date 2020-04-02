package synergysoft.ch.kongo.services;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

@Service
public class StockServices {

    public HttpResponse<JsonNode> getExampleSiebel(){
//        System.out.println("Im working addAPI()");
        return Unirest.get("http://localhost:8000")
                .header("Accept", "application/json")
                .header("x-custom-header", "hello")
                .header("Host", "example-siebel.com")
                .header("apikey", "ENTER_KEY_HERE")
                .asJson();
    }

}
