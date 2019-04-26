package school.finalproject.mrbbe.dao.homework;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import school.finalproject.mrbbe.dao.user.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class HomeworkStudent implements Serializable {
    @EmbeddedId
    private HomeworkStudentId id;

    @MapsId("homework_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOMEWORK_ID", referencedColumnName = "id")
    private Homework homework;

    @MapsId("student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "id")
    private Student student;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Integer> choices;

    private int result;
}
