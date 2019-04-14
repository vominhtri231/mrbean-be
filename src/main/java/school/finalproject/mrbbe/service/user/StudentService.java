package school.finalproject.mrbbe.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dto.user.StudentDTO;
import school.finalproject.mrbbe.mapper.StudentMapper;
import school.finalproject.mrbbe.repository.user.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService extends UserGeneralService<Student> {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMapper studentMapper;

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

    public List<StudentDTO> getAllStudent() {
        return studentRepository.findAll().stream()
                .map(studentMapper::studentToStudentDto)
                .collect(Collectors.toList());
    }

    public Student find(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student is not found!"));
    }
}
