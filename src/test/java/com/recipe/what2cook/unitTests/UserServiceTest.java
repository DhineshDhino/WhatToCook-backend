package com.recipe.what2cook.unitTests;

import com.recipe.what2cook.dto.UserDTO;
import com.recipe.what2cook.repository.UserRepository;
import com.recipe.what2cook.entity.UserEntity;
import com.recipe.what2cook.exception.UserNotFoundException;
import com.recipe.what2cook.mapper.UserEntityMapperImpl;
import com.recipe.what2cook.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
 class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserEntityMapperImpl userEntityMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetUser() throws UserNotFoundException {
        when(userRepository.findByFirstName("test")).thenReturn(UserEntity.builder().firstName("test").lastName("test").dateOfBirth("test").email("test").build());
        when(userEntityMapper.toDto(any())).thenReturn(UserDTO.builder().firstName("test").lastName("test").dateOfBirth("test").email("test").build());
assertEquals(UserDTO.builder().firstName("test").lastName("test").dateOfBirth("test").email("test").build(),userService.getUser("test"));
    }

    @Test
    void testGetUserException() throws UserNotFoundException {
        when(userRepository.findByFirstName("test")).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.getUser("test"));
    }
}
