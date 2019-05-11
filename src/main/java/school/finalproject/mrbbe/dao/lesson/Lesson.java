package school.finalproject.mrbbe.dao.lesson;

import lombok.Data;
import lombok.EqualsAndHashCode;
import school.finalproject.mrbbe.dao.homework.Homework;
import school.finalproject.mrbbe.dao.klass.Klass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int lessonNumber;

    private String description;

    @Column(name = "content", length = 65536)
    private String content;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klass_id")
    private Klass klass;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Homework> homeworkList = new ArrayList<>();
}
