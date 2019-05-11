package school.finalproject.mrbbe.service.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.klass.KlassTemplate;
import school.finalproject.mrbbe.dao.lesson.LessonTemplate;
import school.finalproject.mrbbe.dto.lesson.LessonTemplateDetailDTO;
import school.finalproject.mrbbe.mapper.LessonTemplateMapper;
import school.finalproject.mrbbe.repository.lesson.LessonTemplateRepository;
import school.finalproject.mrbbe.service.klass.KlassTemplateService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonTemplateService {
    @Autowired
    private LessonTemplateRepository lessonTemplateRepository;

    @Autowired
    private KlassTemplateService klassTemplateService;

    @Autowired
    private LessonTemplateMapper lessonTemplateMapper;

    public List<LessonTemplateDetailDTO> getAllOfKlassTemplate(long klassTemplateId) {
        KlassTemplate klassTemplate = klassTemplateService.find(klassTemplateId);
        return lessonTemplateRepository.findAllByKlassTemplate(klassTemplate)
                .stream()
                .map(lessonTemplateMapper::lessonToLessonDetailDto)
                .collect(Collectors.toList());
    }

    public LessonTemplateDetailDTO createLessonTemplate(LessonTemplateDetailDTO lessonTemplateDetailDTO) {
        KlassTemplate klassTemplate = klassTemplateService.find(lessonTemplateDetailDTO.getKlassTemplateId());
        LessonTemplate lessonTemplate = lessonTemplateMapper.lessonDtoToLesson(lessonTemplateDetailDTO);
        lessonTemplate.setKlassTemplate(klassTemplate);
        LessonTemplate createdLessonTemplate = lessonTemplateRepository.save(lessonTemplate);
        return lessonTemplateMapper.lessonToLessonDetailDto(createdLessonTemplate);
    }

    public LessonTemplateDetailDTO updateLessonTemplate(LessonTemplateDetailDTO lessonTemplateDetailDTO) {
        LessonTemplate lessonTemplate = find(lessonTemplateDetailDTO.getId());
        lessonTemplate.setDescription(lessonTemplateDetailDTO.getDescription());
        lessonTemplate.setContent(lessonTemplateDetailDTO.getContent());
        LessonTemplate updatedLessonTemplate = lessonTemplateRepository.save(lessonTemplate);
        return lessonTemplateMapper.lessonToLessonDetailDto(updatedLessonTemplate);
    }

    public LessonTemplateDetailDTO getLessonTemplate(long id){
        LessonTemplate lessonTemplate=find(id);
        return lessonTemplateMapper.lessonToLessonDetailDto(lessonTemplate);
    }

    public void deleteLessonTemplate(long id) {
        LessonTemplate lessonTemplate = find(id);
        lessonTemplateRepository.delete(lessonTemplate);
    }

    public LessonTemplate find(long id) {
        return lessonTemplateRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson template is not found!")
        );
    }
}
