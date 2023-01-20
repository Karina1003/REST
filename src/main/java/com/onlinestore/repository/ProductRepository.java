package com.onlinestore.repository;

import com.onlinestore.dto.ProductDetailsDto;
import com.onlinestore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE name=:name")
    public Page<Product> findAllByName(Pageable pageable, @Param("name") String name);
    @Query("SELECT p FROM Product p WHERE description LIKE (%:description%)")
    public Page<Product> findAllByDescription(Pageable pageable, @Param("description") String description);

    @Query("SELECT p FROM Product p WHERE name=:name AND description LIKE (%:description%)")
    public Page<Product> findAllByNameAndDescription(Pageable pageable, @Param("name") String name,
                                                    @Param("description") String description);


}
