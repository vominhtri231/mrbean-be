package school.finalproject.mrbbe.repository.klass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.finalproject.mrbbe.dao.klass.Klass;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dao.user.Teacher;

import java.util.List;

@Repository
public interface KlassRepository extends JpaRepository<Klass, Long> {
    List<Klass> findAllByTeacher(Teacher teacher);
}
