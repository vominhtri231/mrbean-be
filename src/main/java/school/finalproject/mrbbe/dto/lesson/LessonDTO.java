package school.finalproject.mrbbe.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.homework.HomeworkDTO;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonDTO {
    private long id;

    private int lessonNumber;

    private Date teachingDate;

    private String description;

    private String content;

    private long klassId;

    private List<HomeworkDTO> homeworkList;
}
