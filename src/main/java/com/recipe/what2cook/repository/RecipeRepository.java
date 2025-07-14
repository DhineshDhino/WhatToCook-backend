package com.recipe.what2cook.repository;

import com.recipe.what2cook.entity.RecipeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<RecipeEntity, String> {

    RecipeEntity findByName(String name);
    List<RecipeEntity> findAll();
    void deleteByName(String name);
}
