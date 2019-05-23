package school.finalproject.mrbbe.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import school.finalproject.mrbbe.dao.user.User;
import school.finalproject.mrbbe.repository.user.UserRepository;
import school.finalproject.mrbbe.support.Constant.PasswordConstant;

public abstract class UserGeneralService<T extends User> {
    @Autowired
    private UserRepository userRepository;

    T prepareForCreate(T user) {
        userRepository.findUserByEmailIgnoreCase(user.getEmail()).ifPresent(
                u -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User existed");
                }
        );
        user.setPassword(PasswordConstant.defaultPassword);
        return user;
    }
}
