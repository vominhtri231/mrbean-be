package school.finalproject.mrbbe.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.user.User;
import school.finalproject.mrbbe.dto.user.UserDTO;
import school.finalproject.mrbbe.mapper.UserMapper;
import school.finalproject.mrbbe.repository.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(long id) {
        User user = find(id);
        userRepository.delete(user);
    }

    private User find(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not found!"));
    }

    public UserDTO get(long id) {
        return userMapper.userToUserDto(find(id));
    }
}
