package carsharing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Model {
    private int id;
    private String name;

    public Company(String name) {
        this.name = name;
    }
}
