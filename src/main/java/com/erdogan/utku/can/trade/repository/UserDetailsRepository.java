package com.erdogan.utku.can.trade.repository;

import com.erdogan.utku.can.trade.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
