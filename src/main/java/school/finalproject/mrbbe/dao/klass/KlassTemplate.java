package school.finalproject.mrbbe.dao.klass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dao.lesson.LessonTemplate;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class KlassTemplate {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "klassTemplate", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<LessonTemplate> lessonTemplates;
}
