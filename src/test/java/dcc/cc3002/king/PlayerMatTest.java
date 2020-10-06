package dcc.cc3002.king;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dcc.cc3002.king.cards.Card;
import dcc.cc3002.king.cards.CardType;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite for the game mat.
 *
 * @author Ignacio Slater M.
 */
class PlayerMatTest {

  private PlayerMat testMat;

  /**
   * Initializes a test game mat.
   */
  @BeforeEach
  void setUp() {
    testMat = new PlayerMat();
  }

  /**
   * Checks that the game mat is created correctly.
   * <p>
   * This tests the equals and hashCode methods.
   *
   * @see PlayerMat#equals(Object)
   * @see PlayerMat#hashCode()
   */
  @Test
  void basicTest() {
    var sameMat = testMat;
    assertEquals(sameMat, testMat);
    var expectedMat = new PlayerMat();
    assertNotEquals(testMat, new Object());
    assertEquals(expectedMat, testMat);
    assertEquals(expectedMat.hashCode(), testMat.hashCode());
    // This lines will assure the branch coverage
    testMat.addMagicCard(new Card(CardType.MAGIC));
    assertNotEquals(testMat, new PlayerMat());
    assertNotEquals(testMat.hashCode(), new PlayerMat().hashCode());
    testMat = new PlayerMat();
    testMat.addMonsterCard(new Card(CardType.MONSTER));
    assertNotEquals(testMat, new PlayerMat());
    assertNotEquals(testMat.hashCode(), new PlayerMat().hashCode());
    testMat.addMagicCard(new Card(CardType.MAGIC));
  }

  /**
   * Checks that monster cards are added correctly to the mat.
   *
   * @see PlayerMat#getMonsterZone()
   * @see PlayerMat#addMonsterCard(Card)
   */
  @Test
  void monsterZoneTest() {
    // Using this notation we can pass method references to the test and reduce code
    // duplication.
    testCardZone(testMat::getMonsterZone, testMat::addMonsterCard, CardType.MONSTER);

  }

  /**
   * Tests that a card is added correctly to the appropriate zone.
   *
   * @param zoneGetter
   *     a getter method.
   *     A supplier is a method that receives no parameters and returns an object.
   *     In this case, it returns an object of type {@code List<Card>}.
   * @param cardAdder
   *     an adder method.
   *     A consumer is a method that receives a parameter and returns nothing.
   *     In this case, it receives an object of type {@code Card}.
   */
  private void testCardZone(final Supplier<List<Card>> zoneGetter,
      final Consumer<Card> cardAdder, final CardType type) {
    assertTrue(zoneGetter.get().isEmpty());
    for (int i = 0; i < 5; i++) {
      cardAdder.accept(new Card(type));
      assertEquals(i + 1, zoneGetter.get().size());
    }
    cardAdder.accept(new Card(type));
    assertEquals(5, zoneGetter.get().size());
  }

  /**
   * Checks that magic cards are added correctly to the mat.
   *
   * @see PlayerMat#getMonsterZone()
   * @see PlayerMat#addMonsterCard(Card)
   */
  @Test
  void magicZoneTest() {
    // Using this notation we can pass method references to the test and reduce code
    // duplication.
    testCardZone(testMat::getMagicZone, testMat::addMagicCard, CardType.MAGIC);
  }
}