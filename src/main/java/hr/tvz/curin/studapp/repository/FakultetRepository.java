package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Fakultet;

import java.util.List;
import java.util.Optional;

public interface FakultetRepository {
    List<Fakultet> getAll();

    Optional<Fakultet> getUniversityById(long id);

    Optional<Fakultet> add(Fakultet request);
    boolean remove(Fakultet request);
}
