package com.skypro.recipes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipes.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.PostConstruct;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Long, Recipe> recipeMap = new HashMap<>();
    private Long counter = 0L;
    private final FileServiceRecipe fileServiceRecipe;

    public RecipeServiceImpl(FileServiceRecipe fileServiceRecipe) {
        this.fileServiceRecipe = fileServiceRecipe;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public static class RecipeObjectNotFoundException extends RuntimeException {
        RecipeObjectNotFoundException(String message) {
            super(message);
        }
    }

    @Override
    public Recipe add(Recipe recipe) {
        recipeMap.put(this.counter++, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public Recipe get(long id) {
        return recipeMap.get(id);
    }

    @Override
    public Recipe upDate(long id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            saveToFile();
            return recipe;
        }
        return null;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Recipe remove(long id) {
        return recipeMap.remove(id);
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(this.recipeMap.values());
    }

    @Override
    public Path creatReport(Recipe recipe) {
        return null;
    }

    @ResponseStatus
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileServiceRecipe.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RecipeObjectNotFoundException("Файл не удалось сохранить!");
        }
    }

    @ResponseStatus
    private void readFromFile() {
        try {
            String json = fileServiceRecipe.readFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RecipeObjectNotFoundException("Файлов для чтения нет!");
        }
    }
}
