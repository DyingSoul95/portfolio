package main.mapper;

import main.dto.UserDTO;
import main.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper/*(uses = {TaskMapper.class})*/
public interface UserMapper {

  UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
  UserDTO fromUser(User user);
  @InheritInverseConfiguration
  User toUser(UserDTO userDTO);
}
