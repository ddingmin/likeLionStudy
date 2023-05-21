package com.example.likelionstudy.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String brand;
    private String name;
    private Integer price;
    private Integer stockQuantity;

}
