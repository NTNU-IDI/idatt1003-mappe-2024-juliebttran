package edu.ntnu.idi.idatt.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeTest {
  private IngredientForRecipe heavyCream;
  private IngredientForRecipe salt;
  private IngredientForRecipe himalayaSalt;
  private Recipe butter;

  @Test
  void testInvalidName() {
    ArrayList<IngredientForRecipe> ingredients = new ArrayList<>();
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Recipe("", "An easy homemade butter recipe", "1. Whip the butter until it separates 2. Once the butter and buttermilk are separated, rinse the butter in cold water until the water runs clean 3. To finish, add salt or seasonings to taste", ingredients, 1));
    assertEquals("Recipe name cannot be blank, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidDescription() {
    ArrayList<IngredientForRecipe> ingredients = new ArrayList<>();
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Recipe("Butter", "", "1. Whip the butter until it separates 2. Once the butter and buttermilk are separated, rinse the butter in cold water until the water runs clean 3. To finish, add salt or seasonings to taste", ingredients, 1));
    assertEquals("Recipe description cannot be blank, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidInstructions() {
    ArrayList<IngredientForRecipe> ingredients = new ArrayList<>();
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Recipe("Butter", "An easy homemade butter recipe", "", ingredients, 1));
    assertEquals("Recipe instructions cannot be blank, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidServings() {
    ArrayList<IngredientForRecipe> ingredients = new ArrayList<>();
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Recipe("Butter", "An easy homemade butter recipe", "1. Whip the butter until it separates 2. Once the butter and buttermilk are separated, rinse the butter in cold water until the water runs clean 3. To finish, add salt or seasonings to taste", ingredients, -1));
    assertEquals("Servings must be a positive number greater than zero, please try again!", exception.getMessage());
  }

  @BeforeEach
  void setUp() {
    heavyCream = new IngredientForRecipe("Heavy cream", 0.4, "L");
    salt = new IngredientForRecipe("Salt", 0.005, "L");
    himalayaSalt = new IngredientForRecipe("Himalaya salt", 0.001, "L");
    ArrayList<IngredientForRecipe> ingredients = new ArrayList<>();
    ingredients.add(heavyCream);
    ingredients.add(salt);
    butter = new Recipe(
        "Butter",
        "An easy homemade butter recipe",
        "1. Whip the butter until it separates 2. Once the butter and buttermilk are separated, rinse the butter in cold water until the water runs clean 3. To finish, add salt or seasonings to taste",
        ingredients,
        1);
  }

  @Test
  void testRecipe() {
    assertEquals("Butter", butter.getNameOfRecipe());
    assertEquals("An easy homemade butter recipe", butter.getRecipeDescription());
    assertEquals("1. Whip the butter until it separates 2. Once the butter and buttermilk are separated, rinse the butter in cold water until the water runs clean 3. To finish, add salt or seasonings to taste", butter.getRecipeInstructions());
    assertEquals(1, butter.getServings());
  }

  @Test
  void testGetIngredientsForRecipe() {
    assertEquals(2, butter.getIngredientsForRecipe().size());
    assertTrue(butter.getIngredientsForRecipe().contains(heavyCream));
    assertTrue(butter.getIngredientsForRecipe().contains(salt));
  }

  @Test
  void testAddIngredient() {
    butter.addIngredient(himalayaSalt);
    assertEquals(3, butter.getIngredientsForRecipe().size());
    assertTrue(butter.getIngredientsForRecipe().contains(himalayaSalt));
  }

  @Test
  void testFindIngredient() {
    assertEquals(salt, butter.findIngredientForRecipe("Salt"));
    assertNull(butter.findIngredientForRecipe("Flour"));
  }
}
