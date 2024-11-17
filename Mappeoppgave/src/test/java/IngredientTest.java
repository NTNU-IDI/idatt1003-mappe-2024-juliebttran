import edu.ntnu.idi.idatt.Ingredient;
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
        "kg",
        LocalDate.of(2024,12,31),
        10.0
    );
    assertEquals("Sugar", ingredient.getName());
    assertEquals(1.5, ingredient.getAmount());
    assertEquals("kg", ingredient.getUnit());
    assertEquals(LocalDate.of(2024,12,31), ingredient.getBestBefore());
    assertEquals(10.0, ingredient.getPrice());
  }

  @Test
  void testInvalidName() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("", 1.5, "kg", LocalDate.of(2024, 12, 31), 10.0)
    );
    assertEquals("The name has to exist, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidAmount() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("Sugar", -1.0, "kg", LocalDate.of(2024, 12, 31), 10.0)
    );
    assertEquals("The amount has to be a positive number, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidUnit() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("Sugar", 1.5, "", LocalDate.of(2024, 12, 31), 10.0)
    );
    assertEquals("The unit has to exist, please try again!", exception.getMessage());
  }

  @Test
  void testNullBestBeforeDate() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("Sugar", 1.5, "kg", null, 10.0)
    );
    assertEquals("The best-before-date has to exist, please try again!", exception.getMessage());
  }

  @Test
  void testInvalidPrice() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Ingredient("Sugar", 1.5, "kg", LocalDate.of(2024, 12, 31), -10.0)
    );
    assertEquals("The price has to exist, please try again!", exception.getMessage());
  }

  @Test
  void testSetInvalidAmount() {
    Ingredient ingredient = new Ingredient(
        "Sugar",
        1.5,
        "kg",
        LocalDate.of(2024, 12, 31),
        10.0
    );
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> ingredient.setAmount(-2.0)
    );
    assertEquals("The amount has to be a positive number, please try again!", exception.getMessage());
  }
}
