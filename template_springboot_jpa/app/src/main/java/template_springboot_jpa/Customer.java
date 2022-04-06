package template_springboot_jpa;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;

    protected Customer() {}

    public Customer(String firstname, String lastName) {
        this.firstName = firstname;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Custoemr[id%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}