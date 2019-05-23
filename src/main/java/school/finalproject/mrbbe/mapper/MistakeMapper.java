package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.mistake.Mistake;
import school.finalproject.mrbbe.dto.mistake.MistakeDTO;

@Mapper(uses = {LessonMapper.class, StudentMapper.class, MistakeTypeMapper.class})
public interface MistakeMapper {

    MistakeDTO mistakeToMistakeDTO(Mistake mistake);

    Mistake mistakeDTOToMistake(MistakeDTO mistakeDTO);
}
