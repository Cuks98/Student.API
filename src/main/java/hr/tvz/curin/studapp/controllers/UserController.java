package hr.tvz.curin.studapp.controllers;


import hr.tvz.curin.studapp.domain.LoginData;
import hr.tvz.curin.studapp.dto.UserDTO;
import hr.tvz.curin.studapp.security.jwt.JwtService;
import hr.tvz.curin.studapp.security.jwt.SecurityUtils;
import hr.tvz.curin.studapp.service.ILoginDataService;
import hr.tvz.curin.studapp.service.IUserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/user")
@Valid
@CrossOrigin(origins = "*")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private IUserService userService;
    private ILoginDataService loginDataService;
    private final JwtService jwtService;
    public UserController(IUserService userService, ILoginDataService loginDataService, JwtService jwtService){
        this.userService = userService;
        this.loginDataService = loginDataService;
        this.jwtService = jwtService;
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("current-user/{username}")
    public ResponseEntity<UserDTO> getById(@PathVariable String username){
        Optional<UserDTO> response = userService.getUserByUsername(username);
        if(response.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(response.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//        return ResponseEntity.ok("Hello");
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return SecurityUtils.getCurrentUserUsername()
                .map(
                        username -> userService.getUserByUsername(username)
                                .map(ResponseEntity::ok)
                                .orElseGet(
                                        () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                                )
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                );
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/get-table")
    public ResponseEntity<List<LoginData>> getLabTable(Authentication authentication){
        String name = authentication.getName();
        Optional<UserDTO> user = userService.getUserByUsername(name);
        if(user.isPresent()){
            String authoirity = user.get().authorities.stream().findFirst().get();
            if(authoirity.equals("ROLE_ADMIN")){
                List<LoginData> response = loginDataService.findAll();
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else if(authoirity.equals("ROLE_USER")){
                LoginData response = loginDataService.findOneByUsernameOrderById(name);
                List<LoginData> responseList = new ArrayList<>();
                responseList.add(response);
                return ResponseEntity.status(HttpStatus.OK).body(responseList);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
