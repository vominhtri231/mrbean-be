package school.finalproject.mrbbe.dao.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dao.Klass;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends User {
    private String name;
    private String phoneNumber;
    private Date dateOfBirth;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Klass> klasses;
}
