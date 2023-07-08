package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.domain.User;
import hr.tvz.curin.studapp.dto.UserDTO;
import hr.tvz.curin.studapp.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    private IUserRepository userRepository;
    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        UserDTO response = mapUserToUserDto(userRepository.findOneByUsername(username).get());
        return Optional.of(response);
    }

    private UserDTO mapUserToUserDto(User user){
        String role = user.getAuthorities().stream().findFirst().get().getAuthority();
        List<String> list = new ArrayList<>();
        list.add(role);
        UserDTO response = new UserDTO(
                user.id,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                list
                //user.getAuthorities().stream().findFirst().get().getAuthority()
        );
        return response;
    }
//    Obavezno napraviti UserDTO za korisnika. On mora imati polja „id“, „username“,
//            „firstName“, „lastName“ i „authorities“. Polje „authorities“ treba biti set String
//    vrijednosti naziva rola koje korisnik ima
}
