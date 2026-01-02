package com.ved.inventory.service;

import com.ved.inventory.dto.ProductRequestDTO;
import com.ved.inventory.entity.Product;
import com.ved.inventory.exception.ResourceNotFoundException;
import com.ved.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // Automatically injects the Repository (Dependency Injection)
public class ProductService {

    private final ProductRepository productRepository;

    // 1. Get All Products (with Pagination)
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // 2. Get One Product
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    // 3. Create Product
    @Transactional
    public Product createProduct(ProductRequestDTO dto) {
        // Convert DTO (Menu) to Entity (Ingredients)
        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();

        return productRepository.save(product);
    }

    // 4. Update Product
    @Transactional
    public Product updateProduct(Long id, ProductRequestDTO dto) {
        Product existing = getProductById(id); // Re-use method to check if exists

        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        existing.setDescription(dto.getDescription());

        return productRepository.save(existing);
    }

    // 5. Delete Product
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}