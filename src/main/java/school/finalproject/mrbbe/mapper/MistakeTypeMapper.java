package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.mistake.MistakeType;
import school.finalproject.mrbbe.dto.mistake.MistakeTypeDTO;

@Mapper
public interface MistakeTypeMapper {
    MistakeTypeDTO mistakeTypeToMistakeTypeDTO(MistakeType mistake);

    MistakeType mistakeTypeDTOToMistakeType(MistakeTypeDTO mistakeDTO);
}
