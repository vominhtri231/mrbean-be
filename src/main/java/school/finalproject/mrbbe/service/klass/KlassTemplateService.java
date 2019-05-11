package school.finalproject.mrbbe.service.klass;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.klass.KlassTemplate;
import school.finalproject.mrbbe.dto.klass.KlassTemplateDTO;
import school.finalproject.mrbbe.mapper.KlassTemplateMapper;
import school.finalproject.mrbbe.repository.klass.KlassTemplateRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KlassTemplateService {

    @Autowired
    private KlassTemplateRepository klassTemplateRepository;

    @Autowired
    private KlassTemplateMapper klassTemplateMapper;

    public KlassTemplateDTO createKlassTemplate(KlassTemplateDTO klassTemplateDTO) {
        KlassTemplate klassTemplate = klassTemplateMapper.klassDTOtoKlass(klassTemplateDTO);
        KlassTemplate createdKlassTemplate = klassTemplateRepository.save(klassTemplate);
        return klassTemplateMapper.klassToKlassDTO(createdKlassTemplate);
    }

    public List<KlassTemplateDTO> getAll() {
        return klassTemplateRepository.findAll()
                .stream()
                .map(klassTemplateMapper::klassToKlassDTO)
                .collect(Collectors.toList());
    }

    public KlassTemplateDTO updateKlassTemplate(KlassTemplateDTO klassTemplateDTO) {
        KlassTemplate updatingKlassTemplate = find(klassTemplateDTO.getId());
        updatingKlassTemplate.setName(klassTemplateDTO.getName());
        KlassTemplate updatedKlassTemplate = klassTemplateRepository.save(updatingKlassTemplate);
        return klassTemplateMapper.klassToKlassDTO(updatedKlassTemplate);
    }

    public void deleteKlassTemplate(long id) {
        KlassTemplate deletingKlassTemplate = find(id);
        klassTemplateRepository.delete(deletingKlassTemplate);
    }

    public KlassTemplate find(long id) {
        return klassTemplateRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Class template is not found!")
        );
    }
}
