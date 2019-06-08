package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.klass.KlassDTO;
import school.finalproject.mrbbe.dto.user.StudentDTO;
import school.finalproject.mrbbe.service.klass.KlassStudentService;

import java.util.List;

@RestController
@RequestMapping("/klassStudent")
public class KlassStudentController {

    @Autowired
    KlassStudentService klassStudentService;

    @GetMapping("/student")
    public List<StudentDTO> getAllStudentOfKlass(@RequestParam long klassId) {
        return klassStudentService.getAllInKlass(klassId);
    }

    @GetMapping("/klass")
    public List<KlassDTO> getAllKlassOfStudent(@RequestParam long studentId) {
        return klassStudentService.getAllKlassOfStudent(studentId);
    }

    @PutMapping("/registerStudents")
    public List<StudentDTO> registerStudents(@RequestBody KlassDTO klassDTO) {
        return klassStudentService.registerStudents(klassDTO.getId(), klassDTO.getStudentIds());
    }

    @PutMapping("/unRegisterStudents")
    public String unRegisterStudents(@RequestBody KlassDTO klassDTO) {
        klassStudentService.unRegisterStudents(klassDTO.getId(), klassDTO.getStudentIds());
        return "{}";
    }
}
