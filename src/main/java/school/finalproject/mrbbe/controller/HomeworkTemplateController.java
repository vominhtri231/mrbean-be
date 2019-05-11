package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.homework.HomeworkTemplateDTO;
import school.finalproject.mrbbe.service.homework.HomeworkTemplateService;

@RestController
@RequestMapping("/homeworkTemplate")
public class HomeworkTemplateController {

    @Autowired
    private HomeworkTemplateService homeworkTemplateService;

    @PostMapping
    public HomeworkTemplateDTO createHomeworkTemplate(@RequestBody HomeworkTemplateDTO homeworkTemplateDTO) {
        return homeworkTemplateService.createHomeworkTemplate(homeworkTemplateDTO);
    }

    @PutMapping
    public HomeworkTemplateDTO updateHomeworkTemplate(@RequestBody HomeworkTemplateDTO homeworkTemplateDTO){
        return homeworkTemplateService.updateHomeworkTemplate(homeworkTemplateDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteHomeworkTemplate(@PathVariable long id) {
        homeworkTemplateService.deleteHomeworkTemplate(id);
        return "{}";
    }
}
