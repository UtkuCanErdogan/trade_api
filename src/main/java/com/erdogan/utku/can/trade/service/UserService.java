package com.erdogan.utku.can.trade.service;

import com.erdogan.utku.can.trade.dto.CreateUserRequest;
import com.erdogan.utku.can.trade.dto.UpdateUserRequest;
import com.erdogan.utku.can.trade.dto.UserDetailsDto;
import com.erdogan.utku.can.trade.dto.UserDto;
import com.erdogan.utku.can.trade.dto.converter.UserDetailsDtoConverter;
import com.erdogan.utku.can.trade.dto.converter.UserDtoConverter;
import com.erdogan.utku.can.trade.exception.UserIsNotActiveException;
import com.erdogan.utku.can.trade.exception.UserNotFoundException;
import com.erdogan.utku.can.trade.model.User;
import com.erdogan.utku.can.trade.repository.UserDetailsRepository;
import com.erdogan.utku.can.trade.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private final UserDtoConverter converter;
    private final UserDetailsService service;


    public UserService(UserRepository repository, UserDtoConverter converter, UserDetailsService service) {
        this.repository = repository;
        this.converter = converter;
        this.service = service;

    }

    public UserDto createUser(final CreateUserRequest request){
        User user = new User(request.getMail(), request.getName(), request.getSurname(),false);
        return converter.convert(repository.save(user));
    }

    public UserDto updateUser(final UpdateUserRequest request, final Long id){
        User user = findUserById(id);

        if (!user.isActive()){
            logger.warn(String.format("Aktif olmayan kullanıcı güncellenemez!, Kullanıcı eposta: %s" , user.getMail()));
            throw new UserIsNotActiveException("Aktif olmayan kullanıcı güncellenemez!");
        }

        User updatedUser = new User(
                user.getId(),
                request.getMail(),
                request.getName(),
                request.getSurname(),
                user.isActive(),
                user.getUserDetails(),
                user.getAdverts());

        return converter.convert(repository.save(updatedUser));
    }

    public List<UserDto> getAllUsers(){
        return converter.convert(repository.findAll());
    }

    public UserDto getUserById(final Long id){
        User user = findUserById(id);
        return converter.convert(user);
    }

    public void deActiveUser(final Long id){
        changeActivateUser(id , false);
    }

    public void toActiveUser(final Long id){
        changeActivateUser(id, true);
    }

    public void deleteUser (final Long id){
        findUserById(id);
        repository.deleteById(id);
    }

    public List<UserDetailsDto> getAllUserDetails(){
        return service.findAllUsers();
    }

    private void changeActivateUser(final Long id, final Boolean isActive){
        User user = findUserById(id);

        User changeActivate = new User(user.getId(), user.getMail(), user.getName(), user.getSurname(), isActive);
        repository.save(changeActivate);
    }

    protected User findUserById(final Long id){
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("Kullanıc Bulunamadı"));
    }




}
