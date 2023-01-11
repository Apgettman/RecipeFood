package com.skypro.recipes.controller;

import com.skypro.recipes.service.FileServiceRecipeImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/recipe")
public class FileControllerRecipe {

    private final FileServiceRecipeImpl fileServiceRecipeImpl;

    public FileControllerRecipe(FileServiceRecipeImpl fileServiceRecipeImpl) {
        this.fileServiceRecipeImpl = fileServiceRecipeImpl;
    }
    @GetMapping("recipeExportAsTxt")
    @Operation(summary = "Сохранение файла в формате txt")
    public ResponseEntity<InputStreamResource> downloadRecipesAsTxt() throws FileNotFoundException {
        File file = fileServiceRecipeImpl.getTxtFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().
                    contentLength(file.length()).
                    contentType(MediaType.TEXT_PLAIN).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.txt\"").
                    body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/export")
    @Operation(summary = "Экспорт файла", description = "Скачивания файла в формате json")
    public ResponseEntity<InputStreamResource> downloadFileRecipe () throws FileNotFoundException {
        File file = fileServiceRecipeImpl.getDataFile();
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName = \"data.json\"")
                    .body(inputStreamResource);
        }else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Импорт файла", description = "Загрузка файла в формате json")
    public ResponseEntity<Void> uploadFileRecipe(@RequestParam MultipartFile file) {
        fileServiceRecipeImpl.cleanDataFile();
        File fileRecipe = fileServiceRecipeImpl.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(fileRecipe)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}