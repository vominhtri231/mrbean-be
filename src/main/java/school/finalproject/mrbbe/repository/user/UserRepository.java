package school.finalproject.mrbbe.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByEmailIgnoreCase(String email);
}
