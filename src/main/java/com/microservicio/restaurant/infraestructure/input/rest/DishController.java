package com.microservicio.restaurant.infraestructure.input.rest;

import com.microservicio.restaurant.application.dto.DishRequest;
import com.microservicio.restaurant.application.dto.DishResponse;
import com.microservicio.restaurant.application.dto.UpdateDishRequest;
import com.microservicio.restaurant.application.dto.UpdateDishStatusRequest;
import com.microservicio.restaurant.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "DishEndPoint")
@RestController
@RequestMapping("/platos")
@RequiredArgsConstructor
public class DishController {
    private final IDishHandler dishHandler;

    @Operation(summary = "createDish")
    @PostMapping
    public DishResponse saveDish(@RequestBody DishRequest dishRequest) {
        return dishHandler.saveDish(dishRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDishById(@PathVariable Long id) {
        Optional<DishResponse> dishResponse = dishHandler.findDishById(id);
        return dishResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "updateDish")
    @PutMapping("/{id}")
    public ResponseEntity<DishResponse> updateDishDescriptionAndPrice(
            @PathVariable Long id,
            @RequestBody UpdateDishRequest updateDishRequest) {
        DishResponse response = dishHandler.updateDishDescriptionAndPrice(id, updateDishRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DishResponse> updateDishStatus(@PathVariable Long id, @RequestBody UpdateDishStatusRequest updateDishStatusRequest) {
        DishResponse updatedDish = dishHandler.updateDishStatus(id, updateDishStatusRequest);
        return ResponseEntity.ok(updatedDish);
    }

    @GetMapping("/restaurante/{idRestaurant}")
    public ResponseEntity<List<DishResponse>> getDishesByRestaurant(@PathVariable Long idRestaurant) {
        List<DishResponse> dishes = dishHandler.getDishesByRestaurant(idRestaurant);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
}