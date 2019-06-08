package school.finalproject.mrbbe.dao.klass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class KlassStudentId implements Serializable {
    @Column(name = "klass_id")
    private long klass;

    @Column(name = "student_id")
    private long student;
}
