package com.grok.hunt.Controllers;

import com.grok.hunt.Services.ProductService;
import com.grok.hunt.dtos.ProductRequest;
import com.grok.hunt.dtos.ProductResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Create a product
    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest, HttpServletRequest request) {
        return productService.createProduct(productRequest, request);
    }

    // Get all products
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id, HttpServletRequest request) {
        productService.deleteProduct(id, request);
    }

    // Update product (basic example)
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable int id, @RequestBody ProductRequest updatedProduct, HttpServletRequest request) {
        return productService.updateProduct(id, updatedProduct, request);
    }

    // Upvote a product
    @PostMapping("/{id}/upvote")
    public ProductResponse upvoteProduct(@PathVariable int id) {
        return productService.upvoteProduct(id);
    }
}
