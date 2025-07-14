package com.recipe.what2cook.model.recipe;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Time implements Serializable {
    private String actualCook;
    private String preparation;
    private String timeBtwPrepAndCook;
}
