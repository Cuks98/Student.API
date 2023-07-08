package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.domain.LoginData;

import java.util.List;

public interface ILoginDataService {
    List<LoginData> findAll();
    List<LoginData> findAllByUsername(String username);
    LoginData findOneByUsernameOrderById(String username);
}
