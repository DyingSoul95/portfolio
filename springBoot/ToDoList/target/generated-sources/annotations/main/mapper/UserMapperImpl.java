package main.mapper;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import main.dto.UserDTO;
import main.model.Role;
import main.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-03T21:21:15+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Amazon.com Inc.)"
)
public class UserMapperImpl implements UserMapper {

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

    @Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setActive( userDTO.isActive() );
        Set<Role> set = userDTO.getRoles();
        if ( set != null ) {
            user.setRoles( new HashSet<Role>( set ) );
        }

        return user;
    }
}
