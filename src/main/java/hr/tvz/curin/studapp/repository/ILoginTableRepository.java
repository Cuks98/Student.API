package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.LoginData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILoginTableRepository extends JpaRepository<LoginData, Long> {
    List<LoginData> findAll();
    List<LoginData> findAllByUsername(String username);
    LoginData findTopByUsernameOrderByDateOfLoginDesc(String username);

}
