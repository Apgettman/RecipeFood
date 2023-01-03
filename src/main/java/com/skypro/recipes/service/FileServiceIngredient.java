package com.skypro.recipes.service;

public interface FileServiceIngredient {

    boolean saveToFile(String json);

    String readFromFile();

}
