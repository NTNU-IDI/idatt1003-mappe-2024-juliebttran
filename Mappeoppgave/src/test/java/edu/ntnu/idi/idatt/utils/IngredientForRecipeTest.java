package edu.ntnu.idi.idatt.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.models.Ingredient;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class IngredientForRecipeTest {
  @Test
  void testIngredientForRecipe() {
    IngredientForRecipe ingredient = new IngredientForRecipe(
        "Sugar",
        1.5,
        "Kg"
    );
    assertEquals("Sugar", ingredient.getNameOfIngredientForRecipe());
    assertEquals(1.5, ingredient.getAmountOfIngredientForRecipe());
    assertEquals("Kg", ingredient.getUnitOfIngredientForRecipe());
  }

  @Test
  void testInvalidName() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new IngredientForRecipe("", 1.5, "Kg"));
    assertEquals("Name cannot be blank, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidAmount() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new IngredientForRecipe("Sugar", -1.0, "Kg"));
    assertEquals("Amount must be positive, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidUnit() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new IngredientForRecipe("Sugar", 1.5, ""));
    assertEquals("Unit must be 'Kg' or 'L', please try again!", exception.getMessage());
  }
}
