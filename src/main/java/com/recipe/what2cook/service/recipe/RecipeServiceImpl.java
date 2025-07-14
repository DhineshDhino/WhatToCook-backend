package com.recipe.what2cook.service.recipe;

import com.recipe.what2cook.dto.RecipeDTO;
import com.recipe.what2cook.repository.RecipeRepository;
import com.recipe.what2cook.entity.RecipeEntity;
import com.recipe.what2cook.exception.RecipeNotFoundException;
import com.recipe.what2cook.mapper.RecipeEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeEntityMapper mapper;
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository,RecipeEntityMapper recipeEntityMapper){
        this.recipeRepository=recipeRepository;
        this.mapper=recipeEntityMapper;
    }

    @Cacheable(value = "recipes", key = "#name")
    public RecipeDTO getRecipe(String name) throws RecipeNotFoundException {
        log.info("Fetching recipe with name: {}", name);
        RecipeEntity recipe=recipeRepository.findByName(name);
        if(recipe==null){
            log.error("Recipe not found with name: {}", name);
            throw new RecipeNotFoundException(name);
        }
        return mapper.toDto(recipe);
    }
    @CachePut(value = "recipes", key = "#recipeDTO.name")
    public RecipeDTO saveRecipe(RecipeDTO recipeDTO){
        log.info("Saving recipe: {}", recipeDTO.getName());
        RecipeEntity recipeEntity=mapper.toEntity(recipeDTO);
        RecipeEntity savedRecipe=recipeRepository.save(recipeEntity);
        log.info("Recipe saved with ID: {}", savedRecipe.getId());
        return mapper.toDto(savedRecipe);
    }
    @CachePut(value = "recipes", key = "#recipeDTO.name")
    public RecipeDTO updateRecipe(RecipeDTO recipeDTO){
        RecipeEntity recipeEntity = recipeRepository.findByName(recipeDTO.getName());
        RecipeEntity toBeUpdatedEntity = mapper.toEntity(recipeDTO);
        toBeUpdatedEntity.setId(recipeEntity.getId());
        RecipeEntity updatedRecipe = recipeRepository.save(toBeUpdatedEntity);
        log.info("Recipe updated with ID: {}", updatedRecipe.getId());
        return mapper.toDto(updatedRecipe);
    }

    @Cacheable(value = "allRecipes")
    public List<RecipeDTO> getAllRecipes(){
        log.info("Fetching all recipes");
        List<RecipeEntity> all=recipeRepository.findAll();
        return all.stream().map(mapper::toDto).toList();
    }
    @CacheEvict(value = "recipes", key = "#name")
    public void deleteRecipe(String name){
        log.info("Deleting recipe with name: {}", name);
        recipeRepository.deleteByName(name);
    }
}