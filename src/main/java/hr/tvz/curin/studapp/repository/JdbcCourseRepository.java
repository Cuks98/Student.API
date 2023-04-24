package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Course;
import hr.tvz.curin.studapp.domain.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcCourseRepository implements CourseRepository{
    private static final String SELECT_ALL = "SELECT * FROM course";
    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;
    public JdbcCourseRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc).withTableName("course").usingGeneratedKeyColumns("id");
    }
    @Override
    public List<Course> getAll() {
        return List.copyOf(jdbc.query(SELECT_ALL, this::mapRowToCourse));
    }

    @Override
    public Optional<Course> getCourseById(long id) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE id = ?", this::mapRowToCourse, id)
            );
        }catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> add(Course request) {
        request.setId(saveCourse(request));

        return Optional.of(request);
    }

    @Override
    public Optional<Course> update(Course request) {
        int executed = jdbc.update("Update course set " + "name = ?, " + "ects_points = ? " + "where id = ?",
                request.name,
                request.ects,
                request.id);
        if(executed > 0)
            return Optional.of(request);
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        //TODO: delete form student_course table first else SQLException
        int executed = jdbc.update("DELETE FROM course WHERE id = ?", id);
        if(executed > 0)
            return true;
        return false;
    }

    private Course mapRowToCourse(ResultSet rs, int rowNum) throws SQLException{
        return new Course(rs.getLong("id"), rs.getString("name"), rs.getInt("ects_points"));
    }

    private long saveCourse(Course course){
        Map<String, Object> values = new HashMap<>();
        //values
        values.put("name", course.name);
        values.put("ects_points", course.ects);
        return inserter.executeAndReturnKey(values).longValue();
    }
}
