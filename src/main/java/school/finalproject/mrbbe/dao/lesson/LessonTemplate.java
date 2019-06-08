package school.finalproject.mrbbe.dao.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dao.homework.HomeworkTemplate;
import school.finalproject.mrbbe.dao.klass.KlassTemplate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LessonTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;

    @Column(name = "content",  columnDefinition = "text")
    private String content;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klass_template_id")
    private KlassTemplate klassTemplate;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "lessonTemplate", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<HomeworkTemplate> homeworkTemplateList = new ArrayList<>();
}
