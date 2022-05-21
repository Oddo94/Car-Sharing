package carsharing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Model {
    private int id;
    private String name;
    private int rentedCarId;

    public Customer(String name) {
        this.name = name;
    }

}
