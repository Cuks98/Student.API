package hr.tvz.curin.studapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    @Column(name = "jmbag")
    public String JMBAG;
    @Column(name = "ects_points")
    public int ECTS;

    public String gender;
    public String address;
    public String city;
    public boolean shouldStudentPayFee;

    @ManyToMany(targetEntity = Course.class)
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> courses;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String JMBAG, int ECTS) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.JMBAG = JMBAG;
        this.ECTS = ECTS;
        this.setShouldStudentPayFee(dateOfBirth);
    }

    public Student(long id,String firstName, String lastName, LocalDate dateOfBirth, String JMBAG, int ECTS, String gender, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.JMBAG = JMBAG;
        this.ECTS = ECTS;
        this.setShouldStudentPayFee(dateOfBirth);
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.id = id;
    }
    public Student(String firstName, String lastName, LocalDate dateOfBirth, String JMBAG, int ECTS, String gender, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.JMBAG = JMBAG;
        this.ECTS = ECTS;
        this.setShouldStudentPayFee(dateOfBirth);
        this.gender = gender;
        this.address = address;
        this.city = city;
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
