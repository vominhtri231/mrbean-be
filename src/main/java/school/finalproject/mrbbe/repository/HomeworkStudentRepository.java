package school.finalproject.mrbbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.homework.Homework;
import school.finalproject.mrbbe.dao.homework.HomeworkStudent;
import school.finalproject.mrbbe.dao.user.Student;

import java.util.Optional;

public interface HomeworkStudentRepository extends JpaRepository<HomeworkStudent, Long> {
    Optional<HomeworkStudent> findFirstByHomeworkAndStudent(Homework homework, Student student);
}
