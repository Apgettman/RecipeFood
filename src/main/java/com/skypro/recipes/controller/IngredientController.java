package com.skypro.recipes.controller;

import com.skypro.recipes.model.Ingredient;
import com.skypro.recipes.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "Операции удаления, создания, получения и редактирования ингредиентов")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {

        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Операция для создания ингредиента")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {

        return ingredientService.add(ingredient);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Операция для получения ингредиента")
    public Ingredient getIngredient(@PathVariable("id") long id) {

        return ingredientService.get(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Операция для редактирования ингредиента")
    public Ingredient updateIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.upDate(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Операция для удаления ингредиента")
    public Ingredient deleteIngredient(@PathVariable("id") long id) {
        return ingredientService.remove(id);
    }
}
