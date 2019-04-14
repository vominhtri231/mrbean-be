package school.finalproject.mrbbe.dao.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dao.Klass;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
    private boolean isWorker;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "students")
    private Set<Klass> klasses = new HashSet<>();
}
