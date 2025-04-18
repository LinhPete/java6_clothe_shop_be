package com.linhpete.java6.persistence.repository;

import com.linhpete.java6.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    Set<Category> findAllByNameIsIn(Set<String> categories);
}
