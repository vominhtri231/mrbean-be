package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.user.Teacher;
import school.finalproject.mrbbe.dto.user.TeacherDTO;

@Mapper
public interface TeacherMapper {
    Teacher teacherDtoToTeacher(TeacherDTO teacherDTO);

    TeacherDTO teacherToTeacherDto(Teacher teacher);
}
