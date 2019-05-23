package school.finalproject.mrbbe.service.mistake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.mistake.MistakeRule;
import school.finalproject.mrbbe.dto.mistake.MistakeRuleDTO;
import school.finalproject.mrbbe.mapper.MistakeRuleMapper;
import school.finalproject.mrbbe.repository.mistake.MistakeRuleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MistakeRuleService {

    @Autowired
    private MistakeRuleRepository mistakeRuleRepository;

    @Autowired
    private MistakeRuleMapper mistakeRuleMapper;

    @Autowired
    private MistakeTypeService mistakeTypeService;

    public List<MistakeRuleDTO> getAll() {
        return mistakeRuleRepository.findAllByOrderByNumber()
                .stream()
                .map(mistakeRuleMapper::mistakeRuleToMistakeRuleDTO)
                .collect(Collectors.toList());
    }

    public MistakeRuleDTO update(MistakeRuleDTO mistakeRuleDTO) {
        MistakeRule oldMistakeRule = find(mistakeRuleDTO.getId());
        oldMistakeRule.setMistakeStandard(mistakeRuleDTO.getMistakeStandard());
        oldMistakeRule.setMistakeType(mistakeTypeService.find(mistakeRuleDTO.getMistakeType().getId()));
        oldMistakeRule.setNumber(mistakeRuleDTO.getNumber());
        oldMistakeRule.setThreshold(mistakeRuleDTO.getThreshold());
        MistakeRule updatedMistakeRule = mistakeRuleRepository.save(oldMistakeRule);
        return mistakeRuleMapper.mistakeRuleToMistakeRuleDTO(updatedMistakeRule);
    }

    public MistakeRuleDTO create(MistakeRuleDTO mistakeRuleDTO) {
        MistakeRule mistakeRule = mistakeRuleMapper.mistakeRuleDTOToMistakeRule(mistakeRuleDTO);
        mistakeRule.setNumber(getNextNumber());
        mistakeRule.setMistakeType(mistakeTypeService.find(mistakeRuleDTO.getMistakeType().getId()));
        MistakeRule createdMistakeRule = mistakeRuleRepository.save(mistakeRule);
        return mistakeRuleMapper.mistakeRuleToMistakeRuleDTO(createdMistakeRule);
    }

    private int getNextNumber() {
        List<MistakeRuleDTO> mistakeRuleDTOS = getAll();
        if (mistakeRuleDTOS.size() == 0) return 1;
        MistakeRuleDTO lastNumberRule = mistakeRuleDTOS.get(mistakeRuleDTOS.size() - 1);
        return lastNumberRule.getNumber() + 1;
    }

    public void delete(long id) {
        MistakeRule mistakeRule = find(id);
        mistakeRuleRepository.delete(mistakeRule);
    }

    private MistakeRule find(long id) {
        return mistakeRuleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mistake rule is not found!"));
    }
}
