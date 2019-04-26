package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.homework.HomeworkStudentDTO;
import school.finalproject.mrbbe.service.homework.HomeworkStudentService;

import java.util.List;

@RestController
@RequestMapping("/homeworkStudent")
public class HomeworkStudentController {
    @Autowired
    private HomeworkStudentService homeworkStudentService;

    @PostMapping
    public HomeworkStudentDTO save(@RequestBody HomeworkStudentDTO homeworkStudentDTO) {
        return homeworkStudentService.saveHomeworkStudent(homeworkStudentDTO);
    }

    @GetMapping
    public HomeworkStudentDTO get(@RequestParam long homeworkId, @RequestParam long studentId) {
        return homeworkStudentService.getHomeworkStudent(homeworkId, studentId);
    }

    @GetMapping("/all")
    public List<HomeworkStudentDTO> getAllResultOfHomework(@RequestParam long homeworkId) {
        return homeworkStudentService.getAllResultOfHomework(homeworkId);
    }
}
