package com.skypro.recipes.service;

import com.skypro.recipes.model.Recipe;

public interface RecipeService {
    Recipe add(Recipe recipe);
    Recipe get(long id);
}
