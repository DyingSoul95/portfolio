package main.service.impl;

import java.util.ArrayList;
import java.util.List;
import main.dto.TaskDTO;
import main.mapper.TaskMapper;
import main.model.Task;
import main.model.User;
import main.repository.TaskRepository;
import main.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  @Autowired
  public TaskManagerServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  @Override
  public List<TaskDTO> getTask() {

    ArrayList<TaskDTO> tasks = new ArrayList<>();
    Iterable<Task> iterable = taskRepository.findAll();
    for (Task task : iterable) {
      tasks.add(taskMapper.fromTask(task));
    }
    return tasks;
  }

  @Override
  public void addTask(Task task) {
    taskRepository.save(task);
  }

  @Override
  public List<TaskDTO> filterTask(String filter) {
    ArrayList<TaskDTO> tasks = new ArrayList<>();
    Iterable<Task> iterable;
    if (!filter.isEmpty()) {
      iterable = taskRepository.findById(Long.valueOf(filter));
      for (Task task : iterable) {
        tasks.add(taskMapper.fromTask(task));
      }
    } else {
      iterable = taskRepository.findAll();
      for (Task task : iterable) {
        tasks.add(taskMapper.fromTask(task));
      }
    }
    return tasks;
  }

  @Override
  public void changeTask(Long id, String name, String description, String date, User user) {
    TaskDTO taskDTO = new TaskDTO(id, name, description, date, user);
    taskRepository.save(taskMapper.toTask(taskDTO));
  }

  @Override
  public String deleteTask(String filter) {
    Iterable<Task> tasks;
    if (!filter.isEmpty()) {
      taskRepository.deleteById(Long.valueOf(filter));
    } else {
      return "deleteAll";
    }
    return "taskList";
  }

  @Override
  public void deleteAll() {
    taskRepository.deleteAll();
  }
}
