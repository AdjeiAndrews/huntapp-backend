package com.grok.hunt.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private String tagline;
    private String description;
    private String websiteUrl;
    private String thumbnailUrl;
}
