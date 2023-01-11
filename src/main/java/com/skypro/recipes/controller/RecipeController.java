package com.skypro.recipes.controller;

import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Операции удаления, создания, получения и редактирования рецептов")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAll() {

        return this.recipeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Операция для получения рецепта")
    public Recipe getRecipe(@PathVariable("id") long id) {

        return recipeService.get(id);
    }

    @PostMapping
    @Operation(summary = "Операция для создания рецепта")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getTitle())) {
            return ResponseEntity.badRequest().body("Название рецепта не может быть пустым");
        }
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Операция для редактирования рецепта")
    public Recipe updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.upDate(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Операция для удаления рецепта")
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }
}
