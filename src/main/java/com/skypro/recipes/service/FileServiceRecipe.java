package com.skypro.recipes.service;

import java.io.File;
import java.nio.file.Path;

public interface FileServiceRecipe {

    boolean saveToFile(String json);

    boolean saveRecipesToTxtFile(String txt);

    String readFromFile();

    File getDateFile();

    File getDataFile();

    Path createTempFile(String suffix);

    boolean cleanDataFile();

    File getTxtFile();
}
