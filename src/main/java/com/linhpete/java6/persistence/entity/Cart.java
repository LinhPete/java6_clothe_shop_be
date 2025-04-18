package com.linhpete.java6.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id") // Khóa chính cũng là khóa ngoại
    User user;
    Double total;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<CartItem> items;
}
