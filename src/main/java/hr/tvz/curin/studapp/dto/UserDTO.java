package hr.tvz.curin.studapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    public Long id;
    public String userName;
    public String firstName;
    public String lastName;
    public List<String> authorities;
    //    Obavezno napraviti UserDTO za korisnika. On mora imati polja „id“, „username“,
//            „firstName“, „lastName“ i „authorities“. Polje „authorities“ treba biti set String
//    vrijednosti naziva rola koje korisnik ima
}
