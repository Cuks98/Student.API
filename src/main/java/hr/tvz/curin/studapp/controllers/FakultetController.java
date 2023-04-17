package hr.tvz.curin.studapp.controllers;

import hr.tvz.curin.studapp.commands.FakultetCommand;
import hr.tvz.curin.studapp.domain.Fakultet;
import hr.tvz.curin.studapp.dto.FakultetDTO;
import hr.tvz.curin.studapp.service.FakultetService;
import hr.tvz.curin.studapp.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("universitys")
public class FakultetController {

    private FakultetService fakultetService;
    public FakultetController(FakultetService fakultetService){
        this.fakultetService = fakultetService;
    }

    @GetMapping("get-universitys")
    List<FakultetDTO> getAll(){
        return fakultetService.getAll();
    }
    @GetMapping("get-university/{id}")
    ResponseEntity<FakultetDTO> getUniversityById(@PathVariable String id){
        try {
            long paresdId = Integer.parseInt(id);
            Optional<FakultetDTO> fakultetDTO = fakultetService.getUniversityById(paresdId);
            if(fakultetDTO.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(fakultetDTO.get());
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("add-university")
    public ResponseEntity<FakultetDTO> add(@RequestBody FakultetCommand request){
        Optional<FakultetDTO> response = fakultetService.add(request);
        if(response.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(response.get());
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        try {
            long paresdId = Integer.parseInt(id);
            boolean delete = fakultetService.remove(paresdId);
            if(delete){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
