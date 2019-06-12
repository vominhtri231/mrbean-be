package school.finalproject.mrbbe.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.user.StudentDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonAttendanceDTO {
    private long id;

    private StudentDTO student;

    private boolean isAttend;

    private LessonDTO lesson;
}
