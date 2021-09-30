package com.erdogan.utku.can.trade.controller;

import com.erdogan.utku.can.trade.dto.CommentDto;
import com.erdogan.utku.can.trade.dto.CreateCommentRequest;
import com.erdogan.utku.can.trade.dto.UpdateCommentRequest;
import com.erdogan.utku.can.trade.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/gettAllComments")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id){
        return ResponseEntity.ok(service.getCommentById(id));
    }

    @PostMapping("/addComment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CreateCommentRequest request){
        return ResponseEntity.ok(service.createComment(request));
    }

    @PutMapping("/updateComment")
    public ResponseEntity<CommentDto> updateComment(@RequestBody UpdateCommentRequest request, @RequestBody Long commentId){
        return ResponseEntity.ok(service.updateComment(request,commentId));
    }

    @DeleteMapping("/[id]/delete")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long commentId){
        service.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
