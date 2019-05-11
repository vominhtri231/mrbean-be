package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.klass.KlassTemplate;
import school.finalproject.mrbbe.dto.klass.KlassTemplateDTO;
import school.finalproject.mrbbe.dto.lesson.LessonTemplateDTO;

@Mapper(uses = {LessonTemplateDTO.class})
public interface KlassTemplateMapper {
    KlassTemplateDTO klassToKlassDTO(KlassTemplate klass);

    KlassTemplate klassDTOtoKlass(KlassTemplateDTO klassDTO);
}
