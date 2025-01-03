package com.microservicio.restaurant.domain.usecase;

import com.microservicio.restaurant.domain.api.IDishServicePort;
import com.microservicio.restaurant.domain.constants.CategoryConstants;
import com.microservicio.restaurant.domain.model.Category;
import com.microservicio.restaurant.domain.model.Dish;
import com.microservicio.restaurant.domain.spi.IDishPersistencePort;

import java.util.Optional;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public Dish saveDish(Dish dish) {
        //Validar categoria
            Category category = validateCategory(dish.getIdCategory());
            dish.setIdCategory(category.getId());

            return dishPersistencePort.saveDish(dish);
    }

    @Override
    public Dish updateDish(Long id, String description, int price) {
        Optional<Dish> optionalDish = dishPersistencePort.findById(id);

        if (optionalDish.isPresent()) {
            Dish dish = optionalDish.get();
            dish.setDescription(description);
            dish.setPrice(price);

            return dishPersistencePort.updateDish(dish);
        }
        return null;
    }

    private Category validateCategory(Long categoryId) {
        if (categoryId.equals(CategoryConstants.APPETIZER)) {
            return new Category(CategoryConstants.APPETIZER, CategoryConstants.APPETIZER_NAME, CategoryConstants.APPETIZER_DESCRIPTION);
        } else if (categoryId.equals(CategoryConstants.COURSE)) {
            return new Category(CategoryConstants.COURSE, CategoryConstants.MAIN_COURSE_NAME, CategoryConstants.MAIN_COURSE_DESCRIPTION);
        } else if (categoryId.equals(CategoryConstants.DESSERT)) {
            return new Category(CategoryConstants.DESSERT, CategoryConstants.DESSERT_NAME, CategoryConstants.DESSERT_DESCRIPTION);
        } else {
            return null; // Aquí no se maneja la excepción, se devuelve null si la categoría no es válida
        }
    }
}
