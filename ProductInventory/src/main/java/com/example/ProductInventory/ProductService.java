



package com.example.ProductInventory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    // Add product
    public Product addProduct(Product product) {

        for (Product p : products) {
            if (p.getProductId() == product.getProductId()) {
                throw new ProductNotFoundException(
                    "Product ID already exists: "
                    + product.getProductId());
            }
        }

        products.add(product);
        return product;
    }

    // View all products
    public List<Product> getAllProducts() {
        return products;
    }

    // Find product by ID
    public Product getProductById(int id) {

        return products.stream()
            .filter(p -> p.getProductId() == id)
            .findFirst()
            .orElseThrow(() ->
                new ProductNotFoundException(
                    "Product not found with ID: " + id));
    }

    // Update product
    public Product updateProduct(int id, Product updatedProduct) {

        for (int i = 0; i < products.size(); i++) {

            if (products.get(i).getProductId() == id) {

                updatedProduct.setProductId(id);
                products.set(i, updatedProduct);

                return updatedProduct;
            }
        }

        throw new ProductNotFoundException(
            "Product not found with ID: " + id);
    }

    // Delete product
    public String deleteProduct(int id) {

        for (int i = 0; i < products.size(); i++) {

            if (products.get(i).getProductId() == id) {
                products.remove(i);
                return "Product deleted successfully";
            }
        }

        throw new ProductNotFoundException(
            "Product not found with ID: " + id);
    }

    // Find products by category
    public List<Product> getProductsByCategory(String category) {

        return products.stream()
            .filter(p -> p.getCategory()
                .equalsIgnoreCase(category))
            .toList();
    }
}

