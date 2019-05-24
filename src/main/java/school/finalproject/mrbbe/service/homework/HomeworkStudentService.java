package school.finalproject.mrbbe.service.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.homework.Homework;
import school.finalproject.mrbbe.dao.homework.HomeworkStudent;
import school.finalproject.mrbbe.dao.homework.HomeworkStudentId;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dto.homework.HomeworkStudentDTO;
import school.finalproject.mrbbe.mapper.HomeworkStudentMapper;
import school.finalproject.mrbbe.repository.homework.HomeworkStudentRepository;
import school.finalproject.mrbbe.service.user.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HomeworkStudentService {
    @Autowired
    private HomeworkStudentRepository homeworkStudentRepository;

    @Autowired
    private HomeworkStudentMapper homeworkStudentMapper;

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private StudentService studentService;

    public HomeworkStudentDTO getHomeworkStudent(long homeworkId, long studentId) {
        try {
            return homeworkStudentMapper.homeworkStudentToHomeworkStudentDto(find(homeworkId, studentId));
        } catch (ResponseStatusException e) {
            return new HomeworkStudentDTO();
        }
    }

    public HomeworkStudentDTO saveHomeworkStudent(HomeworkStudentDTO homeworkStudentDTO) {
        try {
            HomeworkStudent originHomeworkStudent = find(homeworkStudentDTO.getHomeworkId(), homeworkStudentDTO.getStudentId());
            return updateHomeworkStudent(originHomeworkStudent, homeworkStudentDTO);
        } catch (ResponseStatusException e) {
            return createHomeworkStudent(homeworkStudentDTO);
        }
    }

    public List<HomeworkStudentDTO> getAllResultOfHomework(long homeworkId) {
        Homework homework = homeworkService.find(homeworkId);
        Set<Student> students = homework.getLesson().getKlass().getStudents();
        return students.stream()
                .map(student -> {
                    try {
                        return find(homework.getId(), student.getId());
                    } catch (ResponseStatusException e) {
                        return HomeworkStudent.builder()
                                .choices(new ArrayList<>())
                                .student(student)
                                .build();
                    }
                })
                .map(homeworkStudent -> homeworkStudentMapper.homeworkStudentToHomeworkStudentDto(homeworkStudent))
                .collect(Collectors.toList());
    }

    public HomeworkStudentDTO save(HomeworkStudent homeworkStudent) {
        HomeworkStudent savedHomeworkStudent = homeworkStudentRepository.save(homeworkStudent);
        return homeworkStudentMapper.homeworkStudentToHomeworkStudentDto(savedHomeworkStudent);
    }

    private HomeworkStudentDTO createHomeworkStudent(HomeworkStudentDTO homeworkStudentDTO) {
        Homework homework = homeworkService.find(homeworkStudentDTO.getHomeworkId());
        Student student = studentService.find(homeworkStudentDTO.getStudentId());
        HomeworkStudent creatingHomeworkStudent = homeworkStudentMapper.homeworkStudentDtoToHomeworkStudent(homeworkStudentDTO);
        creatingHomeworkStudent.setId(new HomeworkStudentId(homework.getId(), student.getId()));
        HomeworkStudent createdHomeworkStudent = homeworkStudentRepository.save(creatingHomeworkStudent);
        return homeworkStudentMapper.homeworkStudentToHomeworkStudentDto(createdHomeworkStudent);
    }

    private HomeworkStudentDTO updateHomeworkStudent(HomeworkStudent originHomeworkStudent,
                                                     HomeworkStudentDTO updateHomeworkStudent) {
        originHomeworkStudent.setChoices(updateHomeworkStudent.getChoices());
        HomeworkStudent updatedHomeworkStudent = homeworkStudentRepository.save(originHomeworkStudent);
        return homeworkStudentMapper.homeworkStudentToHomeworkStudentDto(updatedHomeworkStudent);
    }

    public HomeworkStudent find(long homeworkId, long studentId) throws ResponseStatusException {
        Homework homework = homeworkService.find(homeworkId);
        Student student = studentService.find(studentId);
        return homeworkStudentRepository.findFirstByHomeworkAndStudent(homework, student)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Class is not found!"));
    }
}
