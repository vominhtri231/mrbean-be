package school.finalproject.mrbbe.dao.mistake;

import lombok.*;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.user.Student;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mistake {
    @Id
    @GeneratedValue
    private long id;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mistake_type_id")
    private MistakeType mistakeType;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
