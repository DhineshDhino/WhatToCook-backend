package com.recipe.what2cook.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(collection = "ingredient")
public class IngredientEntity {

    @Id
    @Field("_id")
    private String id;
    private String name;
    private String type;
    private String tamilName;
    private String tanglishName;
}
