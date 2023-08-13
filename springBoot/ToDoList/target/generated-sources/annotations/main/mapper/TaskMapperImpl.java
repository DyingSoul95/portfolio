package main.mapper;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import main.dto.TaskDTO;
import main.dto.UserDTO;
import main.model.Role;
import main.model.Task;
import main.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-03T21:21:15+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Amazon.com Inc.)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toTask(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDTO.getId() );
        task.setName( taskDTO.getName() );
        task.setDescription( taskDTO.getDescription() );
        task.setDate( taskDTO.getDate() );
        task.setAuthor( taskDTO.getAuthor() );

        return task;
    }

    @Override
    public TaskDTO fromTask(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setAuthorName( task.getAuthorName() );
        taskDTO.setId( task.getId() );
        taskDTO.setName( task.getName() );
        taskDTO.setDescription( task.getDescription() );
        taskDTO.setDate( task.getDate() );
        taskDTO.setAuthor( task.getAuthor() );

        return taskDTO;
    }

    @Override
    public UserDTO fromUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setActive( user.isActive() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDTO.setRoles( new HashSet<Role>( set ) );
        }

        return userDTO;
    }
}
