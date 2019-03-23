package school.finalproject.mrbbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.finalproject.mrbbe.dao.Klass;

@Repository
public interface KlassRepository extends JpaRepository<Klass,Integer> {
}
