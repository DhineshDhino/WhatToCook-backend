package com.recipe.what2cook.service.user;

import com.recipe.what2cook.dto.UserDTO;
import com.recipe.what2cook.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO getUser(String name) throws UserNotFoundException;
    List<UserDTO> getAllUser();
    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException;

}
