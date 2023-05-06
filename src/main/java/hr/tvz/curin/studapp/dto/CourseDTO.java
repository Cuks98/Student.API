package hr.tvz.curin.studapp.dto;

import hr.tvz.curin.studapp.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTO {
    public String name;
    public int ects;
}
