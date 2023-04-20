package hr.tvz.curin.studapp.dto;

import hr.tvz.curin.studapp.domain.Student;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class StudentDTO {
    public String jmbag;
    public int ects;
    public boolean shouldStudentPayFee;
    public List<Integer> classes;

    public StudentDTO(String jmbag, int ects, LocalDate dateOfBirth, List<Integer> classes) {
        this.jmbag = jmbag;
        this.ects = ects;
        this.setShouldStudentPayFee(dateOfBirth);
        this.classes = classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return ects == that.ects && shouldStudentPayFee == that.shouldStudentPayFee && jmbag.equals(that.jmbag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbag, ects, shouldStudentPayFee);
    }

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
