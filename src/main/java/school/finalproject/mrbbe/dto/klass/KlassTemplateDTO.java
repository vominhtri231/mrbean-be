package school.finalproject.mrbbe.dto.klass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.lesson.LessonTemplateDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KlassTemplateDTO {
    private long id;
    private String name;
    private List<LessonTemplateDTO> lessonTemplates;
}
