package com.linhpete.java6.persistence.repository;

import com.linhpete.java6.persistence.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
}
