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
   * get-method for Ingredients.
   *
   * @return List of ingredients in the food storage, ArrayList
   */
  public ArrayList<Ingredient> getIngredients() {
    return ingredients;
  }

  /**
   * Method for adding a new ingredient to the food storage.
   *
   * @param newIngredient The ingredient to add to the food storage
   * @return If the amount of an ingredient is updated the method will return 'true', else if a new
   *      ingredient is added to the list the method will return 'false'
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
   * Method for finding an ingredient in the foodstorage.
   * Searched through the food storage based by the name of the ingredient
   *
   * @param ingredientName Name of the ingredient you want to find
   * @return If found, the method will return the ingredient, else the method return 'null',
   *      Ingredient
   */
  public Ingredient findIngredient(String ingredientName) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
        return ingredient;
      }
    }
    return null;
  }

  /**
   * Method for removing an amount of an existing ingredient.
   * If the client tries to remove more than the amount in the foodstorage,ƒ
   *
   * @param ingredientName Name of the ingredient you want to find
   * @param amountToRemove The amount you want to remove of an ingredient in the food storage
   * @return If either amount or the ingredient is removed the method will return 'true', else
   *      'false', boolean
   */
  public boolean removeAmountOfIngredient(String ingredientName, double amountToRemove) {
    Ingredient ingredient = findIngredient(ingredientName);
    if (ingredient.getAmount() < amountToRemove) {
      return false;
    } else if (ingredient.getAmount() == amountToRemove) {
      ingredients.remove(ingredient);
      return true;
    } else {
      ingredient.setAmount(ingredient.getAmount() - amountToRemove);
      return true;
    }
  }

  /**
   * Method for finding all ingredients in the food storage with best-before-date before a specific
   * date.
   *
   * @param date The date you want to use as reference
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
    ArrayList<Ingredient> sortedList = new ArrayList<>(ingredients);
    return sortedList;
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
