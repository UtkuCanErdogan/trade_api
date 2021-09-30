package com.erdogan.utku.can.trade.service;

import com.erdogan.utku.can.trade.dto.CreateUserDetailsRequest;
import com.erdogan.utku.can.trade.dto.UpdateUserDetailsRequest;
import com.erdogan.utku.can.trade.dto.UserDetailsDto;
import com.erdogan.utku.can.trade.dto.converter.UserDetailsDtoConverter;
import com.erdogan.utku.can.trade.exception.UserDetailNotFoundException;
import com.erdogan.utku.can.trade.model.User;
import com.erdogan.utku.can.trade.model.UserDetails;
import com.erdogan.utku.can.trade.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {

    private final UserDetailsRepository repository;
    private final UserDetailsDtoConverter converter;
    private final UserService userService;

    public UserDetailsService(UserDetailsRepository repository, UserDetailsDtoConverter converter, UserService userService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
    }

    public UserDetailsDto getUserDetailById(final Long id){
        UserDetails userDetails = findUserDetailById(id);
        return converter.convert(userDetails);
    }

    public UserDetailsDto createUserDetails(final CreateUserDetailsRequest request){
        User user = userService.findUserById(request.getUserId());

        UserDetails userDetails = new UserDetails(
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCountry(),
                request.getCity(),
                request.getPostCode(),
                user);

        return converter.convert(repository.save(userDetails));
    }

    public UserDetailsDto updateUserDetails(final UpdateUserDetailsRequest request, final Long userDetailId){
        UserDetails userDetails = findUserDetailById(userDetailId);

        UserDetails updatedUserDetail = new UserDetails(
                userDetails.getId(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCountry(),
                request.getCity(),
                request.getPostCode(),
                userDetails.getUser());

        return converter.convert(repository.save(updatedUserDetail));
    }

    public void deleteUserDetail(final Long userDetailId){
        findUserDetailById(userDetailId);
        repository.deleteById(userDetailId);
    }

    private UserDetails findUserDetailById(final Long id){
        return repository.findById(id).orElseThrow(() -> new UserDetailNotFoundException("Adres Bilgileri Bulunamadı!"));
    }

    protected List<UserDetailsDto> findAllUsers(){
        return converter.convert(repository.findAll());
    }

}
