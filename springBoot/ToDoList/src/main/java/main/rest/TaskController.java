package main.rest;


import java.util.Map;
import main.dto.TaskDTO;
import main.mapper.TaskMapper;
import main.model.Task;
import main.model.User;
import main.service.TaskManagerService;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

  private final TaskManagerService taskManagerService;

  private final TaskMapper taskMapper;

  public TaskController(TaskManagerService taskManagerService, TaskMapper taskMapper) {
    this.taskManagerService = taskManagerService;
    this.taskMapper = taskMapper;
  }


  @GetMapping("/")
  public String greeting(Map<String, Object> model) {
    return "greeting";
  }

  @GetMapping("tasks")
  public String tasks(Map<String, Object> model) {
    model.put("massages", taskManagerService.getTask());
    return "taskList";
  }

  @PostMapping("addList")

  public String add(
      @AuthenticationPrincipal User user, TaskDTO taskDTO, Map<String, Object> model) {
    Task task = taskMapper.toTask(taskDTO);
    task.setAuthor(user);
    taskManagerService.addTask(task);
    model.put("massages", taskManagerService.getTask());
    return "taskList";
  }

  @PostMapping("filter")
  public String filter(@RequestParam String filter, Map<String, Object> model) {
    model.put("massages", taskManagerService.filterTask(filter));
    return "taskList";
  }

  @PostMapping("change")
  public String change(
      @AuthenticationPrincipal User user,
      @RequestParam Long id, @RequestParam String name,
      @RequestParam String description,
      @RequestParam String date, Map<String, Object> model) {
    taskManagerService.changeTask(id, name, description, date, user);
    model.put("massages", taskManagerService.getTask());
    return "taskList";
  }

  @Transactional
  @PostMapping("delete")
  public String delete(@RequestParam String filter, Map<String, Object> model) {
    String text = taskManagerService.deleteTask(filter);
    model.put("massages", taskManagerService.getTask());
    return text;
  }

  @PostMapping("deleteAll")
  public String deleteAll() {
    taskManagerService.deleteAll();
    return "taskList";
  }

}
