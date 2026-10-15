package com.example.ProductInventory;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Add product
    @PostMapping
    public ResponseEntity<Product> addProduct(
            @Valid @RequestBody Product product) {

        Product savedProduct = productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(savedProduct);
    }

    // View all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // View product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // Update product
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable int id,
            @Valid @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable int id) {

        return ResponseEntity.ok(
            productService.deleteProduct(id));
    }

    // View products by category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(
            @PathVariable String category) {

        return productService.getProductsByCategory(category);
    }
}
