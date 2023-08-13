package main.service;

import java.util.List;
import main.dto.TaskDTO;
import main.model.Task;
import main.model.User;

public interface TaskManagerService {

  List<TaskDTO> getTask();

  void addTask(Task task);

  List<TaskDTO> filterTask(String filter);

  void changeTask(Long id, String name, String description, String date, User user);

  String deleteTask(String filter);

  void deleteAll();
}
