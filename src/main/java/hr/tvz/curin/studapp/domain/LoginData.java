package hr.tvz.curin.studapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "_login")
public class LoginData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String username;
    @Column(name = "user_role")
    public String userRole;
    @Column(name = "date_of_login")
    public LocalDateTime dateOfLogin;
    @Column(name = "date_of_logout")
    public LocalDateTime dateOfLogout;

//    id             identity,
//    username       nvarchar(100),
//    user_role      nvarchar(50),
//    date_of_login  date not null,
//    date_of_logout date not null
}
