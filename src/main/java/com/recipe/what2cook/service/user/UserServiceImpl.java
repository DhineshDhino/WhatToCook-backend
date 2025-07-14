package com.recipe.what2cook.service.user;

import com.recipe.what2cook.dto.UserDTO;
import com.recipe.what2cook.repository.UserRepository;
import com.recipe.what2cook.entity.UserEntity;
import com.recipe.what2cook.exception.UserNotFoundException;
import com.recipe.what2cook.mapper.UserEntityMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserEntityMapperImpl userEntityMapper;


    public UserServiceImpl(UserRepository userRepository, UserEntityMapperImpl userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    private static final String NOT_FOUND_MESSAGE = "User not found with the given firstName ";


    public UserDTO getUser(String name) throws UserNotFoundException {
        UserEntity byFirstName = userRepository.findByFirstName(name);
        if (byFirstName==null) {
            log.error(NOT_FOUND_MESSAGE + name);
            throw new UserNotFoundException(NOT_FOUND_MESSAGE + name);
        }
        return userEntityMapper.toDto(byFirstName);
    }

    public List<UserDTO> getAllUser() {
        List<UserEntity> all = userRepository.findAll();
        return all.stream().map(user -> userEntityMapper.toDto(user)).toList();
    }

    public UserDTO addUser(UserDTO userDTO) {
        UserEntity userEntity = userEntityMapper.toEntity(userDTO);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userEntityMapper.toDto(savedUserEntity);
    }

    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException {
        UserEntity userEntityInDB = userRepository.findByFirstName(userDTO.getFirstName());
        if (userEntityInDB == null) {
            throw new UserNotFoundException(NOT_FOUND_MESSAGE + userDTO.getFirstName());
        }
        UserEntity updatedUserEntity = UserEntity.builder()
                .id(userEntityInDB.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .dateOfBirth(userDTO.getDateOfBirth())
                .email(userDTO.getEmail())
                .build();
        UserEntity savedUserEntity = userRepository.save(updatedUserEntity);
        return savedUserEntity == null ? null : userEntityMapper.toDto(savedUserEntity);
    }
}
