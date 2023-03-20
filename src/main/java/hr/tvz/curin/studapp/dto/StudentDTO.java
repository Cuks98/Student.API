package hr.tvz.curin.studapp.dto;

import hr.tvz.curin.studapp.domain.Student;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
public class StudentDTO {
    public String jmbag;
    public int ects;
    public boolean shouldStudentPayFee;

    public StudentDTO(String firstName, String lastName, LocalDate dateOfBirth, String JMBAG, int ECTS) {
        this.jmbag = JMBAG;
        this.ects = ECTS;
        this.setShouldStudentPayFee(dateOfBirth);
    }

    public StudentDTO(String JMBAG, int ECTS, LocalDate dateOfBirth){
        this.jmbag = JMBAG;
        this.ects = ECTS;
        this.setShouldStudentPayFee(dateOfBirth);
    }

    public StudentDTO(Student student){
        this.jmbag = student.JMBAG;
        this.ects = student.ECTS;
        this.setShouldStudentPayFee(student.dateOfBirth);
    }

    public boolean isShouldStudentPayFee() {
        return shouldStudentPayFee;
    }

    public void setShouldStudentPayFee(LocalDate dateOfBirth) {
        //this.shouldStudentPayFee = shouldStudentPayFee;
        LocalDate curDate = LocalDate.now();

        int age =  Period.between(dateOfBirth, curDate).getYears();
        if(age > 26){
            this.shouldStudentPayFee = true;
        }else{
            this.shouldStudentPayFee = false;
        }
    }
}
