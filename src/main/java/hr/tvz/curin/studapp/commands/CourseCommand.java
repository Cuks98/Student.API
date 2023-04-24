package hr.tvz.curin.studapp.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseCommand {
    public int id;
    public String name;
    public int ects;
}
