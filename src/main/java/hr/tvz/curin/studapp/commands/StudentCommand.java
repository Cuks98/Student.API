package hr.tvz.curin.studapp.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.remote.JMXServerErrorException;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StudentCommand {
    @NotBlank(message = "First name is required")
    public String firstName;
    @NotBlank(message = "Last name is required")
    public String lastName;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy.")
    @NotNull(message = "DateOfBirth is required")
    @Past(message = "dateOfBirth should be in past")
    public LocalDate dateOfBirth;
    @NotBlank(message = "JMBAG is requiered")
    @Pattern(message = "JMBAG has to be 10 digits", regexp = "[\\d]{10}")
    public String jmbag;
    @NotNull(message = "Number of ECTS has to be entered")
    @PositiveOrZero(message = "Number of ECTS needs to be zero or greater")
    @Max(message = "Number of ECTS can not be greater then 480", value = 480)
    public int ects;
    public boolean shouldStudentPayFee;
    public String gender;
    public String address;
    public String city;
}
