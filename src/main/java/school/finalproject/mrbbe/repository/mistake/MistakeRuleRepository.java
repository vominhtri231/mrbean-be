package school.finalproject.mrbbe.repository.mistake;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.mistake.MistakeRule;

import java.util.List;

public interface MistakeRuleRepository extends JpaRepository<MistakeRule,Long> {
    List<MistakeRule> findAllByOrderByNumber();
}
