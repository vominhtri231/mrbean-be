package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.mistake.MistakeRule;
import school.finalproject.mrbbe.dto.mistake.MistakeRuleDTO;

@Mapper(uses = MistakeTypeMapper.class)
public interface MistakeRuleMapper {
    MistakeRuleDTO mistakeRuleToMistakeRuleDTO(MistakeRule mistakeRule);

    MistakeRule mistakeRuleDTOToMistakeRule(MistakeRuleDTO mistakeRuleDTO);
}
