package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.homework.HomeworkTemplate;
import school.finalproject.mrbbe.dto.homework.HomeworkTemplateDTO;

@Mapper
public interface HomeworkTemplateMapper {
    HomeworkTemplate homeworkDtoToHomework(HomeworkTemplateDTO homeworkDto);

    HomeworkTemplateDTO homeworkToHomeworkDto(HomeworkTemplate homework);
}
