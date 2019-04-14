package school.finalproject.mrbbe.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.user.Teacher;
import school.finalproject.mrbbe.dto.user.TeacherDTO;
import school.finalproject.mrbbe.mapper.TeacherMapper;
import school.finalproject.mrbbe.repository.user.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService extends UserGeneralService<Teacher> {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserService userService;

    @Autowired
    TeacherMapper teacherMapper;

    public TeacherDTO create(TeacherDTO teacherDTO) {
        Teacher savingTeacher = teacherMapper.teacherDtoToTeacher(teacherDTO);
        Teacher preparedTeacher = prepareForCreate(savingTeacher);
        Teacher savedTeacher = teacherRepository.save(preparedTeacher);
        return teacherMapper.teacherToTeacherDto(savedTeacher);
    }

    public Teacher find(long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Teacher is not found!"));
    }

    public List<TeacherDTO> getAll() {
        return teacherRepository.findAll().stream()
                .map(teacher -> teacherMapper.teacherToTeacherDto(teacher))
                .collect(Collectors.toList());
    }
}
