package com.microservicio.restaurant.infraestructure.input.rest;

import com.microservicio.restaurant.application.dto.DishRequest;
import com.microservicio.restaurant.application.dto.DishResponse;
import com.microservicio.restaurant.application.handler.IDishHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platos")
@RequiredArgsConstructor
public class DishController {
    private final IDishHandler dishHandler;

    @PostMapping
    public DishResponse saveDish(@RequestBody DishRequest dishRequest) {
        return dishHandler.saveDish(dishRequest);
    }

    @PutMapping("/{id}")
    public DishResponse updateDishDescriptionAndPrice(@PathVariable Long id,
                                                      @RequestParam String description,
                                                      @RequestParam int price) {
        return dishHandler.updateDishDescriptionAndPrice(id, description, price);
    }
}