package school.finalproject.mrbbe.dto.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dao.homework.Question;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkDTO {
    private long id;
    private String name;
    private long lessonId;
    private Date deathLine;
    private boolean ended;
    private List<Question> questions;
}
