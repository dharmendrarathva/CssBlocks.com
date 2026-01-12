package com.example.Blocks.service;

import com.example.Blocks.dto.ProductRequest;
import com.example.Blocks.entity.Product;
import com.example.Blocks.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product create(ProductRequest req) {
        Product p = new Product();
        p.setName(req.getName());
        p.setCode(req.getCode());
        return repo.save(p);
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product update(String id, ProductRequest req) {
        Product p = getById(id);
        p.setName(req.getName());
        p.setCode(req.getCode());
        return repo.save(p);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
