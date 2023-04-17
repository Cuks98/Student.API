package hr.tvz.curin.studapp.service;

import hr.tvz.curin.studapp.commands.FakultetCommand;
import hr.tvz.curin.studapp.domain.Fakultet;
import hr.tvz.curin.studapp.dto.FakultetDTO;
import hr.tvz.curin.studapp.repository.FakultetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FakultetServiceImpl implements FakultetService{

    public FakultetRepository fakultetRepository;
    public FakultetServiceImpl(FakultetRepository fakultetRepository){
        this.fakultetRepository = fakultetRepository;
    }


    private FakultetDTO mapFakultetToFakultetDTO(Fakultet request){
        FakultetDTO response = new FakultetDTO(request.getName(), request.getAddress(), request.getPostalCode());
        return  response;
    }

    private Fakultet mapFakultetCommandToFakultet(FakultetCommand request){
        Fakultet response = new Fakultet(request.getId(), request.getName(), request.getAddress(), request.getType(), request.getPostalCode());
        return response;
    }

    @Override
    public List<FakultetDTO> getAll() {
        return fakultetRepository.getAll().stream().map(this::mapFakultetToFakultetDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<FakultetDTO> getUniversityById(long id) {
        return fakultetRepository.getUniversityById(id).stream().map(this::mapFakultetToFakultetDTO).findFirst();
    }

    @Override
    public Optional<FakultetDTO> add(FakultetCommand request) {
        Fakultet fakultet = mapFakultetCommandToFakultet(request);
        Optional<Fakultet> university = fakultetRepository.getUniversityById(request.getId());
        if(!university.isPresent()){
            return fakultetRepository.add(fakultet).stream().map(this::mapFakultetToFakultetDTO).findFirst();
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(long id) {
        Optional<Fakultet> university = fakultetRepository.getUniversityById(id);
        if(university.isPresent()){
            return fakultetRepository.remove(university.get());
        }
        return false;
    }
}
