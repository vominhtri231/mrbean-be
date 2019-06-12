package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.lesson.LessonAttendance;
import school.finalproject.mrbbe.dto.lesson.LessonAttendanceDTO;

@Mapper(uses = {StudentMapper.class, LessonMapper.class})
public interface LessonAttendanceMapper {
    LessonAttendanceDTO lessonAttendanceToLessonAttendanceDTO(LessonAttendance lessonAttendance);
}
