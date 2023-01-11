package com.skypro.recipes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceIngredientImpl implements FileServiceIngredient {
    @Value("${path.to.ingredient.file}")
    private String ingredientFilePath;
    @Value("${name.of.ingredient.file}")
    private String ingredientFileName;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public static class IngredientFileSavingException extends RuntimeException {

        public IngredientFileSavingException(String message) {

            super(message);
        }
    }

    @Override
    public boolean saveToFile(String json) {
        try {
            Files.writeString(Path.of(ingredientFilePath, ingredientFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(ingredientFilePath, ingredientFileName));
        } catch (IOException e) {
            throw new IngredientFileSavingException("Файлов для чтения нет!");
        }
    }

    @Override
    public File getDataFile() {
        return new File(ingredientFilePath + "/"+ ingredientFileName);
    }

    public boolean cleanDataFile() {
        Path path = Path.of(ingredientFilePath, ingredientFileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}