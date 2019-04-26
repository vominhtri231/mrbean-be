package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.homework.HomeworkDTO;
import school.finalproject.mrbbe.service.homework.HomeworkService;

@RestController
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;

    @PostMapping
    public HomeworkDTO createHomework(@RequestBody HomeworkDTO homeworkDTO) {
        return homeworkService.createHomework(homeworkDTO);
    }

    @PutMapping
    public HomeworkDTO updateHomework(@RequestBody HomeworkDTO homeworkDTO) {
        return homeworkService.updateHomework(homeworkDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteHomework(@PathVariable long id) {
        homeworkService.deleteHomework(id);
        return "{}";
    }

    @PutMapping("/end/{id}")
    public HomeworkDTO endHomework(@PathVariable long id) {
        return homeworkService.endHomework(id);
    }

}
