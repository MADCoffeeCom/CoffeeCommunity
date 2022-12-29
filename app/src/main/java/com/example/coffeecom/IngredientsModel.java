package com.example.coffeecom;

public class IngredientsModel {

    private String ingredientTitle;
    private String ingredientDesc;

    public IngredientsModel(String ingredientTitle, String ingredientDesc) {
        this.ingredientTitle = ingredientTitle;
        this.ingredientDesc = ingredientDesc;
    }

    public String getIngredientTitle() {
        return ingredientTitle;
    }

    public void setIngredientTitle(String ingredientTitle) {
        this.ingredientTitle = ingredientTitle;
    }

    public String getIngredientDesc() {
        return ingredientDesc;
    }

    public void setIngredientDesc(String ingredientDesc) {
        this.ingredientDesc = ingredientDesc;
    }
}
