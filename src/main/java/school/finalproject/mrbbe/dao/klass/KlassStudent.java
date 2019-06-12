package school.finalproject.mrbbe.dao.klass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dao.user.Student;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KlassStudent {
    @EmbeddedId
    private KlassStudentId id;

    @MapsId("klass_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klass_id", referencedColumnName = "id")
    private Klass klass;

    @MapsId("student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    private Date registerDate;
}
