package school.finalproject.mrbbe.service.mistake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.mistake.MistakeType;
import school.finalproject.mrbbe.dto.mistake.MistakeTypeDTO;
import school.finalproject.mrbbe.mapper.MistakeTypeMapper;
import school.finalproject.mrbbe.repository.mistake.MistakeTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MistakeTypeService {

    @Autowired
    private MistakeTypeRepository mistakeTypeRepository;

    @Autowired
    private MistakeTypeMapper mistakeTypeMapper;

    public MistakeTypeDTO createMistakeType(MistakeTypeDTO mistakeTypeDTO) {
        MistakeType mistakeType = mistakeTypeMapper.mistakeTypeDTOToMistakeType(mistakeTypeDTO);
        MistakeType createdMistakeType = mistakeTypeRepository.save(mistakeType);
        return mistakeTypeMapper.mistakeTypeToMistakeTypeDTO(createdMistakeType);
    }

    public List<MistakeTypeDTO> getAllMistakeType() {
        return mistakeTypeRepository.findAll()
                .stream()
                .map(mistakeTypeMapper::mistakeTypeToMistakeTypeDTO)
                .collect(Collectors.toList());
    }

    public MistakeTypeDTO updateMistakeType(MistakeTypeDTO mistakeTypeDTO) {
        MistakeType updatingMistakeType = find(mistakeTypeDTO.getId());
        updatingMistakeType.setName(mistakeTypeDTO.getName());
        updatingMistakeType.setDescription(mistakeTypeDTO.getDescription());
        MistakeType updatedMistakeType = mistakeTypeRepository.save(updatingMistakeType);
        return mistakeTypeMapper.mistakeTypeToMistakeTypeDTO(updatedMistakeType);
    }

    public void deleteMistakeType(long id) {
        MistakeType mistakeType = find(id);
        mistakeTypeRepository.delete(mistakeType);
    }

    public MistakeType find(Long id) {
        return mistakeTypeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mistake type is not found!")
        );
    }
}
