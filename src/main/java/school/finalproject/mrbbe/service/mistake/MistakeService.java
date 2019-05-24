package school.finalproject.mrbbe.service.mistake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.klass.Klass;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.mistake.Mistake;
import school.finalproject.mrbbe.dao.mistake.MistakeType;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dto.mistake.MistakeDTO;
import school.finalproject.mrbbe.mapper.MistakeMapper;
import school.finalproject.mrbbe.repository.mistake.MistakeRepository;
import school.finalproject.mrbbe.service.klass.KlassService;
import school.finalproject.mrbbe.service.lesson.LessonService;
import school.finalproject.mrbbe.service.user.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MistakeService {
    @Autowired
    private MistakeRepository mistakeRepository;

    @Autowired
    private KlassService klassService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MistakeTypeService mistakeTypeService;

    @Autowired
    private MistakeMapper mistakeMapper;

    public List<MistakeDTO> getAllOfKlass(long klassId) {
        Klass klass = klassService.find(klassId);
        List<List<Mistake>> mistakesOfLessons = klass.getLessons().stream()
                .map(lesson -> mistakeRepository.findAllByLesson(lesson))
                .collect(Collectors.toList());
        List<Mistake> mistakes = new ArrayList<>();
        for (List<Mistake> listMistake : mistakesOfLessons) {
            mistakes.addAll(listMistake);
        }
        return mistakes.stream()
                .map(mistakeMapper::mistakeToMistakeDTO)
                .collect(Collectors.toList());
    }

    public List<MistakeDTO> getAllOfKlassAndStudent(long klassId, long studentId) {
        Klass klass = klassService.find(klassId);
        Student student = studentService.find(studentId);
        List<Lesson> lessons = klass.getLessons();
        List<Mistake> mistakesOfStudent = mistakeRepository.findAllByStudent(student);
        return mistakesOfStudent.stream()
                .filter(mistake -> lessons.stream().anyMatch(lesson -> lesson.getId() == mistake.getLesson().getId()))
                .map(mistakeMapper::mistakeToMistakeDTO)
                .collect(Collectors.toList());
    }

    public MistakeDTO create(MistakeDTO mistakeDTO) {
        Lesson lesson = lessonService.find(mistakeDTO.getLesson().getId());
        MistakeType mistakeType = mistakeTypeService.find(mistakeDTO.getMistakeType().getId());
        Student student = studentService.find(mistakeDTO.getStudent().getId());
        Mistake mistake = Mistake.builder().lesson(lesson)
                .mistakeType(mistakeType).student(student).build();
        Mistake createdMistake = mistakeRepository.save(mistake);
        return mistakeMapper.mistakeToMistakeDTO(createdMistake);
    }

    public void delete(long mistakeId) {
        Mistake mistake = find(mistakeId);
        mistakeRepository.delete(mistake);
    }

    public MistakeDTO update(MistakeDTO mistakeDTO) {
        Mistake mistake = find(mistakeDTO.getId());
        Student student = studentService.find(mistakeDTO.getStudent().getId());
        Lesson lesson = lessonService.find(mistakeDTO.getLesson().getId());
        MistakeType mistakeType = mistakeTypeService.find(mistakeDTO.getMistakeType().getId());
        mistake.setLesson(lesson);
        mistake.setMistakeType(mistakeType);
        mistake.setStudent(student);
        Mistake updatedMistake = mistakeRepository.save(mistake);
        return mistakeMapper.mistakeToMistakeDTO(updatedMistake);

    }

    private Mistake find(long mistakeId) {
        return mistakeRepository.findById(mistakeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mistake is not found!"));
    }
}
