package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.lesson.LessonTemplate;
import school.finalproject.mrbbe.dto.lesson.LessonTemplateDTO;
import school.finalproject.mrbbe.dto.lesson.LessonTemplateDetailDTO;

@Mapper(uses = {HomeworkTemplateMapper.class})
public interface LessonTemplateMapper {
    LessonTemplate lessonDtoToLesson(LessonTemplateDetailDTO lessonDTO);

    LessonTemplateDTO lessonToLessonDto(LessonTemplate lesson);

    LessonTemplateDetailDTO lessonToLessonDetailDto(LessonTemplate lesson);
}
