package school.finalproject.mrbbe.dao.homework;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import school.finalproject.mrbbe.dao.Lesson;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private Date deathLine;

    private boolean ended;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Question> questions;

    @OneToMany(
            mappedBy = "homework",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @EqualsAndHashCode.Exclude
    private Set<HomeworkStudent> homeworkStudents = new HashSet<>();
}
