package com.onlinestore.repository;

import com.onlinestore.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    public Optional<Category> findByName (String name);
}
