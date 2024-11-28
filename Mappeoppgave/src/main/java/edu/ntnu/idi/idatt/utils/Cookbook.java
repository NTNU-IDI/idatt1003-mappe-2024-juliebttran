package edu.ntnu.idi.idatt.utils;

import java.util.ArrayList;

/**
 * Class representing a cookbook.
 *
 * @author Julie Bao Thi Tran
 * @version 27.11
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
    for (Recipe recipe : recipes) {
      if (recipe.getNameOfRecipe().equalsIgnoreCase(nameOfRecipe)) {
        return recipe;
      }
    }
    return null;
  }

  /**
   * Method for adding an ingredient to a specific recipe.
   *
   * @param nameOfRecipe  The name of the recipe you want to add an ingredient to, String
   * @param newIngredient The ingredient you want to add to this specific recipe, of type
   *                      IngredientForRecipe
   * @return 'false' if the specific recipe does not exist in the cookbook, 'true' if the recipe
   *     exists and ingredient is added
   */
  public boolean addIngredientToRecipe(String nameOfRecipe, IngredientForRecipe newIngredient) {
    Recipe foundRecipe = findRecipe(nameOfRecipe);
    if (foundRecipe == null) {
      return false;
    } else {
      foundRecipe.addIngredient(newIngredient);
      return true;
    }
  }
}