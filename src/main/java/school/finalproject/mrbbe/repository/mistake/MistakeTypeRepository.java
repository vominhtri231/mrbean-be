package school.finalproject.mrbbe.repository.mistake;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.mistake.MistakeType;

public interface MistakeTypeRepository extends JpaRepository<MistakeType, Long> {
}
