package com.skypro.recipes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipes.exception.IngredientObjectNotFoundException;
import com.skypro.recipes.model.Ingredient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private Long counter = 0L;
    private final FileServiceIngredient fileServiceIngredient;

    public IngredientServiceImpl(FileServiceIngredient fileServiceIngredient) {
        this.fileServiceIngredient = fileServiceIngredient;
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        ingredientMap.put(this.counter++, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public Ingredient get(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient upDate(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            saveToFile();
            return ingredientMap.put(id, ingredient);
        }
        return null;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Ingredient remove(long id) {
        return ingredientMap.remove(id);
    }

    @ResponseStatus
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileServiceIngredient.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IngredientObjectNotFoundException("Файл не удалось сохранить!");
        }
    }

    @ResponseStatus
    private void readFromFile() {
        String json = fileServiceIngredient.readFromFile();
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IngredientObjectNotFoundException("Файлов для чтения нет!");
        }
    }
}


