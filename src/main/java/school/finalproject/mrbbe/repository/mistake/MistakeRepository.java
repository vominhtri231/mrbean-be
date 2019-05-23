package school.finalproject.mrbbe.repository.mistake;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.mistake.Mistake;
import school.finalproject.mrbbe.dao.user.Student;

import java.util.List;

public interface MistakeRepository extends JpaRepository<Mistake,Long> {
    List<Mistake> findAllByLesson(Lesson lesson);
    List<Mistake> findAllByStudent(Student student);
}
