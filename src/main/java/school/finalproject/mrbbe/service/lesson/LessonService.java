package school.finalproject.mrbbe.service.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.klass.Klass;
import school.finalproject.mrbbe.dao.klass.KlassStudent;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dto.lesson.LessonDTO;
import school.finalproject.mrbbe.mapper.LessonMapper;
import school.finalproject.mrbbe.repository.lesson.LessonRepository;
import school.finalproject.mrbbe.service.klass.KlassService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {
    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private KlassService klassService;

    @Autowired
    private LessonAttendanceService lessonAttendanceService;

    public List<LessonDTO> getLessonOfKlass(long klassId) {
        Klass klass = klassService.find(klassId);

        return lessonRepository.findAllByKlassOrderByLessonNumber(klass)
                .stream()
                .map(lesson -> lessonMapper.lessonToLessonDto(lesson))
                .collect(Collectors.toList());
    }

    public LessonDTO create(LessonDTO lessonDTO) {
        long klassId = lessonDTO.getKlassId();
        Klass klass = klassService.find(klassId);
        Lesson savingLesson = lessonMapper.lessonDtoToLesson(lessonDTO);
        savingLesson.setKlass(klass);

        Lesson savedLesson = lessonRepository.save(savingLesson);

        List<Student> students = klass.getKlassStudents().stream().map(KlassStudent::getStudent).collect(Collectors.toList());
        lessonAttendanceService.createLessonAttendanceForNewLesson(students, savedLesson);

        return lessonMapper.lessonToLessonDto(savedLesson);
    }

    public LessonDTO update(LessonDTO lessonDTO) {
        Lesson updatingLesson = find(lessonDTO.getId());

        updatingLesson.setDescription(lessonDTO.getDescription());
        updatingLesson.setLessonNumber(lessonDTO.getLessonNumber());
        updatingLesson.setContent(lessonDTO.getContent());

        Lesson updatedLesson = lessonRepository.save(updatingLesson);
        return lessonMapper.lessonToLessonDto(updatedLesson);
    }

    public void delete(long id) {
        Lesson lesson = find(id);
        lessonRepository.delete(lesson);
    }

    public Lesson find(long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson is not found!"));
    }
}
