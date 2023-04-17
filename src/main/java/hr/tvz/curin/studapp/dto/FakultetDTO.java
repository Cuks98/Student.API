package hr.tvz.curin.studapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FakultetDTO {
    private String name;
    private String address;
    private Integer postalCode;
}
