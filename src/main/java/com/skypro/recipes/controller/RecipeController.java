package com.skypro.recipes.controller;

import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
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
    public Recipe getRecipe(@PathVariable("id") long id) {

        return recipeService.get(id);
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {

        return recipeService.add(recipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.upDate(id, recipe);
    }

    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }
}
