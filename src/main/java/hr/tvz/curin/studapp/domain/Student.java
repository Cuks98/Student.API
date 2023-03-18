package hr.tvz.curin.studapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
public class Student {
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public Long JMBAG;
    public int ECTS;
    public boolean shouldStudentPayFee;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, Long JMBAG, int ECTS) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.JMBAG = JMBAG;
        this.ECTS = ECTS;
        this.setShouldStudentPayFee(dateOfBirth);
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
