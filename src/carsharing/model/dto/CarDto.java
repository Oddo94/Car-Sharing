package carsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private int id;
    private String name;

    public CarDto(String name) {
        this.id = id;
        this.name = name;
    }
}
