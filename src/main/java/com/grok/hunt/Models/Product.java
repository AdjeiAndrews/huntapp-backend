package com.grok.hunt.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;                      // Product name
    private String tagline;                   // Short description or tagline
    private String description;               // Full product description
    private String websiteUrl;                // Link to the product website
    private String thumbnailUrl;              // Image preview of the product
    private LocalDateTime createdAt;          // When the product was posted

    private int upvotes = 0;                  // Number of upvotes

    @ManyToOne
    private Users postedBy;                    // User who posted the product

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<Comment> comments = new ArrayList<>();  // Comments on product


}

;