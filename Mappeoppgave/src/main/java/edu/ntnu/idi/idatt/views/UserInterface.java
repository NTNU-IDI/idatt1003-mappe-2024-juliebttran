package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Ingredient;
import edu.ntnu.idi.idatt.utils.Cookbook;
import edu.ntnu.idi.idatt.utils.IngredientForRecipe;
import edu.ntnu.idi.idatt.utils.MealManager;
import edu.ntnu.idi.idatt.utils.Recipe;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing user interface, handles all user interaction as well as initializing and setup
 * of new instances.
 *
 * @author Julie Bao Thi Tran
 * @version 10.12
 * @since 23.11
 */
public class UserInterface {
  // Final to prevent external code from modifying the original list directly, ArrayList
  private final ArrayList<Ingredient> ingredients = new ArrayList<>();
  private FoodStorage foodStorage;
  // Final to prevent external code from modifying the original list directly, ArrayList
  private final ArrayList<Recipe> recipes = new ArrayList<>();
  private Cookbook cookBook;
  private MealManager mealManager;
  private final Scanner scanner = new Scanner(System.in);

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
    fillFoodStorage();
    fillCookBook();
    createMealManager();
  }

  /**
   * Start-method.
   * Starts the application, presents the menu, retrieves the selected menu choice from the user
   * and executes the selected functionality
   */
  public void start() {
    boolean runProgram = true;
    while (runProgram) {
      System.out.println("Enter:");
      System.out.println("1: If you want a list of all the ingredients in the food storage.");
      System.out.println("2: If you want to add a new ingredient to the food storage.");
      System.out.println("3: If you want to check if you have an ingredient in the food storage.");
      System.out.println("4: If you want to remove an amount of an ingredient in the food"
          + " storage.");
      System.out.println("5: If you want a list of all the expired ingredients in the food"
          + " storage.");
      System.out.println("6: If you want a list of all the ingredients with best-before-date"
          + " before a specific date.");
      System.out.println("7: If you want a list of all the ingredients in the food storage,"
          + " in alphabetical order.");
      System.out.println("8: If you want a list of all the recipes in the cookbook.");
      System.out.println("9: If you want to add a new recipe to the cookbook.");
      System.out.println("10: If you want to add an ingredient to an existing recipe.");
      System.out.println("11: If you want to check if you can make a specific recipe.");
      System.out.println("12: If you want to use ingredients for making a recipe.");
      System.out.println("13: If you want a list of suggestions of recipes you can make with the"
          + " available ingredients in the food storage.");
      System.out.println("14: If you want to quit the program.");
      int menuChoice = 0;
      if (scanner.hasNextInt()) {
        menuChoice = scanner.nextInt();
      } else {
        System.out.println("You must enter a number, not text, please try again!");
      }
      scanner.nextLine();
      switch (menuChoice) {
        case 1:
          printIngredientsInFoodStorage();
          break;
        case 2:
          addNewIngredientToFoodStorage();
          break;
        case 3:
          searchForIngredientInFoodStorage();
          break;
        case 4:
          removeAmountFromFoodStorage();
          break;
        case 5:
          printListOfExpiredIngredients();
          break;
        case 6:
          printListOfIngredientsBeforeDate();
          break;
        case 7:
          printListOfIngredientsAlphabetical();
          break;
        case 8:
          printAllRecipesInCookbook();
          break;
        case 9:
          addRecipeToCookBook();
          break;
        case 10:
          addIngredientToRecipe();
          break;
        case 11:
          hasRequiredAmountOfIngredients();
          break;
        case 12:
          useIngredientsToMakeRecipe();
          break;
        case 13:
          listOfSuggestedRecipes();
          break;
        case 14:
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
   * Method for filling the food storage with ingredients.
   */
  public void fillFoodStorage() {
    Ingredient carrot = new Ingredient("Carrot", 2.3, "Kg", LocalDate.of(2024, 11, 12), 24.5);
    Ingredient sugar = new Ingredient("Sugar", 1.5, "Kg", LocalDate.of(2025, 12, 14), 29.9);
    Ingredient flour = new Ingredient("Flour", 2.0, "Kg", LocalDate.of(2025, 3, 5), 19.90);
    Ingredient broccolini =
        new Ingredient("Broccolini", 0.5, "Kg", LocalDate.of(2025, 7, 30), 39.90);
    Ingredient egg = new Ingredient("Egg", 1, "Kg", LocalDate.of(2025, 1, 17), 62.99);
    Ingredient butter = new Ingredient("Butter", 0.5, "Kg", LocalDate.of(2025, 3, 30), 64.90);
    Ingredient milk = new Ingredient("Milk", 1.5, "L", LocalDate.of(2024, 9, 28), 24.5);
    Ingredient juice = new Ingredient("Juice", 1, "L", LocalDate.of(2025, 11, 16), 46.90);
    Ingredient bread = new Ingredient("Bread", 0.5, "Kg", LocalDate.of(2025, 4, 7), 50.90);
    Ingredient salami = new Ingredient("Salami", 0.3, "Kg", LocalDate.of(2025, 10, 15), 330.00);
    ingredients.add(carrot);
    ingredients.add(sugar);
    ingredients.add(flour);
    ingredients.add(broccolini);
    ingredients.add(egg);
    ingredients.add(butter);
    ingredients.add(milk);
    ingredients.add(juice);
    ingredients.add(bread);
    ingredients.add(salami);
    foodStorage = new FoodStorage(ingredients);
  }

  /**
   * Method for filling the cookbook with recipes.
   */
  public void fillCookBook() {
    ArrayList<IngredientForRecipe> ingredientsForBreakfast = new ArrayList<>();
    IngredientForRecipe bread = new IngredientForRecipe("Bread", 0.1, "Kg");
    IngredientForRecipe salami = new IngredientForRecipe("Salami", 0.1, "Kg");
    IngredientForRecipe butter = new IngredientForRecipe("Butter", 0.1, "Kg");
    ingredientsForBreakfast.add(bread);
    ingredientsForBreakfast.add(salami);
    ingredientsForBreakfast.add(butter);
    Recipe breakfast = new Recipe("Breakfast", "A traditional norwegian breakfast",
        "\n1. Spread butter on bread 2. Put salami on bread", ingredientsForBreakfast, 1);
    recipes.add(breakfast);

    ArrayList<IngredientForRecipe> ingredientsForDinner = new ArrayList<>();
    IngredientForRecipe beef = new IngredientForRecipe("Beef", 0.5, "Kg");
    IngredientForRecipe brokkolini = new IngredientForRecipe("Brokkolini", 0.4, "Kg");
    IngredientForRecipe sauce = new IngredientForRecipe("Sauce", 0.1, "L");
    ingredientsForDinner.add(beef);
    ingredientsForDinner.add(brokkolini);
    ingredientsForDinner.add(sauce);
    Recipe dinner = new Recipe("Dinner", "An easy recipe for a delicious meal!",
        "\n1. Grill the beef 2. Cook the brokkolini 3. Make the sauce 4. Plate everything",
        ingredientsForDinner, 1);
    recipes.add(dinner);
    cookBook = new Cookbook(recipes);
  }

  /**
   * Method for creating an instance of MealManager.
   */
  public void createMealManager() {
    mealManager = new MealManager(foodStorage, cookBook);
  }

  /**
   * Method for printing all ingredients in the food storage.
   * Prints each ingredient in the following format: name, amount with unit, best-before-date and
   * price per unit
   */
  public void printIngredientsInFoodStorage() {
    System.out.println(foodStorage.toString());
  }

  /**
   * Method for adding a new ingredient to the food storage.
   */
  public void addNewIngredientToFoodStorage() {
    String nameOfnewIngredient;
    do {
      System.out.println("What's the name of the ingredient you want to add?");
      nameOfnewIngredient = scanner.nextLine();
      if (nameOfnewIngredient.isBlank()) {
        System.out.println("Name cannot be blank, please try again");
      }
    } while (nameOfnewIngredient.isBlank());

    double amountOfNewIngredient = 0;
    do {
      System.out.println("How much would you like to add? In kilogram or litre.");
      if (scanner.hasNextDouble()) {
        amountOfNewIngredient = scanner.nextDouble();
        scanner.nextLine();
        if (amountOfNewIngredient <= 0) {
          System.out.println("Amount must be a positive number greater than 0, please try again!");
        }
      } else {
        System.out.println("Amount must be a number, not text, please try again!");
        scanner.next();
      }
    } while (amountOfNewIngredient <= 0);

    String unitOfNewIngredient;
    do {
      System.out.println("Which unit do you want to use for this ingredient? 'Kg' or 'L'.");
      unitOfNewIngredient = scanner.nextLine();
      if (!unitOfNewIngredient.equalsIgnoreCase("Kg")
          && !unitOfNewIngredient.equalsIgnoreCase("L")) {
        System.out.println("Unit must be either 'Kg' or 'L',please try again.");
      }
    } while (!unitOfNewIngredient.equalsIgnoreCase("Kg")
        && !unitOfNewIngredient.equalsIgnoreCase("L"));

    LocalDate date = null;
    do {
      System.out.println("What's the best-before-date, in the following format: yyyy-mm-dd");
      String bestBeforeOfNewIngredient = scanner.nextLine();
      try {
        String[] dateParts = bestBeforeOfNewIngredient.split("-");
        date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]),
            Integer.parseInt(dateParts[2]));
      } catch (Exception e) {
        System.out.println(
            "The date-format you provided is wrong, please try again in the following format:"
                + " yyyy-mm-dd");
      }
    } while (date == null);

    double priceOfNewIngredient = 0;
    do {
      System.out.println("What's the price per unit in NOK?");

      if (scanner.hasNextDouble()) {
        priceOfNewIngredient = scanner.nextDouble();
        if (priceOfNewIngredient <= 0) {
          System.out.println("Price must be a positive number greater than 0, please try again!");
        }
      } else {
        System.out.println("Price must be a number, not text, please try again!");
        scanner.next();
      }
    } while (priceOfNewIngredient <= 0);

    try {
      Ingredient newIngredient =
          new Ingredient(nameOfnewIngredient, amountOfNewIngredient, unitOfNewIngredient, date,
              priceOfNewIngredient);
      if (foodStorage.addNewIngredient(newIngredient)) {
        System.out.println("Amount of " + nameOfnewIngredient + " was successfully updated.");
      } else {
        System.out.println(nameOfnewIngredient + " was successfully added to the food storage.");
      }
    } catch (IllegalArgumentException exception) {
      System.out.println(exception.getMessage());
    }
  }

  /**
   * Method for searching after an ingredient in the food storage, based on the name of the
   * ingredient.
   */
  public void searchForIngredientInFoodStorage() {
    Ingredient foundIngredient;
    String nameOfIngredientToFind;
    do {
      System.out.println("What's the name of the ingredient you want to search for?");
      nameOfIngredientToFind = scanner.nextLine();
      foundIngredient = foodStorage.findIngredient(nameOfIngredientToFind);
      if (nameOfIngredientToFind.isBlank()) {
        System.out.println("Name cannot be blank, please try again");
      } else if (foundIngredient == null) {
        System.out.println(
            nameOfIngredientToFind + " does not exist in the food storage, please try again!");
      }
    } while (nameOfIngredientToFind.isBlank() || foundIngredient == null);
    System.out.println(nameOfIngredientToFind + " exists in the food storage.");
  }

  /**
   * Method for removing an amount of an existing ingredient.
   */
  public void removeAmountFromFoodStorage() {
    Ingredient foundIngredient;
    String nameOfIngredientToRemove;
    do {
      System.out.println("What's the name of the ingredient you want to remove an amount from?");
      nameOfIngredientToRemove = scanner.nextLine();
      foundIngredient = foodStorage.findIngredient(nameOfIngredientToRemove);
      if (nameOfIngredientToRemove.isBlank()) {
        System.out.println("Name cannot be blank, please try again");
      } else if (foundIngredient == null) {
        System.out.println(
            nameOfIngredientToRemove + " does not exist in the food storage, please try again!");
      }
    } while (nameOfIngredientToRemove.isBlank() || foundIngredient == null);

    double amountOfIngredientToRemove = 0;
    do {
      System.out.println("What's the amount you want to remove?");
      if (scanner.hasNextDouble()) {
        amountOfIngredientToRemove = scanner.nextDouble();
        if (amountOfIngredientToRemove <= 0) {
          System.out.println(
              "The amount you want to remove must be a positive number greater than 0, please"
                  + " try again!");
        } else if (amountOfIngredientToRemove > foundIngredient.getAmount()) {
          System.out.println("You do not have enough " + nameOfIngredientToRemove
              + " in the food storage to remove " + amountOfIngredientToRemove
              + " " + foundIngredient.getUnit() + ". Please try again!");
        }
      } else {
        System.out.println("Amount must be a number, not text, please try again!");
        scanner.next();
      }
    } while (amountOfIngredientToRemove <= 0
        || amountOfIngredientToRemove > foundIngredient.getAmount());
    System.out.println(amountOfIngredientToRemove + " " + foundIngredient.getUnit()
        + " of " + nameOfIngredientToRemove + " was successfully removed from the food storage.");
  }

  /**
   * Method for printing all the expired ingredients in the food storage, including the total sum
   * of the ingredients.
   * Prints each ingredient in the following format: name, amount with unit, best-before-date
   * and price per unit
   */
  public void printListOfExpiredIngredients() {
    if (!foodStorage.findAllExpiredIngredients().isEmpty()) {
      foodStorage.findAllExpiredIngredients()
          .forEach(System.out::println);
      System.out.println(
          "Total sum of expired ingredients: " + foodStorage.sumOfExpiredIngredients());
    } else {
      System.out.println("The food storage contains no expired ingredients.");
    }
  }

  /**
   * Method for printing all the ingredients in the food storage with best-before-date before a
   * specific date.
   * Prints each ingredient in the following format: name, amount with unit, best-before-date
   * and price per unit
   */
  public void printListOfIngredientsBeforeDate() {
    System.out.println(
        "Which date to you want to use as reference, in the following format: yyyy-mm-dd");
    LocalDate checkDate = null;
    boolean validDate = false;
    do {
      String dateForReference = scanner.nextLine();
      try {
        String[] dateParts = dateForReference.split("-");
        checkDate = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]),
            Integer.parseInt(dateParts[2]));
        validDate = true;
      } catch (Exception e) {
        System.out.println(
            "The date-format you provided is wrong, please try again in the following format:"
                + " yyyy-mm-dd");
      }
    } while (!validDate);
    ArrayList<Ingredient> findAllIngredientsBeforeDate =
        foodStorage.findAllIngredientsBeforeDate(checkDate);
    if (!findAllIngredientsBeforeDate.isEmpty()) {
      findAllIngredientsBeforeDate
          .forEach(System.out::println);
    } else {
      System.out.println(
          "There is no ingredient in the food storage with best-before-date before " + checkDate);
    }
  }

  /**
   * Method for printing all the ingredients in the food storage, in alphabetical order.
   * Prints each ingredient in the following format: name, amount with unit, best-before-date
   * and price per unit
   */
  public void printListOfIngredientsAlphabetical() {
    foodStorage.sortAllIngredientsAlphabetical()
        .forEach(System.out::println);
  }

  /**
   * Method for printing all recipes in the cookbook.
   * Prints each recipe in the following format: name, description, instructions, ingredients,
   * servings
   */
  public void printAllRecipesInCookbook() {
    System.out.println(cookBook.toString());
  }

  /**
   * Method for adding a new recipe to the cookbook.
   */
  public void addRecipeToCookBook() {
    String nameOfRecipe;
    Recipe foundRecipe;
    do {
      System.out.println("What's the name of the recipe you want to add?");
      nameOfRecipe = scanner.nextLine();
      foundRecipe = cookBook.findRecipe(nameOfRecipe);
      if (foundRecipe != null) {
        System.out.println(nameOfRecipe + " already exists in the cookbook, please try again!");
      }
    } while (foundRecipe != null);

    String descriptionOfRecipe;
    do {
      System.out.println("Please write a short description of the recipe.");
      descriptionOfRecipe = scanner.nextLine();
      if (descriptionOfRecipe.isBlank()) {
        System.out.println("Description of recipe cannot be blank, please try again");
      }
    } while (descriptionOfRecipe.isBlank());

    String instructionsOfRecipe;
    do {
      System.out.println("Please instructions for the recipe.");
      instructionsOfRecipe = scanner.nextLine();
      if (instructionsOfRecipe.isBlank()) {
        System.out.println("Instructions of recipe cannot be blank, please try again");
      }
    } while (instructionsOfRecipe.isBlank());

    int servingsForRecipe = 0;
    do {
      System.out.println("How many servings is this recipe meant for?");
      if (scanner.hasNextInt()) {
        servingsForRecipe = scanner.nextInt();
        scanner.nextLine();
        if (servingsForRecipe <= 0) {
          System.out.println("Servings must be a positive number greater than 0, please try again");
        }
      } else {
        System.out.println("Servings must be a number, not text, please try again");
        scanner.next();
      }
    } while (servingsForRecipe <= 0);

    ArrayList<IngredientForRecipe> ingredientsForNewRecipe = new ArrayList<>();
    try {
      Recipe newRecipe =
          new Recipe(nameOfRecipe, descriptionOfRecipe, instructionsOfRecipe,
              ingredientsForNewRecipe,
              servingsForRecipe);
      cookBook.addNewRecipe(newRecipe);
      System.out.println(nameOfRecipe + " was successfully added to the cookbook!");
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

  /**
   * Method for adding an ingredient to an already existing recipe.
   */
  public void addIngredientToRecipe() {
    String nameOfRecipe;
    Recipe foundRecipe;
    do {
      System.out.println("What's the name of the recipe you want to add an ingredient to?");
      nameOfRecipe = scanner.nextLine();
      foundRecipe = cookBook.findRecipe(nameOfRecipe);
      if (foundRecipe == null) {
        System.out.println(nameOfRecipe + " does not exist in the cookbook, please try again.");
      }
    } while (foundRecipe == null);

    IngredientForRecipe foundIngredient;
    String nameOfIngredient;
    do {
      System.out.println("What's the name of the ingredient you want to add?");
      nameOfIngredient = scanner.nextLine();
      foundIngredient = foundRecipe.findIngredientForRecipe(nameOfIngredient);
      if (nameOfIngredient.isBlank()) {
        System.out.println("Name cannot be blank, please try again");
      } else if (foundIngredient != null) {
        System.out.println(nameOfIngredient + " already exists in this recipe, please try again!");
      }
    } while (nameOfIngredient.isBlank() || foundIngredient != null);

    double amountOfIngredient = 0;
    do {
      System.out.println("How much " + nameOfIngredient + " do you need for this recipe? In"
          + " kilogram or litre.");
      if (scanner.hasNextDouble()) {
        amountOfIngredient = scanner.nextDouble();
        scanner.nextLine();
        if (amountOfIngredient <= 0) {
          System.out.println(
              "The amount must be a positive number greater than 0, please try again!");
        }
      } else {
        System.out.println("Amount must be a number, not text, please try again");
        scanner.next();
      }
    } while (amountOfIngredient <= 0);

    String unitOfIngredient;
    do {
      System.out.println("Which unit do you want to use for this ingredient? 'Kg' or 'L'.");
      unitOfIngredient = scanner.nextLine();
      if (!unitOfIngredient.equalsIgnoreCase("Kg") && !unitOfIngredient.equalsIgnoreCase("L")) {
        System.out.println("Unit must be either 'Kg' or 'L',please try again.");
      }
    } while (!unitOfIngredient.equalsIgnoreCase("Kg") && !unitOfIngredient.equalsIgnoreCase("L"));
    try {
      IngredientForRecipe newIngredient =
          new IngredientForRecipe(nameOfIngredient, amountOfIngredient, unitOfIngredient);
      cookBook.addIngredientToRecipe(nameOfRecipe, newIngredient);
      System.out.println(nameOfIngredient + " was successfully added to " + nameOfRecipe + ".");
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

  /**
   * Method for checking if the food storage has the required amounts of ingredients to make a
   * specific recipe.
   */
  public void hasRequiredAmountOfIngredients() {
    String nameOfRecipe;
    Recipe foundRecipe;
    do {
      System.out.println("What's the name of the recipe you want to check if you can make?");
      nameOfRecipe = scanner.nextLine();
      foundRecipe = cookBook.findRecipe(nameOfRecipe);
      if (foundRecipe == null) {
        System.out.println(nameOfRecipe + " does not exist in the cookbook, please try again.");
      }
    } while (foundRecipe == null);
    if (mealManager.hasRequiredAmountOfIngredients(nameOfRecipe)) {
      System.out.println(
          "You have the required amounts of ingredients to make " + nameOfRecipe + ".");
    } else {
      System.out.println(
          "You don't have the required amounts of ingredients to make " + nameOfRecipe + ".");
    }
  }

  /**
   * Method for using the ingredients in the food storage to make a recipe.
   */
  public void useIngredientsToMakeRecipe() {
    String nameOfRecipe;
    Recipe foundRecipe;
    do {
      System.out.println("Whats the name of the recipe you want to make?");
      nameOfRecipe = scanner.nextLine();
      foundRecipe = cookBook.findRecipe(nameOfRecipe);
      if (foundRecipe == null) {
        System.out.println(nameOfRecipe + " does not exist in the cookbook, please try again.");
      }
    } while (foundRecipe == null);
    if (mealManager.useIngredientsToMakeRecipe(nameOfRecipe)) {
      mealManager.useIngredientsToMakeRecipe(nameOfRecipe);
      System.out.println("Successfully made " + nameOfRecipe + ".");
    } else {
      System.out.println(nameOfRecipe
          + " was not successfully made, you do not have the required amounts of ingredients to"
          + " make this recipe.");
    }
  }

  /**
   * Method for printing all recipes that can be made with the available ingredients in the food
   * storage.
   */
  public void listOfSuggestedRecipes() {
    if (!mealManager.suggestRecipes().isEmpty()) {
      mealManager.suggestRecipes().stream()
          .map(Recipe::toString)
          .forEach(System.out::println);
    } else {
      System.out.println("You cannot make any recipes with the available ingredients in the food"
          + " storage.");
    }
  }
}
