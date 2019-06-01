package school.finalproject.mrbbe.repository.mistake;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.mistake.MistakeRule;

public interface MistakeRuleRepository extends JpaRepository<MistakeRule, Long> {
}
