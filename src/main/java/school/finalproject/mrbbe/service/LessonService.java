package school.finalproject.mrbbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.Klass;
import school.finalproject.mrbbe.dao.Lesson;
import school.finalproject.mrbbe.dto.LessonDTO;
import school.finalproject.mrbbe.mapper.LessonMapper;
import school.finalproject.mrbbe.repository.LessonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {
    @Autowired
    KlassService klassService;

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    private LessonRepository lessonRepository;

    public List<LessonDTO> getLessonOfKlass(long klassId) {
        Klass klass = klassService.find(klassId);

        return lessonRepository.findAllByKlass(klass)
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
        return lessonMapper.lessonToLessonDto(savedLesson);
    }

    public LessonDTO update(LessonDTO lessonDTO) {
        Lesson updatingLesson = find(lessonDTO.getId());

        updatingLesson.setDescription(lessonDTO.getDescription());
        updatingLesson.setLessonNumber(lessonDTO.getLessonNumber());
        updatingLesson.setContent(lessonDTO.getContent());

        Lesson savedLesson = lessonRepository.save(updatingLesson);
        return lessonMapper.lessonToLessonDto(savedLesson);
    }

    public void delete(long id) {
        Lesson lesson = find(id);
        lessonRepository.delete(lesson);
    }

    private Lesson find(long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson is not found!"));
    }
}
