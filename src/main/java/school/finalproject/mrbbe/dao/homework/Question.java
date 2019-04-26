package school.finalproject.mrbbe.dao.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private String questionText;
    private List<String> answers;
    private Integer correctAnswer;
}
