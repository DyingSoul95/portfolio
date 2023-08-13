package main.repository;

import java.util.List;
import main.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {

  List<Task> findById(Long id);

  List<Task> deleteById(Long id);
}
