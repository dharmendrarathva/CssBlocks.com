package com.example.Blocks.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String code;

    // NEW FIELDS
    private String htmlCode;
    private String cssCode;
    private String jsCode;
}
