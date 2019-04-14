package school.finalproject.mrbbe.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dao.user.User;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findUserByEmailIgnoreCase(String email);
}
