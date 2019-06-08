package school.finalproject.mrbbe.service.klass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.klass.Klass;
import school.finalproject.mrbbe.dao.user.Student;
import school.finalproject.mrbbe.dao.user.Teacher;
import school.finalproject.mrbbe.dto.klass.KlassDTO;
import school.finalproject.mrbbe.mapper.KlassMapper;
import school.finalproject.mrbbe.repository.klass.KlassRepository;
import school.finalproject.mrbbe.service.user.StudentService;
import school.finalproject.mrbbe.service.user.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KlassService {
    @Autowired
    private KlassRepository klassRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private KlassStudentService klassStudentService;

    @Autowired
    private KlassMapper klassMapper;

    public KlassDTO create(KlassDTO klassDTO) {
        Klass savingKlass = klassMapper.klassDTOtoKlass(klassDTO);
        savingKlass.setTeacher(teacherService.find(klassDTO.getTeacherId()));
        Klass createdKlass = klassRepository.save(savingKlass);

        klassStudentService.registerStudents(createdKlass.getId(), klassDTO.getStudentIds());

        return klassMapper.klassToKlassDTO(createdKlass);
    }

    public List<KlassDTO> getAll() {
        return klassRepository.findAll()
                .stream()
                .map(klass -> klassMapper.klassToKlassDTO(klass))
                .collect(Collectors.toList());
    }

    public Klass find(long id) {
        return klassRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Class is not found!"));
    }

    public KlassDTO get(long id) {
        return klassMapper.klassToKlassDTO(find(id));
    }

    public List<KlassDTO> getAllOfTeacher(long teacherId) {
        Teacher teacher = teacherService.find(teacherId);
        return klassRepository
                .findAllByTeacher(teacher)
                .stream()
                .map(klass -> klassMapper.klassToKlassDTO(klass))
                .collect(Collectors.toList());
    }

    public KlassDTO updateKlass(KlassDTO klassDTO) {
        Klass updatingKlass = find(klassDTO.getId());
        updatingKlass.setTeacher(teacherService.find(klassDTO.getTeacherId()));
        updatingKlass.setName(klassDTO.getName());
        updatingKlass.setDescription(klassDTO.getDescription());
        Klass updatedKlass = klassRepository.save(updatingKlass);
        return klassMapper.klassToKlassDTO(updatedKlass);
    }

    public void delete(long id) {
        Klass deleteKlass = find(id);
        klassRepository.delete(deleteKlass);
    }
}
