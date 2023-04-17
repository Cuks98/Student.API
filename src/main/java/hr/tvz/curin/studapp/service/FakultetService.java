package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.commands.FakultetCommand;
import hr.tvz.curin.studapp.domain.Fakultet;
import hr.tvz.curin.studapp.dto.FakultetDTO;

import java.util.List;
import java.util.Optional;

public interface FakultetService {
    List<FakultetDTO> getAll();

    Optional<FakultetDTO> getUniversityById(long id);

    Optional<FakultetDTO> add(FakultetCommand request);
    boolean remove(long id);
}
