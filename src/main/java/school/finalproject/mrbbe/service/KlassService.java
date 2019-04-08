package school.finalproject.mrbbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.Klass;
import school.finalproject.mrbbe.dto.KlassDTO;
import school.finalproject.mrbbe.mapper.KlassMapper;
import school.finalproject.mrbbe.repository.KlassRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KlassService {
    @Autowired
    private KlassRepository klassRepository;

    @Autowired
    private KlassMapper klassMapper;

    public Klass saveKlass(KlassDTO klassDTO) {
        return klassRepository.save(klassMapper.toKlass(klassDTO));
    }

    public List<KlassDTO> getAll() {
        return klassRepository.findAll()
                .stream()
                .map(klass -> klassMapper.toKlassDTO(klass))
                .collect(Collectors.toList());
    }

    public void delete(int id){
        Klass deleteKlass=klassRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Class is not found!"));
        klassRepository.delete(deleteKlass);
    }
}
