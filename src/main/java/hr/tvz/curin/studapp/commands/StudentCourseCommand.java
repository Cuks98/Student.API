package hr.tvz.curin.studapp.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourseCommand {
    public long studentId;
    public long courseId;
}
