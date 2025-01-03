package com.microservicio.restaurant.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponse {

    private String nameRestaurant;

    private String address;

    private String phone;

    private String urlLogo;

    private String nit;

    private Long ownerId;

}