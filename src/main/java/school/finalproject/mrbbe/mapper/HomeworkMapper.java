package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.homework.Homework;
import school.finalproject.mrbbe.dto.homework.HomeworkDTO;

@Mapper
public interface HomeworkMapper {
    Homework homeworkDtoToHomework(HomeworkDTO homeworkDto);

    HomeworkDTO homeworkToHomeworkDto(Homework homework);
}
