package com.microservicio.restaurant.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequest {

    @NotBlank(message = "El nombre del restaurante no puede estar vacío")
    @Pattern(regexp = "^(?!\\d+$)[\\w\\s]+$", message = "El nombre del restaurante no puede ser solo números")
    private String nameRestaurant;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String address;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "El teléfono debe contener solo números y puede contener el símbolo '+'")
    private String phone;

    private String urlLogo;

    @NotBlank(message = "El NIT no puede estar vacío")
    @Pattern(regexp = "^\\d+$", message = "El NIT debe contener solo números")
    private String nit;

    @NotBlank(message = "El id del usuario propietario no puede estar vacío")
    private Long ownerId; // Nuevo campo para el id del usuario propietario
}
