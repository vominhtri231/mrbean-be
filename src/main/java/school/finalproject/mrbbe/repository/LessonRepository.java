package school.finalproject.mrbbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.finalproject.mrbbe.dao.Klass;
import school.finalproject.mrbbe.dao.Lesson;
import school.finalproject.mrbbe.dao.user.Teacher;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByKlass(Klass klass);
}
