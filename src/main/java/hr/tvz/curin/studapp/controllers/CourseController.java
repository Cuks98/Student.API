package hr.tvz.curin.studapp.controllers;

import hr.tvz.curin.studapp.commands.CourseCommand;
import hr.tvz.curin.studapp.commands.CourseNotInRequest;
import hr.tvz.curin.studapp.commands.StudentCommand;
import hr.tvz.curin.studapp.domain.Course;
import hr.tvz.curin.studapp.domain.Student;
import hr.tvz.curin.studapp.dto.CourseDTO;
import hr.tvz.curin.studapp.dto.StudentDTO;
import hr.tvz.curin.studapp.service.CourseService;
import hr.tvz.curin.studapp.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("course")
@Valid
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    Logger logger = LoggerFactory.getLogger(CourseController.class);
    private CourseService courseService;
    private StudentService studentService;
    public CourseController(CourseService courseService, StudentService studentService){
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping("get-all")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<CourseDTO> response =courseService.getAll();
        if(response == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("")
    public ResponseEntity<List<CourseDTO>> getById(@RequestParam("jmbag") String jmbag){
        List<CourseDTO> responseList = new ArrayList<>();
        Optional<StudentDTO> student = studentService.findStudentByJMBAG(jmbag);
        for(int courseId: student.get().classes){
            responseList.add(courseService.getCourseById(courseId).get());
        }
        if(!responseList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(responseList);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        long requestId = Long.parseLong(id);
        boolean response = courseService.delete(requestId);
        if(response)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PostMapping("add")
    public ResponseEntity<CourseDTO> add(@RequestBody final CourseCommand request){
        Optional<CourseDTO> response = courseService.add(request);
        if(response.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(response.get());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PostMapping("update")
    public ResponseEntity<CourseDTO> update(@RequestBody final CourseCommand request){
        Optional<CourseDTO> response = courseService.update(request);
        if(response.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(response.get());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @GetMapping("get-all-jpa")
    public ResponseEntity<List<CourseDTO>> getAllCoursesJpa(){
        Optional<List<CourseDTO>> response =courseService.getAllWithJpa();
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("get-all-by-student-jmbag/{jmbag}")
    public ResponseEntity<List<CourseDTO>> getAllCoursesByStudentJmabg(@PathVariable String jmbag){
        Optional<List<CourseDTO>> response =courseService.getCourseByStudentJmbag(jmbag);
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("get-first-by-student-jmbag/{jmbag}")
    public ResponseEntity<CourseDTO> getFirstByJmbag(@PathVariable String jmbag){
        Optional<CourseDTO> response =courseService.getFirstByJmbag(jmbag);
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("get-top-containing-student-jmbag/{jmbag}")
    public ResponseEntity<List<CourseDTO>> getTopContaining(@PathVariable String jmbag){
        Optional<List<CourseDTO>> response =courseService.getTop(jmbag);
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("get-starting-with-student-jmbag/{jmbag}")
    public ResponseEntity<List<CourseDTO>> getStartingWith(@PathVariable String jmbag){
        Optional<List<CourseDTO>> response =courseService.getStartsWith(jmbag);
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("get-ending-with-student-jmbag/{jmbag}")
    public ResponseEntity<List<CourseDTO>> getEndingWith(@PathVariable String jmbag){
        Optional<List<CourseDTO>> response =courseService.getEndsWith(jmbag);
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("get-not-in")
    public ResponseEntity<List<CourseDTO>> getDistinctNotIn(@Valid @RequestBody final CourseNotInRequest request) {
        Optional<List<CourseDTO>> response =courseService.getNotIn(request.jmbagList);
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("get-in")
    public ResponseEntity<List<CourseDTO>> getDistinctIn(@Valid @RequestBody final CourseNotInRequest request) {
        Optional<List<CourseDTO>> response =courseService.getIn(request.jmbagList);
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("get-all-ordered-by")
    public ResponseEntity<List<CourseDTO>> getAllOrderedBy(){
        Optional<List<CourseDTO>> response =courseService.getAllOrderdBy();
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("get-ignore/{jmbag}")
    public ResponseEntity<List<CourseDTO>> getAllOrderedBy(@PathVariable String jmbag){
        Optional<List<CourseDTO>> response =courseService.getIgnoreCase(jmbag);
        if(!response.isPresent())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }
}
