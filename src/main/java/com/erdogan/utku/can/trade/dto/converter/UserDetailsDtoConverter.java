package com.erdogan.utku.can.trade.dto.converter;

import com.erdogan.utku.can.trade.dto.UserDetailsDto;
import com.erdogan.utku.can.trade.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDtoConverter {

    public UserDetailsDto convert(UserDetails from){
        return new UserDetailsDto(from.getPhoneNumber(), from.getAddress(), from.getCountry(), from.getCity(), from.getPostCode());
    }

    public List<UserDetailsDto> convert(List<UserDetails> fromList){
        return fromList.stream()
                .map(this::convert).collect(Collectors.toList());
    }
}
