package school.finalproject.mrbbe.dao.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dao.homework.HomeworkStudent;
import school.finalproject.mrbbe.dao.klass.KlassStudent;
import school.finalproject.mrbbe.dao.lesson.LessonAttendance;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {
    private String name;
    private String phoneNumber;
    private Date dateOfBirth;
    private String workspace;

    @Column(name = "is_worker")
    private boolean isWorker;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    private Set<KlassStudent> klassStudents = new HashSet<>(0);

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    private Set<HomeworkStudent> homeworkStudents = new HashSet<>();

    @OneToMany(mappedBy = "student", orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<LessonAttendance> lessonAttendances = new HashSet<>();
}
