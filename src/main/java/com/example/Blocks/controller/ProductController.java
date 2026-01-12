package com.example.Blocks.controller;

import com.example.Blocks.dto.ProductRequest;
import com.example.Blocks.entity.Product;
import com.example.Blocks.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody ProductRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable String id, @RequestBody ProductRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
