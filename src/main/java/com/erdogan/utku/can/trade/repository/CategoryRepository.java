package com.erdogan.utku.can.trade.repository;

import com.erdogan.utku.can.trade.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
