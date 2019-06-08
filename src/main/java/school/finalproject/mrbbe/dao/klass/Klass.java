package school.finalproject.mrbbe.dao.klass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.user.Teacher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Klass {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(
            mappedBy = "klass",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    private Set<KlassStudent> klassStudents = new HashSet<>(0);

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "klass", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Lesson> lessons;
}
