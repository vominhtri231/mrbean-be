package school.finalproject.mrbbe.repository.klass;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.klass.Klass;
import school.finalproject.mrbbe.dao.klass.KlassStudent;
import school.finalproject.mrbbe.dao.user.Student;

import java.util.List;
import java.util.Optional;

public interface KlassStudentRepository extends JpaRepository<KlassStudent, Long> {
    Optional<KlassStudent> findFirstByKlassAndStudent(Klass klass, Student student);

    List<KlassStudent> findAllByKlass(Klass klass);

    List<KlassStudent> findAllByStudent(Student student);
}
