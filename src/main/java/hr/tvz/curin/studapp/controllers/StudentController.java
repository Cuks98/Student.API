package hr.tvz.curin.studapp.controllers;

import hr.tvz.curin.studapp.commands.StudentCommand;
import hr.tvz.curin.studapp.dto.StudentDTO;
import hr.tvz.curin.studapp.dto.StudentSecondDTO;
import hr.tvz.curin.studapp.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
@Valid
@CrossOrigin(origins = "*")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("")
    List<StudentDTO> all() {
        logger.info("Method get-students called with ...");
        List<StudentDTO> response = studentService.findAll();
        logger.info("Method get-students responding with {}", response);
        return response;
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{jmbag}")
    ResponseEntity<StudentDTO> getStudentByJmbag(@PathVariable String jmbag) {
        Optional<StudentDTO> response = studentService.findStudentByJMBAG(jmbag);
        if(response.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(response.get());
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("get-students-for-lab")
    List<StudentDTO> getStudentsForLab() {
        boolean isPaying = true;
        String jmbagContains = "0032";
        int ects = 6;
        int age = 25;
        return studentService.findStudentForLab(jmbagContains, ects, isPaying, age);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody final StudentCommand request) {
        StudentDTO studentDTO = studentService.save(request);
        if (studentDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{jmbag}")
    public ResponseEntity deleteStudent(@PathVariable String jmbag){
        boolean response = studentService.deleteStudent(jmbag);
        if(response == true){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{jmbag}")
    public ResponseEntity<StudentDTO> UpdateStudent(@Valid @RequestBody final StudentCommand request){
        StudentDTO studentDTO = studentService.updateStudent(request);
        if (studentDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("get-by-gender/{gender}")
    public ResponseEntity<List<StudentSecondDTO>> getByGender(@PathVariable String gender){
        Optional<List<StudentSecondDTO>> response = studentService.getStudentsByGender(gender);
        if(response.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(response.get());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @GetMapping("get-by-city/{city}")
    public ResponseEntity<List<StudentSecondDTO>> getByCity(@PathVariable String city){
        Optional<List<StudentSecondDTO>> response = studentService.getStudentsByCity(city);
        if(response.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(response.get());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{jmbag}/{lang}")
    ResponseEntity<StudentDTO> getStudentAboutMeLab(@PathVariable String jmbag, @PathVariable String lang) {
        Optional<StudentDTO> response = studentService.findStudentByJMBAG(jmbag);
        if(response.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(response.get());
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
