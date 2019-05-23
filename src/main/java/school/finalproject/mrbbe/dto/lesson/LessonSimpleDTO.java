package school.finalproject.mrbbe.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonSimpleDTO {
    private long id;

    private int lessonNumber;

    private String description;
}
