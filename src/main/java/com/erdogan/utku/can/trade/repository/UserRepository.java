package com.erdogan.utku.can.trade.repository;

import com.erdogan.utku.can.trade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
