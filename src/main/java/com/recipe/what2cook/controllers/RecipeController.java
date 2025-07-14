package com.recipe.what2cook.controllers;
import com.recipe.what2cook.dto.RecipeDTO;
import com.recipe.what2cook.exception.RecipeNotFoundException;
import com.recipe.what2cook.service.recipe.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeServiceImpl recipeService;
    @Autowired
    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(path = "/recipe/{name}" , consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRecipe(@PathVariable String name) {
        RecipeDTO recipeDTO = null;
        try {
            recipeDTO = recipeService.getRecipe(name);
        } catch (RecipeNotFoundException e) {
            return ResponseEntity.status(404).body("Recipe not found");
        }
        return ResponseEntity.ok(recipeDTO);
    }
    @PostMapping(path = "/recipe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDTO> saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO recipe = recipeService.saveRecipe(recipeDTO);
        return ResponseEntity.ok(recipe);
    }


    @GetMapping(path = "/allRecipe",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        List<RecipeDTO> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

}

