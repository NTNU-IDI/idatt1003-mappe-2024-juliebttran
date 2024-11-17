package edu.ntnu.idi.idatt;

import java.time.LocalDate;

/**
 * Class representing an ingredient.
 *
 * @author Julie Bao Thi Tran
 * @version 14.11.2024
 */
public class Ingredient {
  // Final because name will never change, String because name is a text
  private final String name;
  // Since the amount doesn't necessarily have to be an integer, the datatype will be double
  private double amount;
  // Final because the unit of an ingredient will never change, String because unit is a text
  private final String unit;
  /* Final because the best-before-date of this ingredient will never change,
  chose to use LocalDate because it has a lot of useful methods. */
  private final LocalDate bestBefore;
  /* Final because the price per unit of the ingredient will never change.
  Since this represents the price per unit the price can be a decimal,
  therefore this variable is of the datatype double. */
  private final double price;

  /**
   * Constructor for Ingredient-class.
   *
   * @param name       Name of the ingredient, String, cannot be blank
   * @param amount     Amount of the ingredient, double, cannot be negative
   *                   (think SI-unit)
   * @param unit       Unit to describe the amount of the ingredient, String,
   *                   cannot be blank (think SI-unit)
   * @param bestBefore Best-before-date for the ingredient, LocalDate,
   *                   should only contain year, month and day
   * @param price      Price of the ingredient per unit, double, cannot be negative
   * @throws IllegalArgumentException Throws illegal argument exception and asks client to try again
   */
  public Ingredient(String name, double amount, String unit, LocalDate bestBefore, double price)
      throws IllegalArgumentException {
    if (name.isBlank()) {
      throw new IllegalArgumentException("The name has to exist, please try again!");
    }
    if (amount < 0) {
      throw new IllegalArgumentException(
          "The amount has to be a positive number, please try again!");
    }
    if (unit.isBlank()) {
      throw new IllegalArgumentException("The unit has to exist, please try again!");
    }
    if (bestBefore == null) {
      throw new IllegalArgumentException("The best-before-date has to exist, please try again!");
    }
    if (price < 0) {
      throw new IllegalArgumentException("The price has to exist, please try again!");
    }

    this.name = name;
    this.amount = amount;
    this.unit = unit;
    this.bestBefore = bestBefore;
    this.price = price;
  }

  /**
   * get-method for name.
   *
   * @return The name of the ingredient
   */
  public String getName() {
    return name;
  }

  /**
   * get-method for amount.
   *
   * @return The amount of the ingredient
   */
  public double getAmount() {
    return amount;
  }

  /**
   * set-method for amount.
   * Has set-method because the amount of the ingredient can change
   *
   * @param amount New amount of the ingredient, double, cannot be negative (think SI-unit)
   */
  public void setAmount(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException(
          "The amount has to be a positive number, please try again!");
    }
    this.amount = amount;
  }

  /**
   * get-method for unit.
   *
   * @return The unit of the ingredient
   */
  public String getUnit() {
    return unit;
  }

  /**
   * get-method for bestBefore.
   *
   * @return The best-before-date of the ingredient
   */
  public LocalDate getBestBefore() {
    return bestBefore;
  }

  /**
   * get-method for price.
   *
   * @return The price of the ingredient
   */
  public double getPrice() {
    return price;
  }

  /**
   * toString method for Ingredient-class.
   *
   * @return String of information about the ingredient
   */
  public String toString() {
    return "Name of ingredient: " + getName()
        + "\nAmount: " + getAmount() + " " + getUnit()
        + "\nBest-before-date: " + getBestBefore()
        + "\nPrice per unit: " + getPrice();
  }
}


