package com.erdogan.utku.can.trade;

import com.erdogan.utku.can.trade.dto.UserDetailsDto;
import com.erdogan.utku.can.trade.dto.UserDto;
import com.erdogan.utku.can.trade.model.User;
import com.erdogan.utku.can.trade.model.UserDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    public  static Long userId = 1L;
    public  static Long userDetailId = 1L;
    public static String mail = "mail@gmail.com";

    public static User generateUser(Long id, Boolean isActive){
        return new User(userId,
                mail,
                "firsName" + userId, "lastName" + userId,
                isActive);
    }

    public static List<User> generateUsers(){
        return IntStream.range(0, 5).mapToObj(i ->
                new User((long)i,
                        i + "@gmail.com",
                        "firsName" + i, "lastName" + i,
                        new Random(2).nextBoolean())
        ).collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<User> userList){
        return userList.stream().map(from -> new UserDto(
                from.getMail(),
                from.getName(),
                from.getSurname()
                )
        ).collect(Collectors.toList());
    }

    public static UserDto generateUserDto(String mail){
        return new UserDto(mail, "fistName"+userId,"lastName"+userId);
    }

    public static UserDetails generateUserDetailsWithOutId(){
        return new UserDetails(
                "5074174445",
                "Yenimahalle/Ankara",
                "Türkiye",
                "Ankara",
                "0606",
                generateUser(userId, true));
    }

    public static UserDetails generateUserDetails(Long id){
        return new UserDetails(id,
                "5074174445",
                "Yenimahalle/Ankara",
                "Türkiye",
                "Ankara",
                "0606",
                generateUser(userId, true));
    }

    public static UserDetailsDto generateUserDetailsDto(){
        return new UserDetailsDto("5074174445",
                "Yenimahalle/Ankara",
                "Türkiye",
                "Ankara",
                "0606"
                );
    }



}
