package com.recipe.what2cook.entity;

import com.recipe.what2cook.model.recipe.RecipeIngredient;
import com.recipe.what2cook.model.recipe.Time;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@Builder @AllArgsConstructor
@Document(collection = "CorrectedRecipe") // Collection name as specified
public class RecipeEntity implements Serializable {
    @Id
    @Field("_id")
    private String id;
    private String name; // name of the recipe
    private String variant; // like dry or gravy or steamed or fried
    private String owner; // who's recipe
    private List<RecipeIngredient> ingredients;
    private List<RecipeIngredient> secretIngredients;
    private List<RecipeIngredient> optionalIngredients; // Ensures this is a List<RecipeIngredient> for consistency
    private String preparation; // if any prep is required before actual steps
    private String cookingSteps;
    private String importantCookingSteps;
    private List<RecipeIngredient> garnish; // Ensures this is a List<RecipeIngredient> for consistency
    private List<String> accompany; // like chutney
    private Time time;
    private String servings; // Kept as String for flexibility with descriptive text or numbers
    private String cuisine;
    private String suitsFor; // breakfast or lunch or evening snack or dinner or mocktail or condiment or sides
    // The '_class' field is typically handled by Spring Data MongoDB for internal type mapping
    // and usually doesn't need to be explicitly declared in the entity unless specific polymorphism is required.
}