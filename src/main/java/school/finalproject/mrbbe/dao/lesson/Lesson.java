package school.finalproject.mrbbe.dao.lesson;

import lombok.Data;
import lombok.EqualsAndHashCode;
import school.finalproject.mrbbe.dao.homework.Homework;
import school.finalproject.mrbbe.dao.klass.Klass;
import school.finalproject.mrbbe.dao.mistake.Mistake;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "lesson",
        uniqueConstraints = @UniqueConstraint(columnNames = {"klass_id", "lessonNumber"}))
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int lessonNumber;

    private Date teachingDate;

    private String description;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klass_id")
    private Klass klass;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Homework> homeworkList = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Mistake> mistakes = new ArrayList<>();

    @OneToMany(mappedBy = "lesson", orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<LessonAttendance> lessonAttendances = new HashSet<>(0);
}
