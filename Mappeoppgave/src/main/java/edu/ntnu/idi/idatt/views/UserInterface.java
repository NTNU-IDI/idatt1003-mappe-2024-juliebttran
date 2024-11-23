package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.models.Ingredient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing user interface, handles all user interaction as well as initializing and setup
 * of new instances.
 *
 * @author Julie Bao Thi Tran
 * @version 23.11
 */
public class UserInterface {
  private ArrayList<Ingredient> ingredients;
  Scanner scanner = new Scanner(System.in);

  /**
   * Constructor for UserInterface-class.
   */
  public UserInterface() {
  }

  /**
   * Initialize-method.
   * Responsible for initializing and setup of new instances
   */
  public void init() {
    ingredients = new ArrayList<>();

    Ingredient carrot = new Ingredient("Carrot", 2.3, "kg", LocalDate.of(2024, 12, 12), 24.5);
    Ingredient sugar = new Ingredient("Sugar", 1.5, "kg", LocalDate.of(2025, 12, 14), 29.9);
    Ingredient flour = new Ingredient("Flour", 2.0, "kg", LocalDate.of(2025, 3, 5), 19.90);
    Ingredient broccoli = new Ingredient("Broccoli", 0.5, "kg", LocalDate.of(2024, 11, 30), 39.90);

    ingredients.add(carrot);
    ingredients.add(sugar);
    ingredients.add(flour);
    ingredients.add(broccoli);
  }

  /**
   * Start-method.
   * Handles all user interaction
   */
  public void start() {
    boolean runProgram = true;
    while (runProgram) {
      System.out.println("What do you want to do next? Type:");
      System.out.println("1: If you want to print a list of all the ingredients.");
      System.out.println("2: If you want to quit the program.");
      int choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          printIngredients();
          break;
        case 2:
          System.out.println("You want to quit the program.");
          runProgram = false;
          break;
        default:
          System.out.println("Please provide a valid choice.");
          break;
      }
    }
  }

  /**
   * Method for printing the ingredients in the list.
   * Prints in the following format: name, amount with unit, best-before-date and price
   */
  public void printIngredients() {
    System.out.println("You want to print a list of all the ingredients.");
    for (Ingredient ingredient : ingredients) {
      System.out.println(ingredient.toString() + "\n");
    }
  }
}
