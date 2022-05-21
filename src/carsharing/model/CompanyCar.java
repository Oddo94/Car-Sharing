package carsharing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCar implements Model {
    String companyName;
    String carName;


    @Override
    public String getName() {
        return carName;
    }
}
