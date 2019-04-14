package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.Klass;
import school.finalproject.mrbbe.dto.KlassDTO;

@Mapper(uses = TeacherMapper.class)
public interface KlassMapper {
    KlassDTO klassToKlassDTO(Klass klass);

    Klass klassDTOtoKlass(KlassDTO klassDTO);
}
