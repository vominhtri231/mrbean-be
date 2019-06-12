package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.lesson.LessonAttendanceDTO;
import school.finalproject.mrbbe.service.lesson.LessonAttendanceService;

import java.util.List;

@RestController
@RequestMapping("/lessonAttendance")
public class LessonAttendanceController {
    @Autowired
    private LessonAttendanceService lessonAttendanceService;

    @GetMapping
    public List<LessonAttendanceDTO> getAllOfLesson(@RequestParam long lessonId) {
        return lessonAttendanceService.getAllOfLesson(lessonId);
    }

    @GetMapping(value = "/klass")
    public List<LessonAttendanceDTO> getAllOfKlass(@RequestParam long klassId) {
        return lessonAttendanceService.getAllOfKlass(klassId);
    }

    @PutMapping
    public List<LessonAttendanceDTO> update(@RequestBody List<LessonAttendanceDTO> lessonAttendances) {
        return lessonAttendanceService.update(lessonAttendances);
    }
}
