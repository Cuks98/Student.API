package hr.tvz.curin.studapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Student {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return JMBAG.equals(student.JMBAG);
    }

    @Override
    public int hashCode() {
        return Objects.hash(JMBAG);
    }
    public long id;
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public String JMBAG;
    public int ECTS;
    public boolean shouldStudentPayFee;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String JMBAG, int ECTS) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.JMBAG = JMBAG;
        this.ECTS = ECTS;
        this.setShouldStudentPayFee(dateOfBirth);
    }

    public Student(long id, String firstName, String lastName, LocalDate dateOfBirth, String JMBAG, int ECTS) {
        this.id = id;
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
