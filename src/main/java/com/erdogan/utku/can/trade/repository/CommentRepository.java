package com.erdogan.utku.can.trade.repository;

import com.erdogan.utku.can.trade.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
