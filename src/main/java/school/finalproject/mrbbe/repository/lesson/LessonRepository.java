package school.finalproject.mrbbe.repository.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.finalproject.mrbbe.dao.klass.Klass;
import school.finalproject.mrbbe.dao.lesson.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByKlassOrderByLessonNumber(Klass klass);
}
