package school.finalproject.mrbbe.repository.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.klass.KlassTemplate;
import school.finalproject.mrbbe.dao.lesson.LessonTemplate;

import java.util.List;

public interface LessonTemplateRepository extends JpaRepository<LessonTemplate, Long> {
    List<LessonTemplate> findAllByKlassTemplate(KlassTemplate klassTemplate);
}
