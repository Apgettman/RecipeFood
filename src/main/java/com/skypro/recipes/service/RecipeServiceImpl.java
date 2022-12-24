package com.skypro.recipes.service;

import com.skypro.recipes.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Long, Recipe> recipeMap = new HashMap<>();
    private Long counter = 0L;

    @Override
    public Recipe add(Recipe recipe) {
        recipeMap.put(this.counter++, recipe);
        return recipe;
    }

    @Override
    public Recipe get(long id) {
        return recipeMap.get(id);
    }
}
