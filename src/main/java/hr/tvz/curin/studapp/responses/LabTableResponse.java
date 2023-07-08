package hr.tvz.curin.studapp.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabTableResponse {
    public Long id;
    public String username;
    public String userRole;
    public LocalDate dateOfLogin;
    public LocalDate dateOfLogout;

//    id             identity,
//    username       nvarchar(100),
//    user_role      nvarchar(50),
//    date_of_login  date not null,
//    date_of_logout date not null
}
