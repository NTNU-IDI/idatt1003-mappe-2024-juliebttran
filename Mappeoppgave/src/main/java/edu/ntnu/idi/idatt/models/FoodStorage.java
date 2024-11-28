package edu.ntnu.idi.idatt.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class representing a food storage.
 *
 * @author Julie Bao Thi Tran
 * @version 25.11
 * @since 24.11
 */
public class FoodStorage {
  // Final to prevent external code from modifying the original list directly, ArrayList
  private final ArrayList<Ingredient> ingredients;

  /**
   * Constructor for FoodStorage-class.
   *
   * @param ingredients List of ingredients in the food storage, ArrayList
   */
  public FoodStorage(ArrayList<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  /**
   * get-method for ingredients.
   *
   * @return List of ingredients in the food storage, ArrayList
   */
  public ArrayList<Ingredient> getIngredients() {
    return ingredients;
  }

  /**
   * Method for adding a new ingredient to the food storage.
   *
   * @param newIngredient The ingredient to add to the food storage, Ingredient
   * @return 'true' if the ingredient already exists in food storage and only amount is added,
   *      'false' if a new ingredient is added to the food storage
   */
  public boolean addNewIngredient(Ingredient newIngredient) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equalsIgnoreCase(newIngredient.getName())) {
        ingredient.setAmount(ingredient.getAmount() + newIngredient.getAmount());
        return true;
      }
    }
    ingredients.add(newIngredient);
    return false;
  }

  /**
   * Method for finding an ingredient in the food storage.
   * Searches through the food storage based by the name of the ingredient
   *
   * @param nameOfIngredient The name of the ingredient you want to find, String
   * @return ingredient of type Ingredient, if it exists in the food storage, 'null' if it does not
   *      exist
   */
  public Ingredient findIngredient(String nameOfIngredient) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equalsIgnoreCase(nameOfIngredient)) {
        return ingredient;
      }
    }
    return null;
  }

  /**
   * Method for removing an amount of an existing ingredient.
   * Uses findIngredient-method to find the specific ingredient in the food storage
   *
   * @param nameOfIngredient The name of the ingredient you want to remove an amount from, String
   * @param amountToRemove The amount you want to remove of an ingredient in the food storage,
   *                       double
   * @return 'false' if the amount in food storage is less than the amount you want to remove,
   *      'true' if amount of ingredient or the ingredient itself is removed
   */
  public boolean removeAmountOfIngredient(String nameOfIngredient, double amountToRemove) {
    Ingredient foundIngredient = findIngredient(nameOfIngredient);
    if (foundIngredient.getAmount() < amountToRemove) {
      return false;
    } else if (foundIngredient.getAmount() == amountToRemove) {
      ingredients.remove(foundIngredient);
      return true;
    } else {
      foundIngredient.setAmount(foundIngredient.getAmount() - amountToRemove);
      return true;
    }
  }

  /**
   * Method for finding all ingredients in the food storage with best-before-date before a specific
   * date.
   *
   * @param date The date you want to use as reference, LocalDate
   * @return List of all the ingredients in the food storage with best-before-date before the
   *      specific date, ArrayList
   */
  public ArrayList<Ingredient> findAllIngredientsBeforeDate(LocalDate date) {
    ArrayList<Ingredient> ingredientsBeforeDate = new ArrayList<>();
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getBestBefore().isBefore(date)) {
        ingredientsBeforeDate.add(ingredient);
      }
    }
    return ingredientsBeforeDate;
  }

  /**
   * Method for sorting all the ingredients in the food storage, in alphabetical order.
   *
   * @return List of all the ingredients in the food storage, in alphabetical order, ArrayList
   */
  public ArrayList<Ingredient> sortAllIngredientsAlphabetical() {
    ingredients.sort(Comparator.comparing(Ingredient::getName));
    return new ArrayList<>(ingredients);
  }

  /**
   * toString-method for FoodStorage-class.
   *
   * @return String of information about all the ingredients in the food storage
   */
  public String toString() {
    StringBuilder printFoodStorage = new StringBuilder();
    for (Ingredient ingredient : ingredients) {
      printFoodStorage.append(ingredient.toString()).append("\n");
    }
    return printFoodStorage.toString();
  }
}
