package school.finalproject.mrbbe.dto.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dao.homework.Question;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkTemplateDTO {
    private long id;
    private String name;
    private long lessonTemplateId;
    private List<Question> questions;
}
