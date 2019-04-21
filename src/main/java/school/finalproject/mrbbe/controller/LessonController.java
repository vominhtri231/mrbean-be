package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.LessonDTO;
import school.finalproject.mrbbe.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<LessonDTO> getLessonOfKlass(@RequestParam long klassId) {
        return lessonService.getLessonOfKlass(klassId);
    }

    @PostMapping
    public LessonDTO create(@RequestBody LessonDTO lessonDTO) {
        return lessonService.create(lessonDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        lessonService.delete(id);
        return "{}";
    }

    @PutMapping
    public LessonDTO update(@RequestBody LessonDTO lessonDTO) {
        return lessonService.update(lessonDTO);
    }
}
