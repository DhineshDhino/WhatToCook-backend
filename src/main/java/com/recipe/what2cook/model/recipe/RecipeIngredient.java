package com.recipe.what2cook.model.recipe;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeIngredient implements Serializable {
    private String name;
    private String type; // Keeping 'type' as it was present in your provided class structure, even if not always populated in JSON.
    private String tamilName;
    private String tanglishName;
    private String unit;
    private String quantity; // Changed to String to accommodate values like "to taste" or numerical strings.
}