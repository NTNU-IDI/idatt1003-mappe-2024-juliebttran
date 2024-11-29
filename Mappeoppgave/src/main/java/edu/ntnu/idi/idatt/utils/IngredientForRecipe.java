package edu.ntnu.idi.idatt.utils;

/**
 * Class representing an ingredient for a recipe.
 *
 * @author Julie Bao Thi Tran
 * @version 27.11
 * @since 27.11
 */
public class IngredientForRecipe {
  // Final because name will never change, String because name is a text
  private final String nameOfIngredientForRecipe;
  // Since the amount doesn't necessarily have to be an integer, the datatype will be double
  private final double amountOfIngredientForRecipe;
  // Final because the unit of an ingredient will never change, String because unit is a text
  private final String unitOfIngredientForRecipe;

  /**
   * Constructor for IngredientForRecipe-class.
   *
   * @param nameOfIngredientForRecipe   Name of the ingredient, String, cannot be blank
   * @param amountOfIngredientForRecipe Amount of the ingredient, double, must be positive, in
   *                                    kilogram or litre
   * @param unitOfIngredientForRecipe   Unit to describe the amount of the ingredient, String,
   *                                    cannot be blank, Kg or L
   * @throws IllegalArgumentException If any parameter is invalid
   */
  public IngredientForRecipe(String nameOfIngredientForRecipe, double amountOfIngredientForRecipe,
                             String unitOfIngredientForRecipe)
      throws IllegalArgumentException {
    if (nameOfIngredientForRecipe.isBlank()) {
      throw new IllegalArgumentException("Name cannot be blank, please try again!");
    }
    if (amountOfIngredientForRecipe <= 0) {
      throw new IllegalArgumentException(
          "Amount must be positive, please try again!");
    }
    if (!unitOfIngredientForRecipe.equalsIgnoreCase("L")
        && !unitOfIngredientForRecipe.equalsIgnoreCase("Kg")) {
      throw new IllegalArgumentException("Unit must be 'Kg' or 'L', please try again!");
    }
    this.nameOfIngredientForRecipe = nameOfIngredientForRecipe;
    this.amountOfIngredientForRecipe = amountOfIngredientForRecipe;
    this.unitOfIngredientForRecipe = unitOfIngredientForRecipe;
  }

  /**
   * get-method for name.
   *
   * @return The name of the ingredient, String
   */
  public String getNameOfIngredientForRecipe() {
    return nameOfIngredientForRecipe;
  }

  /**
   * get-method for amount.
   *
   * @return The amount of the ingredient, double
   */
  public double getAmountOfIngredientForRecipe() {
    return amountOfIngredientForRecipe;
  }

  /**
   * get-method for unit.
   *
   * @return The unit of the ingredient, String
   */
  public String getUnitOfIngredientForRecipe() {
    return unitOfIngredientForRecipe;
  }

  /**
   * toString method for Ingredient-class.
   *
   * @return String of information about the ingredient
   */
  public String toString() {
    return getNameOfIngredientForRecipe() + " " + getAmountOfIngredientForRecipe() + " "
        + getUnitOfIngredientForRecipe();
  }
}
