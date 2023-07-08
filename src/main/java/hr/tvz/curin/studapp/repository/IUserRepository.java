package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
}
