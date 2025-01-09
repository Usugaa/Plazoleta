package com.microservicio.restaurant.infraestructure.input.client;

import com.microservicio.restaurant.application.dto.UserDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "plazoleta", url = "http://localhost:8090/usuario")
public interface UserFeignClient {

    @GetMapping(value = "/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

}
