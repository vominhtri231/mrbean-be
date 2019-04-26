package school.finalproject.mrbbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.finalproject.mrbbe.dao.homework.Homework;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
