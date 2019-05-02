package school.finalproject.mrbbe.dto.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.user.StudentDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkStudentDTO {
    private HomeworkDTO homework;
    private StudentDTO student;
    private long homeworkId;
    private long studentId;
    private List<Integer> choices;
}
