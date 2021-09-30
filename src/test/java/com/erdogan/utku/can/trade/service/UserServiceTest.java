package com.erdogan.utku.can.trade.service;

import com.erdogan.utku.can.trade.TestSupport;
import com.erdogan.utku.can.trade.dto.CreateUserRequest;
import com.erdogan.utku.can.trade.dto.UpdateUserRequest;
import com.erdogan.utku.can.trade.dto.UserDto;
import com.erdogan.utku.can.trade.dto.converter.UserDtoConverter;
import com.erdogan.utku.can.trade.exception.UserIsNotActiveException;
import com.erdogan.utku.can.trade.exception.UserNotFoundException;
import com.erdogan.utku.can.trade.model.User;
import com.erdogan.utku.can.trade.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest extends TestSupport {

    private UserRepository repository;
    private UserDtoConverter converter;
    private UserDetailsService userDetailsService;
    private UserService service ;

    @BeforeEach
    public void setup(){
        converter = Mockito.mock(UserDtoConverter.class);
        repository = Mockito.mock(UserRepository.class);
        userDetailsService = Mockito.mock(UserDetailsService.class);

        service = new UserService(repository, converter, userDetailsService);
    }

    @Test

    public void testGetAllUsers_itShouldReturnUserDtoList(){
        List<User> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);
        when(repository.findAll()).thenReturn(userList);
        when(converter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = service.getAllUsers();
        assertEquals(userDtoList, result);
        verify(repository).findAll();
        verify(converter).convert(userList);
    }

    @Test
    public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDto(){

        User user = generateUser(userId, true);
        UserDto userDto = generateUserDto(mail);
        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(converter.convert(user)).thenReturn(userDto);

        UserDto result = service.getUserById(userId);
        assertEquals(userDto, result);
        verify(repository).findById(userId);
        verify(converter).convert(user);
    }

    @Test
    public void testGetUserByMail_whenUserMailDoesNotExist_itShouldReturnThrowUserNotFoundException(){

        when(repository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> service.getUserById(userId));

        verify(repository).findById(userId);
        verifyNoInteractions(converter);
    }

    @Test
    public void testCreateUser_itShouldReturnCreateUserDto(){

        CreateUserRequest req = new CreateUserRequest(mail,"firstName","LastName");
        User user = new User(mail,"firstName","LastName",false);
        User createdUser = generateUser(userId, false);
        UserDto userDto = generateUserDto(mail);

        when(repository.save(user)).thenReturn(createdUser);
        when(converter.convert(createdUser)).thenReturn(userDto);

        UserDto result = service.createUser(req);

        assertEquals(userDto, result);

        verify(repository).save(user);
        verify(converter).convert(createdUser);
    }

    @Test
    public void testUpdateUser_whenUserMailExistAndUserIsActive_itShouldReturnCreateUserDto(){

        UpdateUserRequest request = new UpdateUserRequest(mail,"firstName2","lastName2");
        User user = new User(1L,mail,"firstName","lastName",true);
        User updatedUser =  new User(1L,mail,"firstName2","lastName2",true);
        UserDto userDto = new UserDto(mail,"firstName2","lastName2");

        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(repository.save(updatedUser)).thenReturn(updatedUser);
        when(converter.convert(updatedUser)).thenReturn(userDto);

        UserDto result = service.updateUser(request, userId);

        assertEquals(userDto, result);

        verify(repository).findById(userId);
        verify(repository).save(updatedUser);
        verify(converter).convert(updatedUser);
    }

    @Test
    public void testUpdateUser_whenUserMailDoesNotExist_itShouldReturnUserNotFoundException(){

        UpdateUserRequest request = new UpdateUserRequest(mail,"firstName2","lastName2");

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.updateUser(request,userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(converter);
    }

    @Test
    public void testUpdateUser_whenUserMailExistButUserNotActive_itShouldReturnUserIsNotActiveException(){

        UpdateUserRequest request = new UpdateUserRequest(mail,"firstName2","lastName2");
        User user = generateUser(userId, false);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        assertThrows(UserIsNotActiveException.class, () -> service.updateUser(request,userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(converter);
    }

    @Test
    public void testDeActiveUser_whenUserExist_itShouldUpdateUserByActiveFalse(){

        User user = generateUser(userId, true);
        User savedUser = generateUser(userId, false);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        service.deActiveUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testDeActiveUser_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.deActiveUser(userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void testToActiveUser_whenUserExist_itShouldUpdateUserByActiveFalse(){

        User user = generateUser(userId, false);
        User savedUser = generateUser(userId, true);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        service.toActiveUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testToActiveUser_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.toActiveUser(userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void testDeleteUser_whenUserExist_itShouldDeleteUser(){

        User user = generateUser(userId, false);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        service.deleteUser(userId);

        verify(repository).findById(userId);
        verify(repository).deleteById(userId);

    }

    @Test
    public void testToDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.deleteUser(userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }


}