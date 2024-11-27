package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.views.UserInterface;

/**
 * Class representing the application/clientprogram.
 *
 * @author Julie Bao Thi Tran
 * @version
 * @since 23.11
 */
public class Application {
  /**
   * The entry point of the application.
   * This method initializes the user interface and starts the application
   *
   * @param args Command-line arguments - not used
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
    userInterface.start();
  }
}