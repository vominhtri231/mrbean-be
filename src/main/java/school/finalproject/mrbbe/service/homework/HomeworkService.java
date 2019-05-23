package school.finalproject.mrbbe.service.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.lesson.Lesson;
import school.finalproject.mrbbe.dao.homework.Homework;
import school.finalproject.mrbbe.dao.homework.HomeworkStudent;
import school.finalproject.mrbbe.dao.homework.Question;
import school.finalproject.mrbbe.dto.homework.HomeworkDTO;
import school.finalproject.mrbbe.mapper.HomeworkMapper;
import school.finalproject.mrbbe.repository.homework.HomeworkRepository;
import school.finalproject.mrbbe.service.lesson.LessonService;

import java.util.List;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private LessonService lessonService;

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
        Homework endingHomework = find(id);
        endedHomework(endingHomework);
        //calculateResult(endingHomework)
        return endedHomework(endingHomework);
    }

    private int calculateResult(Homework homework, HomeworkStudent homeworkStudent) {
        List<Question> questionList = homework.getQuestions();
        if (questionList.size() == 0) return 0;
        List<Integer> answers = homeworkStudent.getChoices();
        int rightAnswer = 0;
        for (int i = 0; i < questionList.size(); i++) {
            Question question = convertToQuestion(questionList.get(i));
            Integer answer = answers.get(i);
            if (question.getCorrectAnswer().equals(answer)) {
                rightAnswer++;
            }
        }
        return Math.round(rightAnswer * 100f / questionList.size());
    }

    private Question convertToQuestion(Object json) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(json, Question.class);
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
