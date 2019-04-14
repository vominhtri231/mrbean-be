package school.finalproject.mrbbe.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.user.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
