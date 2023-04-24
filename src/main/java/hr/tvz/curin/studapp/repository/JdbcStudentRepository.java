package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcStudentRepository implements StudentRepository{
    private static final String SELECT_ALL = "SELECT * FROM student";
    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;
    public JdbcStudentRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc).withTableName("student").usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Student> findAll() {
        return List.copyOf(jdbc.query(SELECT_ALL, this::mapRowToStudent));
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String JMBAG) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE jmbag = ?", this::mapRowToStudent, JMBAG)
            );
        }catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findStudentsForLab(String jmbag, int ects, boolean isPaying, int age) {
        return null;
    }

    @Override
    public Student save(Student student) {
        try{
            student.setId(saveStudent(student));
            return student;
        }catch(DuplicateKeyException exception){
            return null;
        }
    }

    @Override
    public boolean deleteStudent(Student student) {
        int executed = jdbc.update("DELETE FROM student WHERE jmbag = ?", student.JMBAG);
        if(executed > 0)
            return true;
        return false;
    }

    @Override
    public Student updateStudent(Student student) throws SQLException{
        int executed = jdbc.update("UPDATE student set " +
                "first_name = ?, " +
                "last_name = ?, " +
                "ects_points = ?, " +
                "date_of_birth = ? ," +
                        "gender = ? ," +
                        "address = ? ," +
                        "city = ? " +
                "WHERE jmbag = ?",
                student.firstName,
                student.lastName,
                student.ECTS,
                student.dateOfBirth,
                student.gender,
                student.address,
                student.city,
                student.JMBAG
                );
        if(executed > 0){
            return student;
        }
        return null;
    }

    public Optional<List<Student>> getStudentsByGender(String request){
        try{
            return Optional.ofNullable(
                    List.copyOf(jdbc.query(SELECT_ALL + " WHERE gender = ?", this::mapRowToStudent, request))
            );
        }catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    public Optional<List<Student>> getStudentsByCity(String request){
        try{
            return Optional.ofNullable(
                    List.copyOf(jdbc.query(SELECT_ALL + " WHERE city = ?", this::mapRowToStudent, request))
            );
        }catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    private Student mapRowToStudent(ResultSet rs, int rowNum) throws SQLException{
        return new Student(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getDate("date_of_birth").toLocalDate(),
                rs.getString("jmbag"),
                rs.getInt("ects_points"),
                rs.getString("gender"),
                rs.getString("address"),
                rs.getString("city")
        );
    }

    private long saveStudent(Student student){
        Map<String, Object> values = new HashMap<>();
        //values
        values.put("first_name", student.firstName);
        values.put("last_name", student.lastName);
        values.put("jmbag", student.JMBAG);
        values.put("date_of_birth", student.dateOfBirth);
        values.put("ects_points", student.ECTS);
        values.put("gender", student.gender);
        values.put("address", student.address);
        values.put("city", student.city);
        return inserter.executeAndReturnKey(values).longValue();
    }
}
