package school.finalproject.mrbbe.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.user.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
