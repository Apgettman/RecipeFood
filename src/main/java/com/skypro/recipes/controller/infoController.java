package com.skypro.recipes.controller;

import com.skypro.recipes.record.infoRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class infoController {

    @GetMapping
    public String index() {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public infoRecord info() {
        return new infoRecord("Alexandr Getmanskii", "Recipes",
                LocalDate.of(2022, 12, 14), "test description");
    }
}
