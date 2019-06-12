package school.finalproject.mrbbe.dao.lesson;

import lombok.*;
import school.finalproject.mrbbe.dao.user.Student;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private boolean isAttend;
}
