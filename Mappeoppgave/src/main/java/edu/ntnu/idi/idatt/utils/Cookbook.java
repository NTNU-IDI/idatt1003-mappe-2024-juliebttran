package edu.ntnu.idi.idatt.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class representing a cookbook.
 *
 * @author Julie Bao Thi Tran
 * @version 10.12
 * @since 25.11
 */
public class Cookbook {
  // Final to prevent external code from modifying the original list directly, ArrayList
  private final ArrayList<Recipe> recipes;

  /**
   * Constructor for Cookbook-class.
   *
   * @param recipes List of the recipes in the cookbook, ArrayList
   */
  public Cookbook(ArrayList<Recipe> recipes) {
    this.recipes = recipes;
  }

  /**
   * get-method for recipes.
   *
   * @return List of recipes in the cookbook, ArrayList
   */
  public ArrayList<Recipe> getRecipes() {
    return recipes;
  }

  /**
   * Method for adding a new recipe to the cookbook.
   *
   * @param newRecipe The recipe you want to add, of type Recipe
   */
  public void addNewRecipe(Recipe newRecipe) {
    recipes.add(newRecipe);
  }

  /**
   * Method for finding a specific recipe.
   *
   * @param nameOfRecipe The name of the specific recipe you want to find in the cookbook, String
   * @return The recipe if it exists in the cookbook, of type Recipe
   *      'null' if it does not exist
   */
  public Recipe findRecipe(String nameOfRecipe) {
    return recipes.stream()
        .filter(recipe -> recipe.getNameOfRecipe().equalsIgnoreCase(nameOfRecipe))
        .findFirst()
        .orElse(null);
  }

  /**
   * Method for adding an ingredient to a specific recipe.
   *
   * @param nameOfRecipe  The name of the recipe you want to add an ingredient to, String
   * @param newIngredient The ingredient you want to add to this specific recipe, of type
   *                      IngredientForRecipe
   */
  public void addIngredientToRecipe(String nameOfRecipe, IngredientForRecipe newIngredient) {
    Recipe foundRecipe = findRecipe(nameOfRecipe);
    foundRecipe.addIngredient(newIngredient);
  }

  /**
   * toString-method for Cookbook-class.
   *
   * @return String of information about all the recipes in the cookbook
   */
  public String toString() {
    return recipes.stream()
        .map(Recipe::toString)
        .collect(Collectors.joining("\n"));
  }
}