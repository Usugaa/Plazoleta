package com.microservicio.restaurant.infraestructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "platos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int price;
    private String urlImage;
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id")
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private CategoryEntity category;

}