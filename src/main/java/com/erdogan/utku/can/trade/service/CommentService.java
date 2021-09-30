package com.erdogan.utku.can.trade.service;

import com.erdogan.utku.can.trade.dto.CommentDto;
import com.erdogan.utku.can.trade.dto.CreateCommentRequest;
import com.erdogan.utku.can.trade.dto.UpdateCommentRequest;
import com.erdogan.utku.can.trade.dto.converter.CommentDtoConverter;
import com.erdogan.utku.can.trade.exception.CommentNotFoundException;
import com.erdogan.utku.can.trade.model.Advert;
import com.erdogan.utku.can.trade.model.Comment;
import com.erdogan.utku.can.trade.model.User;
import com.erdogan.utku.can.trade.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository repository;
    private final CommentDtoConverter converter;
    private final AdvertService advertService;

    public CommentService(CommentRepository repository, CommentDtoConverter converter, UserService userService, AdvertService advertService) {
        this.repository = repository;
        this.converter = converter;
        this.advertService = advertService;
    }

    public CommentDto createComment(final CreateCommentRequest request){

        Advert advert = advertService.findAdvertById(request.getAdvertId());
        User user = advert.getUser();

        Comment comment = new Comment(request.getMessage(),advert,user);

        return converter.convert(repository.save(comment),user);
    }

    public CommentDto updateComment(final UpdateCommentRequest request, final Long commentId){

        Comment comment = findCommentById(commentId);
        User user = comment.getUser();

        Comment updatedComment = new Comment(
                comment.getId(),
                comment.getMessage(),
                comment.getAdvert(),
                comment.getUser()
                );

        return converter.convert(repository.save(updatedComment),user);
    }

    public CommentDto getCommentById(final Long commentId){
        Comment comment = findCommentById(commentId);
        User user = comment.getUser();

        return converter.convert(comment,user);
    }

    public void deleteComment(final Long commentId){
        findCommentById(commentId);
        repository.deleteById(commentId);
    }

    private Comment findCommentById(final Long commentId){
        return repository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Yorum Bulunamadı"));
    }
}
