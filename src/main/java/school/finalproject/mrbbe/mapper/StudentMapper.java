package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dto.user.StudentDTO;

@Mapper
public interface StudentMapper {
    Student studentDtoToStudent(StudentDTO studentDTO);

    StudentDTO studentToStudentDto(Student student);
}
