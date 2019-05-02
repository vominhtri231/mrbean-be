package school.finalproject.mrbbe.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.Klass;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dto.user.StudentDTO;
import school.finalproject.mrbbe.mapper.StudentMapper;
import school.finalproject.mrbbe.repository.user.StudentRepository;
import school.finalproject.mrbbe.service.KlassService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService extends UserGeneralService<Student> {
    @Autowired
    private KlassService klassService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public StudentDTO create(StudentDTO studentDTO) {
        Student savingStudent = studentMapper.studentDtoToStudent(studentDTO);
        try {
            Student preparedStudent = prepareForCreate(savingStudent);
            Student savedStudent = studentRepository.save(preparedStudent);
            return studentMapper.studentToStudentDto(savedStudent);
        } catch (ResponseStatusException e) {
            Student duplicateStudent = studentRepository
                    .findUserByEmailIgnoreCase(studentDTO.getEmail())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email existed in different role"));
            return studentMapper.studentToStudentDto(duplicateStudent);
        }
    }

    public List<StudentDTO> getAllInKlass(long klassId) {
        Klass findingKlass = klassService.find(klassId);

        return studentRepository
                .findAllByKlassesContains(findingKlass)
                .stream()
                .map(student -> studentMapper.studentToStudentDto(student))
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> studentMapper.studentToStudentDto(student))
                .collect(Collectors.toList());
    }

    public StudentDTO update(StudentDTO studentDTO) {
        Student updatingStudent = find(studentDTO.getId());

        updatingStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        updatingStudent.setName(studentDTO.getName());
        updatingStudent.setPhoneNumber(studentDTO.getPhoneNumber());
        updatingStudent.setWorker(studentDTO.isWorker());
        updatingStudent.setEmail(studentDTO.getEmail());
        updatingStudent.setWorkspace(studentDTO.getWorkspace());

        Student updatedStudent = studentRepository.save(updatingStudent);
        return studentMapper.studentToStudentDto(updatedStudent);
    }

    public StudentDTO get(long id) {
        Student foundStudent = find(id);
        return studentMapper.studentToStudentDto(foundStudent);
    }

    public Student find(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student is not found!"));
    }
}
