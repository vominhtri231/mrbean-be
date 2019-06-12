package school.finalproject.mrbbe.service.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.klass.Klass;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.lesson.LessonAttendance;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dto.lesson.LessonAttendanceDTO;
import school.finalproject.mrbbe.mapper.LessonAttendanceMapper;
import school.finalproject.mrbbe.repository.lesson.LessonAttendanceRepository;
import school.finalproject.mrbbe.service.klass.KlassService;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LessonAttendanceService {
    @Autowired
    private LessonAttendanceRepository lessonAttendanceRepository;

    @Autowired
    private LessonAttendanceMapper lessonAttendanceMapper;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private KlassService klassService;

    public void createLessonAttendanceForNewLesson(List<Student> students, Lesson lesson) {
        List<LessonAttendance> lessonAttendances = students.stream()
                .map(student -> LessonAttendance.builder()
                        .lesson(lesson)
                        .isAttend(false)
                        .student(student).build())
                .collect(Collectors.toList());
        lessonAttendanceRepository.saveAll(lessonAttendances);
    }

    public List<LessonAttendanceDTO> getAllOfLesson(long lessonId) {
        Lesson lesson = lessonService.find(lessonId);
        return convertToLessonAttendanceDTOs(lessonAttendanceRepository.findAllByLessonOrderByStudentName(lesson));
    }

    public List<LessonAttendanceDTO> getAllOfKlass(long klassId) {
        Klass klass = klassService.find(klassId);
        Set<LessonAttendance> allKlassLessonAttendances = klass.getLessons().stream()
                .map(Lesson::getLessonAttendances)
                .reduce((Set<LessonAttendance> result, Set<LessonAttendance> attendances) -> {
                    result.addAll(attendances);
                    return result;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Attendance is not found!"));
        return convertToLessonAttendanceDTOs(allKlassLessonAttendances);
    }

    public List<LessonAttendanceDTO> update(List<LessonAttendanceDTO> lessonAttendanceDTOS) {
        List<LessonAttendance> updatingLessonAttendances = lessonAttendanceDTOS.stream()
                .map(lessonAttendanceDTO -> {
                    LessonAttendance lessonAttendance = find(lessonAttendanceDTO.getId());
                    lessonAttendance.setAttend(lessonAttendanceDTO.isAttend());
                    return lessonAttendance;
                })
                .collect(Collectors.toList());
        return convertToLessonAttendanceDTOs(lessonAttendanceRepository.saveAll(updatingLessonAttendances));
    }

    private LessonAttendance find(long id) {
        return lessonAttendanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson is not found!"));
    }

    private List<LessonAttendanceDTO> convertToLessonAttendanceDTOs(Collection<LessonAttendance> lessonAttendances) {
        return lessonAttendances
                .stream()
                .map(lessonAttendance -> lessonAttendanceMapper.lessonAttendanceToLessonAttendanceDTO((lessonAttendance)))
                .collect(Collectors.toList());
    }
}
