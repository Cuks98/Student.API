package hr.tvz.curin.studapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourse {
    public long id;
    public long studentId;
    public long courseId;

    public StudentCourse(long studentId, long courseId){
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
