package school.finalproject.mrbbe.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.homework.HomeworkTemplateDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonTemplateDetailDTO {
    private long id;

    private long klassTemplateId;

    private String description;

    private String content;

    private List<HomeworkTemplateDTO> homeworkTemplateList;
}
