package com.skypro.recipes.service;

import java.io.File;

public interface FileServiceIngredient {

    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();
}
