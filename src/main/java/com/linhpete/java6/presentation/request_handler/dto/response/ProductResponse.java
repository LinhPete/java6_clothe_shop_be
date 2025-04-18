package com.linhpete.java6.presentation.request_handler.dto.response;

import com.linhpete.java6.persistence.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Long id;
    String name;
    String image;
    int quantity;
    String description;
    Float price;
    Set<Category> categories;
}
