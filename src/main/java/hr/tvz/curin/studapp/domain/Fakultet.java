package hr.tvz.curin.studapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fakultet {
    private Integer Id;
    private String name;
    private String address;
    private String type;
    private Integer postalCode;
}
