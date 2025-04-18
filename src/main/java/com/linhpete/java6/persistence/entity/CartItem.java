package com.linhpete.java6.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_Id", nullable = false)
    Cart cart;
    @Column(name = "added-date")
    LocalDate addedDate;
    Integer quantity;
    Double subtotal;
}
