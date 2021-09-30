package com.erdogan.utku.can.trade.dto.converter;

import com.erdogan.utku.can.trade.dto.UserDto;
import com.erdogan.utku.can.trade.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    private final UserDetailsDtoConverter converter;
    private final AdvertsDtoConverter advertsDtoConverter;

    public UserDtoConverter(UserDetailsDtoConverter converter, AdvertsDtoConverter advertsDtoConverter) {
        this.converter = converter;
        this.advertsDtoConverter = advertsDtoConverter;
    }

    public UserDto convert(User from){
        return new UserDto(
                from.getMail(),
                from.getName(),
                from.getSurname(),
                from.getUserDetails().stream().map(converter ::convert).collect(Collectors.toSet()),
                from.getAdverts().stream().map(advertsDtoConverter :: convert).collect(Collectors.toSet()));
    }

    public List<UserDto> convert(List<User> fromList){
        return fromList.stream()
                .map(this::convert).collect(Collectors.toList());
    }
}
