package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.commands.CourseCommand;
import hr.tvz.curin.studapp.domain.Course;
import hr.tvz.curin.studapp.domain.Student;
import hr.tvz.curin.studapp.dto.CourseDTO;
import hr.tvz.curin.studapp.repository.CourseRepository;
import hr.tvz.curin.studapp.repository.ICourseRepository;
import hr.tvz.curin.studapp.repository.JdbcStudentRepository;
import hr.tvz.curin.studapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService{

    public CourseRepository courseRepository;
    public ICourseRepository jpaCourseRepository;
    public StudentRepository studentRepository;
    public CourseService(
            CourseRepository courseRepository,
            ICourseRepository jpaCourseRepository,
            StudentRepository studentRepository)
    {
        this.courseRepository = courseRepository;
        this.jpaCourseRepository = jpaCourseRepository;
        this.studentRepository = studentRepository;
    }
    @Override
    public List<CourseDTO> getAll() {
        try{
            return courseRepository.getAll().stream().map(this::mapCourseToCourseDTO).collect(Collectors.toList());
        }catch (Exception ex){
            return null;
        }
    }


    @Override
    public Optional<CourseDTO> getCourseById(long id) {
        Optional<Course> response = courseRepository.getCourseById(id);
        if(response.isPresent())
            return response.stream().map(this::mapCourseToCourseDTO).findFirst();
        return Optional.empty();
    }

    @Override
    public Optional<CourseDTO> add(CourseCommand request) {
        Optional<Course> response = courseRepository.add(mapCourseCommandToCourse(request));
        return response.map(this::mapCourseToCourseDTO);
    }

    @Override
    public Optional<CourseDTO> update(CourseCommand request) {
        return courseRepository.update(mapCourseCommandToCourse(request)).map(this::mapCourseToCourseDTO);
    }

    @Override
    public boolean delete(long id) {
        if(courseRepository.delete(id))
            return true;
        return false;
    }

    @Override
    public Optional<List<CourseDTO>> getCourseByStudentJmbag(String jmbag) {
        List<CourseDTO> response = jpaCourseRepository.findAllByStudents_JMBAG(jmbag)
                .stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return Optional.of(response);
    }

    @Override
    public Optional<List<CourseDTO>> getAllWithJpa() {
        List<CourseDTO> response = jpaCourseRepository.findAll()
                .stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return Optional.of(response);
    }

    @Override
    public Optional<CourseDTO> getFirstByJmbag(String jmbag) {
        Course response = jpaCourseRepository.findFirstByStudents_JMBAG(jmbag);
        CourseDTO mapped = mapCourseToCourseDTO(response);
        return Optional.of(mapped);
    }

    @Override
    public Optional<List<CourseDTO>> getTop(String jmbag) {
        List<CourseDTO> response = jpaCourseRepository.findTop3ByStudents_JMBAGContaining(jmbag)
                .stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return Optional.of(response);
    }

    @Override
    public Optional<List<CourseDTO>> getStartsWith(String jmbag) {
        List<CourseDTO> response = jpaCourseRepository.findByStudents_JMBAGStartingWith(jmbag)
                .stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return Optional.of(response);
    }

    @Override
    public Optional<List<CourseDTO>> getEndsWith(String jmbag) {
        List<CourseDTO> response = jpaCourseRepository.findByStudents_JMBAGEndingWith(jmbag)
                .stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return Optional.of(response);
    }

    @Override
    public Optional<List<CourseDTO>> getNotIn(List<String> jmbags) {
        List<CourseDTO> response = jpaCourseRepository.findDistinctByStudents_JMBAGNotIn(jmbags)
                .stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return Optional.of(response);
    }

    @Override
    public Optional<List<CourseDTO>> getIn(List<String> jmbags) {
        List<CourseDTO> response = jpaCourseRepository.findDistinctByStudents_JMBAGIn(jmbags)
                .stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return Optional.of(response);
    }

    @Override
    public Optional<List<CourseDTO>> getAllOrderdBy() {
        List<CourseDTO> response = jpaCourseRepository.findAllByOrderByEcts()
                .stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return Optional.of(response);
    }

    @Override
    public Optional<List<CourseDTO>> getIgnoreCase(String jmbag) {
        List<CourseDTO> response = jpaCourseRepository.findByStudents_JMBAGIgnoreCase(jmbag)
                .stream()
                .map(this::mapCourseToCourseDTO)
                .collect(Collectors.toList());
        return Optional.of(response);
    }

    private CourseDTO mapCourseToCourseDTO(Course request){
        return new CourseDTO(request.name, request.ects);
    }

    private  Course mapCourseCommandToCourse(CourseCommand request){
        return new Course(request.id, request.name, request.ects);
    }
}
