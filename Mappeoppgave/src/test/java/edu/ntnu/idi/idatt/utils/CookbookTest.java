package edu.ntnu.idi.idatt.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CookbookTest {
  private Recipe butter;
  private Recipe cocoa;
  private Cookbook cookbook;

  @BeforeEach
  void setRecipe() {
    IngredientForRecipe heavyCream = new IngredientForRecipe("Heavy cream", 0.4, "L");
    IngredientForRecipe salt = new IngredientForRecipe("Salt", 0.001, "L");
    ArrayList<IngredientForRecipe> ingredientsForButter = new ArrayList<>();
    ingredientsForButter.add(heavyCream);
    ingredientsForButter.add(salt);
    butter = new Recipe(
        "Butter",
        "An easy homemade butter recipe",
        "1. Whip the butter until it separates 2. Once the butter and buttermilk are separated, rinse the butter in cold water until the water runs clean 3. To finish, add salt or seasonings to taste",
        ingredientsForButter,
        1);

    IngredientForRecipe milk = new IngredientForRecipe("Milk", 0.25, "L");
    IngredientForRecipe cocoaPowder = new IngredientForRecipe("Cocoa powder", 0.003, "Kg");
    ArrayList<IngredientForRecipe> ingredientsForCocoa = new ArrayList<>();
    ingredientsForCocoa.add(milk);
    ingredientsForCocoa.add(cocoaPowder);
    cocoa = new Recipe(
        "Cocoa",
        "Delicious, homemade cocoa!",
        "1. Heat up milk 2. Pour the cocoa powder into a cup 3. Pour the hot milk in the cup",
        ingredientsForCocoa,
        1);

    ArrayList<Recipe> recipes = new ArrayList<>();
    recipes.add(butter);
    cookbook = new Cookbook(recipes);
  }

  @Test
  void testGetRecipe() {
    assertEquals(1, cookbook.getRecipes().size());
    assertTrue(cookbook.getRecipes().contains(butter));
    assertFalse(cookbook.getRecipes().contains(cocoa));
  }

  @Test
  void testFindRecipe() {
    assertEquals(butter, cookbook.findRecipe("Butter"));

    assertNull(cookbook.findRecipe("Cocoa"));
  }
}
