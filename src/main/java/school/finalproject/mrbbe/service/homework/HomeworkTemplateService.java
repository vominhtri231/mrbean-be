package school.finalproject.mrbbe.service.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.homework.HomeworkTemplate;
import school.finalproject.mrbbe.dao.lesson.LessonTemplate;
import school.finalproject.mrbbe.dto.homework.HomeworkTemplateDTO;
import school.finalproject.mrbbe.mapper.HomeworkTemplateMapper;
import school.finalproject.mrbbe.repository.homework.HomeworkTemplateRepository;
import school.finalproject.mrbbe.service.lesson.LessonTemplateService;

@Service
public class HomeworkTemplateService {

    @Autowired
    private HomeworkTemplateRepository homeworkTemplateRepository;

    @Autowired
    private HomeworkTemplateMapper homeworkTemplateMapper;

    @Autowired
    private LessonTemplateService lessonTemplateService;

    public HomeworkTemplateDTO createHomeworkTemplate(HomeworkTemplateDTO homeworkTemplateDTO) {
        HomeworkTemplate homeworkTemplate = homeworkTemplateMapper.homeworkDtoToHomework(homeworkTemplateDTO);
        LessonTemplate lessonTemplate = lessonTemplateService.find(homeworkTemplateDTO.getLessonTemplateId());
        homeworkTemplate.setLessonTemplate(lessonTemplate);
        HomeworkTemplate createdHomeworkTemplate = homeworkTemplateRepository.save(homeworkTemplate);
        return homeworkTemplateMapper.homeworkToHomeworkDto(createdHomeworkTemplate);
    }

    public HomeworkTemplateDTO updateHomeworkTemplate(HomeworkTemplateDTO homeworkTemplateDTO) {
        HomeworkTemplate updatingHomeworkTemplate = find(homeworkTemplateDTO.getId());
        updatingHomeworkTemplate.setQuestions(homeworkTemplateDTO.getQuestions());
        updatingHomeworkTemplate.setName(homeworkTemplateDTO.getName());
        HomeworkTemplate updatedHomeworkTemplate = homeworkTemplateRepository.save(updatingHomeworkTemplate);
        return homeworkTemplateMapper.homeworkToHomeworkDto(updatedHomeworkTemplate);
    }

    public void deleteHomeworkTemplate(long id) {
        HomeworkTemplate deletingHomeworkTemplate = find(id);
        homeworkTemplateRepository.delete(deletingHomeworkTemplate);
    }

    private HomeworkTemplate find(long id) {
        return homeworkTemplateRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Homework template is not found!")
        );
    }
}
