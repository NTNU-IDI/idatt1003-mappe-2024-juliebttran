package edu.ntnu.idi.idatt.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientTest {
  @Test
  void testIngredient() {
    Ingredient ingredient = new Ingredient(
        "Sugar",
        1.5,
        "Kg",
        LocalDate.of(2024,12,31),
        10.0);
    assertEquals("Sugar", ingredient.getName());
    assertEquals(1.5, ingredient.getAmount());
    assertEquals("Kg", ingredient.getUnit());
    assertEquals(LocalDate.of(2024,12,31), ingredient.getBestBefore());
    assertEquals(10.0, ingredient.getPrice());
  }

  @Test
  void testInvalidName() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("", 1.5, "Kg", LocalDate.of(2024, 12, 31), 10.0));
    assertEquals("Name cannot be blank, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidAmount() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("Sugar", -1.0, "Kg", LocalDate.of(2024, 12, 31), 10.0));
    assertEquals("Amount must be positive, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidUnit() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("Sugar", 1.5, "", LocalDate.of(2024, 12, 31), 10.0));
    assertEquals("Unit must be 'Kg' or 'L', please try again!", exception.getMessage());
  }

  @Test
  void testNullBestBeforeDate() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("Sugar", 1.5, "Kg", null, 10.0));
    assertEquals("Best-before-date cannot be null, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidPrice() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("Sugar", 1.5, "Kg", LocalDate.of(2024, 12, 31), -10.0));
    assertEquals("Price must be positive, please try again!", exception.getMessage());
  }

  @Test
  void testSetInvalidAmount() {
    Ingredient ingredient = new Ingredient(
        "Sugar",
        1.5,
        "Kg",
        LocalDate.of(2024, 12, 31),
        10.0);
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> ingredient.setAmount(-2.0));
    assertEquals("Amount must be positive, please try again!", exception.getMessage());
  }
}
