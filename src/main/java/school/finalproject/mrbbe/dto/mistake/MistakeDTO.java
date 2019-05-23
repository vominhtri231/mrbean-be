package school.finalproject.mrbbe.dto.mistake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.lesson.LessonSimpleDTO;
import school.finalproject.mrbbe.dto.user.StudentDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MistakeDTO {
    private long id;
    private StudentDTO student;
    private MistakeTypeDTO mistakeType;
    private LessonSimpleDTO lesson;
}
