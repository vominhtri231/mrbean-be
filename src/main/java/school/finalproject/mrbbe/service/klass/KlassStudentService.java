package school.finalproject.mrbbe.service.klass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.klass.Klass;
import school.finalproject.mrbbe.dao.klass.KlassStudent;
import school.finalproject.mrbbe.dao.klass.KlassStudentId;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dto.klass.KlassDTO;
import school.finalproject.mrbbe.dto.user.StudentDTO;
import school.finalproject.mrbbe.mapper.KlassMapper;
import school.finalproject.mrbbe.mapper.StudentMapper;
import school.finalproject.mrbbe.repository.klass.KlassStudentRepository;
import school.finalproject.mrbbe.service.user.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KlassStudentService {

    @Autowired
    private KlassStudentRepository klassStudentRepository;

    @Autowired
    private KlassService klassService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private StudentMapper studentMapper;

    public List<StudentDTO> getAllInKlass(long klassId) {
        Klass klass = klassService.find(klassId);
        return convertToListStudent(klassStudentRepository.findAllByKlassOrderByStudentName(klass));
    }

    public List<KlassDTO> getAllKlassOfStudent(long studentId) {
        Student student = studentService.find(studentId);

        return klassStudentRepository.findAllByStudent(student).stream()
                .map(KlassStudent::getKlass)
                .map(klass -> klassMapper.klassToKlassDTO(klass))
                .collect(Collectors.toList());
    }

    public List<StudentDTO> registerStudents(long klassId, long[] studentIds) {
        Klass klass = klassService.find(klassId);
        List<KlassStudent> klassStudents = Arrays.stream(studentIds)
                .mapToObj(id -> studentService.find(id))
                .map(student -> KlassStudent.builder()
                        .id(new KlassStudentId(klass.getId(), student.getId()))
                        .klass(klass).student(student).build())
                .collect(Collectors.toList());
        return convertToListStudent(klassStudentRepository.saveAll(klassStudents));
    }

    public void unRegisterStudents(long klassId, long[] studentIds) {
        Klass klass = klassService.find(klassId);
        List<KlassStudent> klassStudents = Arrays.stream(studentIds)
                .mapToObj(id -> studentService.find(id))
                .map(student -> find(klass, studentService.find(student.getId())))
                .collect(Collectors.toList());
        klassStudentRepository.deleteAll(klassStudents);
    }

    public KlassStudent find(Klass klass, Student student) {
        return klassStudentRepository.findFirstByKlassAndStudent(klass, student)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Class is not found!"));
    }

    private List<StudentDTO> convertToListStudent(List<KlassStudent> klassStudents) {
        return klassStudents.stream()
                .map(KlassStudent::getStudent)
                .map(student -> studentMapper.studentToStudentDto(student))
                .collect(Collectors.toList());
    }
}
