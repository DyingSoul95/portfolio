package main.mapper;

import main.dto.TaskDTO;
import main.dto.UserDTO;
import main.model.Task;
import main.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaskMapper {

  Task toTask(TaskDTO taskDTO);

  TaskDTO fromTask(Task task);

  UserDTO fromUser(User user);

}
