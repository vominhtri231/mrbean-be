package school.finalproject.mrbbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.homework.HomeworkDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    private long id;

    private int lessonNumber;

    private String description;

    private String content;

    private long klassId;

    private List<HomeworkDTO> homeworkList;
}
