package hr.tvz.curin.studapp.repository;

import hr.tvz.curin.studapp.domain.Fakultet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakultetImplementationRepository implements FakultetRepository {

    private List<Fakultet> listOfUniversitys;
    public FakultetImplementationRepository(){
        listOfUniversitys = new ArrayList<Fakultet>();
        listOfUniversitys.add(new Fakultet(
                0,
                "TVZ",
                "Vrbik 8",
                "Tehnički",
                10000
        ));
        listOfUniversitys.add(new Fakultet(
                1,
                "Filiozofski fakultet",
                "neka Adresa 5",
                "Sve",
                10000
        ));
    }
    @Override
    public List<Fakultet> getAll() {
        return listOfUniversitys;
    }

    @Override
    public Optional<Fakultet> getUniversityById(long id) {
        Optional<Fakultet> fakultet = listOfUniversitys.stream().filter(x->x.getId() == id).findFirst();
        if(!fakultet.isPresent())
            return fakultet;
        return Optional.empty();
    }

    @Override
    public Optional<Fakultet> add(Fakultet request) {
        try{
            listOfUniversitys.add(request);
            return Optional.of(request);
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean remove(Fakultet request) {
        try{
            listOfUniversitys.remove(request);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
