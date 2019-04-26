package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.Lesson;
import school.finalproject.mrbbe.dto.LessonDTO;

@Mapper(uses = HomeworkMapper.class)
public interface LessonMapper {
    Lesson lessonDtoToLesson(LessonDTO lessonDTO);

    LessonDTO lessonToLessonDto(Lesson lesson);
}
