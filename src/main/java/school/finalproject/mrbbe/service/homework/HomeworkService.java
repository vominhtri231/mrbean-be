package school.finalproject.mrbbe.service.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.homework.Homework;
import school.finalproject.mrbbe.dao.homework.HomeworkStudent;
import school.finalproject.mrbbe.dao.klass.KlassStudent;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.mistake.Mistake;
import school.finalproject.mrbbe.dao.mistake.MistakeRule;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dto.homework.HomeworkDTO;
import school.finalproject.mrbbe.mapper.HomeworkMapper;
import school.finalproject.mrbbe.mapper.MistakeRuleMapper;
import school.finalproject.mrbbe.repository.homework.HomeworkRepository;
import school.finalproject.mrbbe.repository.homework.HomeworkStudentRepository;
import school.finalproject.mrbbe.repository.mistake.MistakeRepository;
import school.finalproject.mrbbe.service.lesson.LessonService;
import school.finalproject.mrbbe.service.mistake.MistakeRuleService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private MistakeRuleService mistakeRuleService;

    @Autowired
    private MistakeRuleMapper mistakeRuleMapper;

    @Autowired
    private HomeworkStudentRepository homeworkStudentRepository;

    @Autowired
    private MistakeRepository mistakeRepository;

    public HomeworkDTO createHomework(HomeworkDTO homeworkDTO) {
        Lesson lesson = lessonService.find(homeworkDTO.getLessonId());
        Homework creatingHomework = homeworkMapper.homeworkDtoToHomework(homeworkDTO);
        creatingHomework.setLesson(lesson);

        Homework createdHomework = homeworkRepository.save(creatingHomework);
        return homeworkMapper.homeworkToHomeworkDto(createdHomework);
    }

    public HomeworkDTO updateHomework(HomeworkDTO homeworkDTO) {
        Homework updatingHomework = find(homeworkDTO.getId());
        updatingHomework.setQuestions(homeworkDTO.getQuestions());
        updatingHomework.setName(homeworkDTO.getName());
        updatingHomework.setDeathLine(homeworkDTO.getDeathLine());

        Homework updatedHomework = homeworkRepository.save(updatingHomework);
        return homeworkMapper.homeworkToHomeworkDto(updatedHomework);
    }

    public void deleteHomework(long id) {
        Homework deletingHomework = find(id);
        homeworkRepository.delete(deletingHomework);
    }

    public HomeworkDTO endHomework(long id) {
        List<MistakeRule> mistakeRules = mistakeRuleService.getAll()
                .stream()
                .map(mistakeRuleMapper::mistakeRuleDTOToMistakeRule)
                .collect(Collectors.toList());
        Homework endingHomework = find(id);
        endedHomework(endingHomework);
        Lesson lesson = endingHomework.getLesson();
        Set<Student> students = lesson.getKlass().getKlassStudents()
                .stream()
                .map(KlassStudent::getStudent)
                .collect(Collectors.toSet());
        for (Student student : students) {
            Optional<HomeworkStudent> optionalHomeworkStudent =
                    homeworkStudentRepository.findFirstByHomeworkAndStudent(endingHomework, student);
            HomeworkResult homeworkResult = optionalHomeworkStudent
                    .map(optional -> new HomeworkResult(endingHomework, optional))
                    .orElseGet(() -> new HomeworkResult(endingHomework.getLesson(), student));
            List<Mistake> mistakes = homeworkResult.getMistakes(mistakeRules);
            for (Mistake mistake : mistakes) {
                mistakeRepository.save(mistake);
            }
        }
        return endedHomework(endingHomework);
    }

    private HomeworkDTO endedHomework(Homework savingHomework) {
        savingHomework.setEnded(true);
        return save(savingHomework);
    }

    private HomeworkDTO save(Homework savingHomework) {
        Homework savedHomework = homeworkRepository.save(savingHomework);
        return homeworkMapper.homeworkToHomeworkDto(savedHomework);
    }

    public Homework find(long homeworkId) {
        return homeworkRepository.findById(homeworkId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Homework is not found!"));
    }
}

