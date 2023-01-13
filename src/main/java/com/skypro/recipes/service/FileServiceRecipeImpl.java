package com.skypro.recipes.service;

import com.skypro.recipes.exception.RecipeFileSavingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceRecipeImpl implements FileServiceRecipe {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.file}")
    private String dataFileName;

    @Value("${path.to.recipesTxt.file}")
    private String recipesTxtFilePath;

    @Value("${name.of.recipesTxt.file}")
    private String recipeTxtFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean saveRecipesToTxtFile(String txt) {
        try {
            cleanRecipeTxtFile();
            Files.writeString(Path.of(recipesTxtFilePath, recipeTxtFileName), txt);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void cleanRecipeTxtFile() {
        try {
            Path path = Path.of(recipesTxtFilePath, recipeTxtFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RecipeFileSavingException("No file");
        }
    }

    @Override
    public File getDateFile() {
        return new File(dataFilePath + "/" + dataFileName);
    }

    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);
    }

    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(dataFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getTxtFile() {
        if (Files.exists(Path.of(recipesTxtFilePath, recipeTxtFileName))) {
            return new File(recipesTxtFilePath + "/" + recipeTxtFileName);
        }
        throw new NotFoundException("No txt file");
    }
}
