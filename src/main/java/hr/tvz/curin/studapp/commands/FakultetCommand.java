package hr.tvz.curin.studapp.commands;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FakultetCommand {
    private Integer id;
    private String name;
    private String address;
    private String type;
    private Integer postalCode;
}
