package edu.ntnu.idi.idatt.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Ingredient;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MealManagerTest {
  private Recipe butter;
  private Recipe cocoa;
  private MealManager mealManager;

  @BeforeEach
  public void setUp() {
    Ingredient heavyCreamFS = new Ingredient("Heavy cream", 1, "L", LocalDate.of(2025, 3, 1), 83);
    Ingredient saltFS = new Ingredient("Salt", 1, "Kg", LocalDate.of(2025, 3, 1), 83);
    Ingredient milkFS = new Ingredient("Milk", 1.5, "L", LocalDate.of(2025, 3, 1), 24.5);
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(heavyCreamFS);
    ingredients.add(saltFS);
    ingredients.add(milkFS);
    FoodStorage foodStorage = new FoodStorage(ingredients);

    IngredientForRecipe heavyCreamCB = new IngredientForRecipe("Heavy cream", 0.4, "L");
    IngredientForRecipe saltCB = new IngredientForRecipe("Salt", 0.001, "L");
    ArrayList<IngredientForRecipe> ingredientsForButter = new ArrayList<>();
    ingredientsForButter.add(heavyCreamCB);
    ingredientsForButter.add(saltCB);
    butter = new Recipe(
        "Butter",
        "An easy homemade butter recipe",
        "1. Whip the butter until it separates 2. Once the butter and buttermilk are separated, rinse the butter in cold water until the water runs clean 3. To finish, add salt or seasonings to taste",
        ingredientsForButter,
        1);

    IngredientForRecipe milkCB = new IngredientForRecipe("Milk", 0.25, "L");
    IngredientForRecipe cocoaPowderCB = new IngredientForRecipe("Cocoa powder", 0.003, "Kg");
    ArrayList<IngredientForRecipe> ingredientsForCocoa = new ArrayList<>();
    ingredientsForCocoa.add(milkCB);
    ingredientsForCocoa.add(cocoaPowderCB);
    cocoa = new Recipe(
        "Cocoa",
        "Delicious, homemade cocoa!",
        "1. Heat up milk 2. Pour the cocoa powder into a cup 3. Pour the hot milk in the cup",
        ingredientsForCocoa,
        1);

    ArrayList<Recipe> recipes = new ArrayList<>();
    recipes.add(butter);
    recipes.add(cocoa);
    Cookbook cookbook = new Cookbook(recipes);

    mealManager = new MealManager(foodStorage, cookbook);
  }

  @Test
  void testHasRequiredAmountOfIngredients() {
    assertFalse(mealManager.hasRequiredAmountOfIngredients("Cocoa"));
    assertTrue(mealManager.hasRequiredAmountOfIngredients("Butter"));
  }

  @Test
  void testUseIngredientsToMakeRecipe() {
    assertFalse(mealManager.useIngredientsToMakeRecipe("Cocoa"));
    assertTrue(mealManager.useIngredientsToMakeRecipe("Butter"));
  }

  @Test
  void testSuggestRecipes() {
    assertTrue(mealManager.suggestRecipes().contains(butter));
    assertFalse(mealManager.suggestRecipes().contains(cocoa));
  }
}
