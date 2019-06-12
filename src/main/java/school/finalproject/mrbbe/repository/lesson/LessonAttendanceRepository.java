package school.finalproject.mrbbe.repository.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.lesson.LessonAttendance;

import java.util.List;

public interface LessonAttendanceRepository extends JpaRepository<LessonAttendance, Long> {
    List<LessonAttendance> findAllByLessonOrderByStudentName(Lesson lesson);
}