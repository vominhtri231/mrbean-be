package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.lesson.LessonTemplateDetailDTO;
import school.finalproject.mrbbe.service.lesson.LessonTemplateService;

import java.util.List;

@RestController
@RequestMapping("/lessonTemplate")
public class LessonTemplateController {

    @Autowired
    private LessonTemplateService lessonTemplateService;

    @GetMapping
    public List<LessonTemplateDetailDTO> getAllOfKlassTemplate(@RequestParam long klassTemplateId) {
        return lessonTemplateService.getAllOfKlassTemplate(klassTemplateId);
    }

    @GetMapping("/{id}")
    public LessonTemplateDetailDTO getLessonTemplate(@PathVariable long id) {
        return lessonTemplateService.getLessonTemplate(id);
    }

    @PostMapping
    public LessonTemplateDetailDTO createLessonTemplate(@RequestBody LessonTemplateDetailDTO lessonTemplateDetailDTO) {
        return lessonTemplateService.createLessonTemplate(lessonTemplateDetailDTO);
    }

    @PutMapping
    public LessonTemplateDetailDTO updateLessonTemplate(@RequestBody LessonTemplateDetailDTO lessonTemplateDetailDTO) {
        return lessonTemplateService.updateLessonTemplate(lessonTemplateDetailDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        lessonTemplateService.deleteLessonTemplate(id);
        return "{}";
    }
}
