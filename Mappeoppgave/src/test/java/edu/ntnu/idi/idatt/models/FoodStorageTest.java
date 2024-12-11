package edu.ntnu.idi.idatt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FoodStorageTest {
  private Ingredient sugar;
  private Ingredient butter;
  private Ingredient milk;
  private FoodStorage foodStorage;

  @BeforeEach
  void setUp() {
    sugar = new Ingredient("Sugar", 1.5, "Kg", LocalDate.of(2025, 12, 14), 29.9);
    butter = new Ingredient("Butter", 0.5, "Kg", LocalDate.of(2025, 3, 30), 64.90);
    milk = new Ingredient("Milk", 1.5, "L", LocalDate.of(2025, 9, 28), 24.5);
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(sugar);
    ingredients.add(butter);
    foodStorage = new FoodStorage(ingredients);
  }

  @Test
  void testGetIngredients() {
    assertEquals(2, foodStorage.getIngredients().size());
    assertTrue(foodStorage.getIngredients().contains(sugar));
    assertTrue(foodStorage.getIngredients().contains(butter));
    assertFalse(foodStorage.getIngredients().contains(milk));
  }

  @Test
  void testAddNewIngredient() {
    assertFalse(foodStorage.addNewIngredient(milk));
    assertEquals(3, foodStorage.getIngredients().size());
    assertTrue(foodStorage.getIngredients().contains(milk));

    Ingredient newSugar = new Ingredient("Sugar", 0.5, "Kg", LocalDate.of(2025, 12, 14), 29.9);
    assertTrue(foodStorage.addNewIngredient(newSugar));
    assertEquals(2.0, foodStorage.findIngredient("Sugar").getAmount());
  }

  @Test
  void testFindIngredient() {
    assertEquals(sugar, foodStorage.findIngredient("Sugar"));
    assertNull(foodStorage.findIngredient("Flour"));
  }


  @Test
  void testRemoveAmountOfIngredient() {
    assertTrue(foodStorage.removeAmountOfIngredient("Butter", 0.2));
    assertEquals(0.3, foodStorage.findIngredient("Butter").getAmount());
    assertFalse(foodStorage.removeAmountOfIngredient("Butter", 0.5));
    assertTrue(foodStorage.removeAmountOfIngredient("Butter", 0.3));
  }

  @Test
  void testFindAllIngredientsBeforeDate() {
    ArrayList<Ingredient> expiredIngredients = foodStorage.findAllIngredientsBeforeDate(LocalDate.of(2025, 4, 1));
    assertEquals(1, expiredIngredients.size());
    assertTrue(expiredIngredients.contains(butter));

    expiredIngredients = foodStorage.findAllIngredientsBeforeDate(LocalDate.of(2024, 12, 31));
    assertTrue(expiredIngredients.isEmpty());
  }

  @Test
  void testFindExpiredIngredients() {
    ArrayList<Ingredient> expiredIngredients = foodStorage.findAllExpiredIngredients();
    assertEquals(0, expiredIngredients.size());

    Ingredient expiredMilk = new Ingredient("Milk", 1.5, "L", LocalDate.of(2023, 9, 28), 24.5);
    foodStorage.addNewIngredient(expiredMilk);
    expiredIngredients = foodStorage.findAllExpiredIngredients();
    assertEquals(1, expiredIngredients.size());
    assertTrue(expiredIngredients.contains(expiredMilk));
  }

  @Test
  void testSumOfExpiredIngredients() {
    assertEquals(0, foodStorage.sumOfExpiredIngredients());

    Ingredient expiredMilk = new Ingredient("Milk", 1.5, "L", LocalDate.of(2023, 9, 28), 24.5);
    foodStorage.addNewIngredient(expiredMilk);
    assertEquals(36.75, foodStorage.sumOfExpiredIngredients());
  }

  @Test
  void testSortAllIngredientsAlphabetical() {
    ArrayList<Ingredient> sortedIngredients = foodStorage.sortAllIngredientsAlphabetical();
    assertEquals(2, sortedIngredients.size());
    assertEquals("Butter", sortedIngredients.get(0).getName());
    assertEquals("Sugar", sortedIngredients.get(1).getName());

    foodStorage.addNewIngredient(milk);
    sortedIngredients = foodStorage.sortAllIngredientsAlphabetical();
    assertEquals("Butter", sortedIngredients.get(0).getName());
    assertEquals("Milk", sortedIngredients.get(1).getName());
    assertEquals("Sugar", sortedIngredients.get(2).getName());
  }
}