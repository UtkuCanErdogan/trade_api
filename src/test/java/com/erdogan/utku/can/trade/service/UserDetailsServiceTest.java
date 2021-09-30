package com.erdogan.utku.can.trade.service;

import com.erdogan.utku.can.trade.TestSupport;
import com.erdogan.utku.can.trade.dto.CreateUserDetailsRequest;
import com.erdogan.utku.can.trade.dto.UpdateUserDetailsRequest;
import com.erdogan.utku.can.trade.dto.UserDetailsDto;
import com.erdogan.utku.can.trade.dto.converter.UserDetailsDtoConverter;
import com.erdogan.utku.can.trade.exception.UserDetailNotFoundException;
import com.erdogan.utku.can.trade.model.User;
import com.erdogan.utku.can.trade.model.UserDetails;
import com.erdogan.utku.can.trade.repository.UserDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserDetailsServiceTest extends TestSupport {

    private UserDetailsRepository repository;
    private UserDetailsDtoConverter converter;
    private UserDetailsService service;
    private UserService userService;

    @BeforeEach
    public void setup(){
        repository = mock(UserDetailsRepository.class);
        converter = mock(UserDetailsDtoConverter.class);
        userService = mock(UserService.class);

        service = new UserDetailsService(repository,converter,userService);
    }

    @Test
    public void testCreateUserDetail_whenUserIdExist_itShouldReturnUserDetailDto(){
        User user = generateUser(userId,true);
        UserDetails userDetails = generateUserDetailsWithOutId();
        CreateUserDetailsRequest request = new CreateUserDetailsRequest(
                userId,
                "5074174445",
                "Yenimahalle/Ankara",
                "Türkiye",
                "Ankara",
                "0606");
        UserDetails createdUserDetail = generateUserDetails(userDetailId);
        UserDetailsDto dto = generateUserDetailsDto();

        when(userService.findUserById(userId)).thenReturn(user);
        when(repository.save(userDetails)).thenReturn(createdUserDetail);
        when(converter.convert(createdUserDetail)).thenReturn(dto);

        UserDetailsDto result = service.createUserDetails(request);
        assertEquals(dto,result);

        verify(userService).findUserById(userId);
        verify(repository).save(userDetails);
        verify(converter).convert(createdUserDetail);

    }

    @Test
    public void testUpdateUserDetails_whenUserDetailsIdExist_itShouldReturnUserDetailsDto(){
        UserDetails userDetails = generateUserDetails(userDetailId);
        UpdateUserDetailsRequest request = new UpdateUserDetailsRequest(
                "5074174445",
                "Susuz Mahallesi No:37 Ankara",
                "Türkiye",
                "Ankara",
                "0637"
        );
        UserDetails updatedUserDetail = new UserDetails(
                userDetailId,
                "5074174445",
                "Susuz Mahallesi No:37 Ankara",
                "Türkiye",
                "Ankara",
                "0637",
                generateUser(userId,true)
        );
        UserDetailsDto dto = new UserDetailsDto(
                "5074174445",
                "Susuz Mahallesi No:37 Ankara",
                "Türkiye",
                "Ankara",
                "0637"
        );

        when(repository.findById(userDetailId)).thenReturn(Optional.of(userDetails));
        when(repository.save(updatedUserDetail)).thenReturn(updatedUserDetail);
        when(converter.convert(updatedUserDetail)).thenReturn(dto);

        UserDetailsDto result = service.updateUserDetails(request,userDetailId);

        assertEquals(dto,result);

        verify(repository).findById(userDetailId);
        verify(repository).save(updatedUserDetail);
        verify(converter).convert(updatedUserDetail);
    }

    @Test
    public void testUpdateUserDetails_whenUserDetailsIdDoesNotExist_itShouldThrowUserDetailsIsNotFoundException(){
        UpdateUserDetailsRequest request = new UpdateUserDetailsRequest(
                "5074174445",
                "Susuz Mahallesi No:37 Ankara",
                "Ankara",
                "Türkiye",
                "0637");
        when(repository.findById(userDetailId)).thenReturn(Optional.empty());

        assertThrows(UserDetailNotFoundException.class, () -> service.updateUserDetails(request,userDetailId));

        verify(repository).findById(userDetailId);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(converter);
    }

    @Test
    public void testDeleteUserDetail_whenUserDetailExist_itShouldDeleteUser(){
        User user = generateUser(userId,true);
        UserDetails userDetails = new UserDetails(userDetailId,
                "5074174445",
                "2.İnönü Caddesi No:37 Ankara",
                "Ankara",
                "Türkiye",
                "0606",
                user);

        when(repository.findById(userDetailId)).thenReturn(Optional.of(userDetails));
        service.deleteUserDetail(userDetailId);

        verify(repository).findById(userDetailId);
        verify(repository).deleteById(userDetailId);
    }

    @Test
    public void testDeleteUserDetail_whenUserDetailDoesNotExist_itShouldThrowUserDetailsIsNotFoundException(){
        when(repository.findById(userDetailId)).thenReturn(Optional.empty());

        assertThrows(UserDetailNotFoundException.class, () -> service.deleteUserDetail(userDetailId));

        verify(repository).findById(userDetailId);
        verifyNoMoreInteractions(repository);
    }
}