package com.portafolio.my_portafolio_backend.mapper;

import com.portafolio.my_portafolio_backend.dto.UserDTO;
import com.portafolio.my_portafolio_backend.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
