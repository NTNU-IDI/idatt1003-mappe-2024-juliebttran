package edu.ntnu.idi.idatt.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class representing a recipe.
 *
 * @author Julie Bao Thi Tran
 * @version 10.12
 * @since 25.11
 */
public class Recipe {
  // Final because the name of the recipe will never change, String because name is a text
  private final String nameOfRecipe;
  // Final because the description of the dish most likely won't change, String since it's a text
  private final String recipeDescription;
  // Final because the instructions of the recipe most likely won't change, String since it's a text
  private final String recipeInstructions;
  // Final to prevent external code from modifying the original list directly, ArrayList
  private final ArrayList<IngredientForRecipe> ingredientsForRecipe;
  /* Final because the servings won't change, int since it represents the fixed integer of servings
   for the recipe */
  private final int servings;

  /**
   * Constructor for Recipe-class.
   *
   * @param nameOfRecipe         Name of the recipe, String, cannot be blank
   * @param recipeDescription    Short description of the dish, String, cannot be blank
   * @param recipeInstructions   Instructions on how to make the dish, String, cannot be blank
   * @param ingredientsForRecipe List of ingredients needed to make the dish, ArrayList, starts as
   *                             empty, but ingredients will be added by the client
   * @param servings             Number of people the recipe is designed for, int, must be positive
   * @throws IllegalArgumentException If any parameter is invalid
   */
  public Recipe(String nameOfRecipe, String recipeDescription, String recipeInstructions,
                ArrayList<IngredientForRecipe> ingredientsForRecipe, int servings)
      throws IllegalArgumentException {
    if (nameOfRecipe.isBlank()) {
      throw new IllegalArgumentException("Recipe name cannot be blank, please try again!");
    }
    if (recipeDescription.isBlank()) {
      throw new IllegalArgumentException("Recipe description cannot be blank, please try again!");
    }
    if (recipeInstructions.isBlank()) {
      throw new IllegalArgumentException("Recipe instructions cannot be blank, please try again!");
    }
    if (servings <= 0) {
      throw new IllegalArgumentException(
          "Servings must be a positive number greater than zero, please try again!");
    }

    this.nameOfRecipe = nameOfRecipe;
    this.recipeDescription = recipeDescription;
    this.recipeInstructions = recipeInstructions;
    this.ingredientsForRecipe = ingredientsForRecipe;
    this.servings = servings;
  }

  /**
   * get-method for nameOfRecipe.
   *
   * @return The name of the recipe, String
   */
  public String getNameOfRecipe() {
    return nameOfRecipe;
  }

  /**
   * get-method for recipeDescription.
   *
   * @return A short description of the dish, String
   */
  public String getRecipeDescription() {
    return recipeDescription;
  }

  /**
   * get-method for recipeInstructions.
   *
   * @return The instructions on how to make the dish, String
   */
  public String getRecipeInstructions() {
    return recipeInstructions;
  }

  /**
   * get-method for ingredientsForRecipe.
   *
   * @return List of ingredients needed for to make the dish, ArrayList
   */
  public ArrayList<IngredientForRecipe> getIngredientsForRecipe() {
    return ingredientsForRecipe;
  }

  /**
   * Method for adding an Ingredient to an existing recipe.
   *
   * @param newIngredient The ingredient you want to add to the recipe, Ingredient
   */
  public void addIngredient(IngredientForRecipe newIngredient) {
    ingredientsForRecipe.add(newIngredient);
  }

  public IngredientForRecipe findIngredientForRecipe(String nameOfIngredient) {
    return ingredientsForRecipe.stream()
        .filter(ingredient -> ingredient.getNameOfIngredientForRecipe().equalsIgnoreCase(nameOfIngredient))
        .findFirst()
        .orElse(null);
  }

  /**
   * get-method for serving.
   *
   * @return Number of people the recipe is designed for, int
   */
  public int getServings() {
    return servings;
  }

  /**
   * toString-method for Recipe-class.
   *
   * @return String of information about the Recipe
   */
  public String toString() {
    String ingredientsForRecipeString = ingredientsForRecipe.stream()
        .map(IngredientForRecipe::toString)
        .collect(Collectors.joining("\n"));

    return "Name of recipe: " + getNameOfRecipe()
        + "\nDescription: " + getRecipeDescription()
        + "\nInstructions: " + getRecipeInstructions()
        + "\nIngredients: \n" + ingredientsForRecipeString
        + "\nServings: " + getServings() + "\n";
  }
}
