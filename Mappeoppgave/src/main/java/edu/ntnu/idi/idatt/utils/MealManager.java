package edu.ntnu.idi.idatt.utils;

import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Ingredient;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class representing a meal manager.
 * Handles all interaction between Cookbook- and FoodStorage-class
 *
 * @author Julie Bao Thi Tran
 * @version 11.12
 * @since 27.11
 */
public class MealManager {
  private final FoodStorage foodStorage;
  private final Cookbook cookbook;

  /**
   * Constructor for MealManager-class.
   *
   * @param foodStorage The food storage containing all the available ingredients
   * @param cookbook The cookbook containing all the recipes
   */
  public MealManager(FoodStorage foodStorage, Cookbook cookbook) {
    this.foodStorage = foodStorage;
    this.cookbook = cookbook;
  }

  /**
   * Method for checking if the food storage contains the required amounts of ingredients to make a
   * specific recipe.
   *
   * @param nameOfRecipe The name of the specific recipe you want to use as reference
   * @return 'true' if the food storage contains the required amounts of all ingredients to make a
   *      specific recipe, 'false' if it does not
   */
  public boolean hasRequiredAmountOfIngredients(String nameOfRecipe) {
    Recipe foundRecipe = cookbook.findRecipe(nameOfRecipe);
    for (IngredientForRecipe ingredientInRecipe : foundRecipe.getIngredientsForRecipe()) {
      boolean ingredientAvailable = false;
      for (Ingredient ingredientInFoodStorage : foodStorage.getIngredients()) {
        if (ingredientInRecipe.getNameOfIngredientForRecipe()
            .equalsIgnoreCase(ingredientInFoodStorage.getName())
            && ingredientInFoodStorage.getAmount()
            >= ingredientInRecipe.getAmountOfIngredientForRecipe()) {
          ingredientAvailable = true;
          break;
        }
      }
      if (!ingredientAvailable) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method for using the ingredients in the food storage to make a recipe.
   * Uses findRecipe-method from cookbook to find the specific recipe
   *
   * @param nameOfRecipe The name of the specific recipe you want to use as reference
   * @return 'false' if you don't have the required ingredients available in the food storage to
   *      make the recipe, 'true' if the recipe is successfully made
   */
  public boolean useIngredientsToMakeRecipe(String nameOfRecipe) {
    Recipe foundRecipe = cookbook.findRecipe(nameOfRecipe);
    if (!hasRequiredAmountOfIngredients(nameOfRecipe)) {
      return false;
    } else {
      for (IngredientForRecipe ingredientInRecipe : foundRecipe.getIngredientsForRecipe()) {
        for (Ingredient ingredientInFoodStorage : foodStorage.getIngredients()) {
          if (ingredientInRecipe.getNameOfIngredientForRecipe()
              .equalsIgnoreCase(ingredientInFoodStorage.getName())) {
            ingredientInFoodStorage.setAmount(
                ingredientInFoodStorage.getAmount()
                    - ingredientInRecipe.getAmountOfIngredientForRecipe());
            break;
          }
        }
      }
    }
    return true;
  }

  /**
   * Method for suggesting recipes that can be made with the available ingredients in the food
   * storage.
   *
   * @return List of suggested recipes, ArrayList
   */
  public ArrayList<Recipe> suggestRecipes() {
    return cookbook.getRecipes().stream()
        .filter(recipe -> hasRequiredAmountOfIngredients(recipe.getNameOfRecipe()))
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
