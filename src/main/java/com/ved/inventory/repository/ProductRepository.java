package com.ved.inventory.repository;

import com.ved.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // That's it!
    // Spring Data JPA automatically gives you methods like:
    // .save(), .findById(), .findAll(), .deleteById()
}
