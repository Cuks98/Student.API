package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.domain.User;
import hr.tvz.curin.studapp.dto.UserDTO;

import java.util.Optional;

public interface IUserService {
    public Optional<UserDTO> getUserByUsername(String username);
}
