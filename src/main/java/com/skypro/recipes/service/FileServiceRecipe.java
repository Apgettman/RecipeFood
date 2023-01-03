package com.skypro.recipes.service;

public interface FileServiceRecipe {

    boolean saveToFile(String json);

    String readFromFile();
}
