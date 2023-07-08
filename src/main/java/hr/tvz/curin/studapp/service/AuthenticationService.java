package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.controllers.StudentController;
import hr.tvz.curin.studapp.domain.User;
import hr.tvz.curin.studapp.repository.IUserRepository;
import hr.tvz.curin.studapp.requests.AuthenticationRequest;
import hr.tvz.curin.studapp.responses.AuthenticationResponse;
import hr.tvz.curin.studapp.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getUsername(),
//                            request.getPassword()
//                    )
//            );

        }catch (Exception ex){
            throw ex;
        }

        User user = userRepository.findOneByUsername(request.getUsername()).orElseThrow();
        logger.info("User =  {user} " + user.toString());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
