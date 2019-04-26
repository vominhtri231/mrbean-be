package school.finalproject.mrbbe.dao.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class HomeworkStudentId implements Serializable {
    @Column(name = "homework_id")
    private long homework;

    @Column(name = "student_id")
    private long student;
}
