package hr.tvz.curin.studapp.controllers;

import hr.tvz.curin.studapp.commands.StudentCommand;
import hr.tvz.curin.studapp.dto.StudentDTO;
import hr.tvz.curin.studapp.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
@Valid
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get-students")
    List<StudentDTO> all() {
        logger.info("Method get-students called with ...");
        List<StudentDTO> response = studentService.findAll();
        logger.info("Method get-students responding with {}", response);
        return response;
    }

    @GetMapping("/get-student/{id}")
    ResponseEntity<StudentDTO> getStudentByJmbag(@PathVariable String id) {
        Optional<StudentDTO> response = studentService.findStudentByJMBAG(id);
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

    @PostMapping("add-new-student")
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody final StudentCommand request) {
        StudentDTO studentDTO = studentService.save(request);
        if (studentDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("delete-student/{jmbag}")
    public ResponseEntity deleteStudent(@PathVariable String jmbag){
        boolean response = studentService.deleteStudent(jmbag);
        if(response == true){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @PostMapping("update-student")
    public ResponseEntity<StudentDTO> UpadateStudent(@Valid @RequestBody final StudentCommand request){
        StudentDTO studentDTO = studentService.updateStudent(request);
        if (studentDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
