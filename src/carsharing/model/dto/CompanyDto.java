package carsharing.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private int id;
    private String name;

    public CompanyDto(String name) {
        this.name = name;
    }
}
