package synergysoft.ch.kongo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Account {

    private String firstName;
    private String lastName;
    private String company;

    public Account setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Account setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Account setCompany(String company) {
        this.company = company;
        return this;
    }
}
