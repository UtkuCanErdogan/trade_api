package com.erdogan.utku.can.trade.dto.converter;

import com.erdogan.utku.can.trade.dto.CommentDto;
import com.erdogan.utku.can.trade.dto.UserDto;
import com.erdogan.utku.can.trade.model.Comment;
import com.erdogan.utku.can.trade.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoConverter {

    public CommentDto convert (Comment from, User fromUser){
        return new CommentDto(fromUser.getName(), fromUser.getSurname(), from.getMessage());
    }

}
