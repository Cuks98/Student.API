package hr.tvz.curin.studapp.controllers;

import hr.tvz.curin.studapp.requests.AuthenticationRequest;
import hr.tvz.curin.studapp.responses.AuthenticationResponse;
import hr.tvz.curin.studapp.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authenticate")
@Valid
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> logIn(@RequestBody final AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
