package com.grok.hunt.Services;

import com.grok.hunt.Models.Product;
import com.grok.hunt.Models.Users;
import com.grok.hunt.Repository.ProductRepository;
import com.grok.hunt.Repository.UserRepo;
import com.grok.hunt.config.JwtFilter;
import com.grok.hunt.dtos.ProductRequest;
import com.grok.hunt.dtos.ProductResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepo  userRepository;
    private final JwtFilter jwtFilter;
    private final JWTService jWTService;

    private String getUsernameFromToken(HttpServletRequest httpServletRequest) {
        String token = jwtFilter.getTokenFromRequest(httpServletRequest);
        return jWTService.extractUserName(token);
    }

    public ProductResponse createProduct(ProductRequest productRequest, HttpServletRequest request) {
        Product product = new Product();
        String username = getUsernameFromToken(request);
        System.out.println(username);

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setTagline(productRequest.getTagline());
        product.setThumbnailUrl(productRequest.getThumbnailUrl());
        product.setWebsiteUrl(productRequest.getWebsiteUrl());
        product.setPostedBy(user);
        product.setCreatedAt(LocalDateTime.now());

        ProductResponse productResponse = getProductResponse(product);
        productRepository.save(product);

        return productResponse;
    }

    private static ProductResponse getProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setTagline(product.getTagline());
        productResponse.setThumbnailUrl(product.getThumbnailUrl());
        productResponse.setWebsiteUrl(product.getWebsiteUrl());
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUsername(product.getPostedBy().getUsername());
        return productResponse;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = getProductResponse(product);
            productResponses.add(productResponse);
        }

        return productResponses.isEmpty() ? null : productResponses;
    }

    public ProductResponse getProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found"));
        return getProductResponse(product);
    }

    public void deleteProduct(int id, HttpServletRequest request) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        String username = getUsernameFromToken(request);
        if (!product.getPostedBy().getUsername().equals(username)) {
            throw new RuntimeException("You are not permitted to delete this product");
        }

        productRepository.deleteById(id);
    }

    public ProductResponse updateProduct(int id, ProductRequest updatedProduct, HttpServletRequest request) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );

        String username = getUsernameFromToken(request);
        if (!product.getPostedBy().getUsername().equals(username)) {
            throw new RuntimeException("You are not permitted to update this product");
        }

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setTagline(updatedProduct.getTagline());
        product.setThumbnailUrl(updatedProduct.getThumbnailUrl());
        product.setWebsiteUrl(updatedProduct.getWebsiteUrl());
        product.setCreatedAt(LocalDateTime.now());
        productRepository.save(product);

        return getProductResponse(product);
    }

    public ProductResponse upvoteProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );

        product.setUpvotes(product.getUpvotes() + 1);
        productRepository.save(product);
        return getProductResponse(product);
    }
}
