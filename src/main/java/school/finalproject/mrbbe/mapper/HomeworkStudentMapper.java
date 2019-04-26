package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.homework.HomeworkStudent;
import school.finalproject.mrbbe.dto.homework.HomeworkStudentDTO;

@Mapper(uses = {HomeworkMapper.class, StudentMapper.class})
public interface HomeworkStudentMapper {
    HomeworkStudentDTO homeworkStudentToHomeworkStudentDto(HomeworkStudent homeworkStudent);

    HomeworkStudent homeworkStudentDtoToHomeworkStudent(HomeworkStudentDTO homeworkStudentDTO);
}
