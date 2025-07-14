package com.recipe.what2cook.service.recipe;

import com.recipe.what2cook.dto.RecipeDTO;
import com.recipe.what2cook.exception.RecipeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeService {

    RecipeDTO saveRecipe(RecipeDTO recipeDTO);
    RecipeDTO getRecipe(String name) throws RecipeNotFoundException;
    RecipeDTO updateRecipe(RecipeDTO recipeDTO);

    List<RecipeDTO> getAllRecipes();
}
