package school.finalproject.mrbbe.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.user.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
