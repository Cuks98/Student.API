package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.domain.LoginData;
import hr.tvz.curin.studapp.repository.ILoginTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LoginDataService implements ILoginDataService{
    private ILoginTableRepository loginTableRepository;
    @Override
    public List<LoginData> findAll() {
        return loginTableRepository.findAll();
    }

    @Override
    public List<LoginData> findAllByUsername(String username) {
        return loginTableRepository.findAllByUsername(username);
    }

    @Override
    public LoginData findOneByUsernameOrderById(String username) {
        return loginTableRepository.findTopByUsernameOrderByDateOfLoginDesc(username);
    }
}
