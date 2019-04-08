package school.finalproject.mrbbe.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.Klass;
import school.finalproject.mrbbe.dto.KlassDTO;

@Mapper
public interface KlassMapper {
    KlassDTO toKlassDTO(Klass klass);

    Klass toKlass(KlassDTO klassDTO);
}
