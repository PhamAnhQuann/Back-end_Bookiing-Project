package com.CodeToDie.TestAlgorithms.DTO;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ProductDTO {
    private String productName;
    private Long productPrice;
    private String unit;
    private Long productSize;
    private Long discount;
    private List<ImageDTO> images;
}
