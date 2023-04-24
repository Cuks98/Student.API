package hr.tvz.curin.studapp.controllers;

import hr.tvz.curin.studapp.commands.CourseCommand;
import hr.tvz.curin.studapp.domain.Course;
import hr.tvz.curin.studapp.dto.CourseDTO;
import hr.tvz.curin.studapp.service.CourseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/course")
@Valid
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    Logger logger = LoggerFactory.getLogger(CourseController.class);
    private CourseService courseService;
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("get-all")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<CourseDTO> response =courseService.getAll();
        if(response == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable String id){
        long requestId = Long.parseLong(id);
        Optional<CourseDTO> response = courseService.getCourseById(requestId);
        if(response.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(response.get());
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
}
