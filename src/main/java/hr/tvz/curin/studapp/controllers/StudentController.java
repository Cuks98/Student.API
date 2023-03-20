package hr.tvz.curin.studapp.controllers;

import hr.tvz.curin.studapp.dto.StudentDTO;
import hr.tvz.curin.studapp.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);
    private StudentService studentService;
    public StudentController(StudentService studentService){
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
    Optional<StudentDTO> getStudentByJmbag(@PathVariable String id){
        logger.info("Method get-student called with request: {}", id);
        Optional<StudentDTO> response = studentService.findStudentByJMBAG(id);
        logger.info("Method get-student responded with {}", response);
        return studentService.findStudentByJMBAG(id);
    }

    @GetMapping("get-students-for-lab")
    List<StudentDTO> getStudentsForLab(){
        boolean isPaying = true;
        String jmbagContains = "0032";
        int ects = 6;
        int age = 25;
        return studentService.findStudentForLab(jmbagContains, ects, isPaying, age);
    }
}
