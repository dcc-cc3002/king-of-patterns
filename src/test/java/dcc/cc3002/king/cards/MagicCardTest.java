package dcc.cc3002.king.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite for the magic cards.
 *
 * @author Ignacio Slater M.
 */
class MagicCardTest extends AbstractCardTest {

  /**
   * Initializes the needed fields for the tests.
   */
  @BeforeEach
  void setUp() {
    super.initMat();
    testCard = new MagicCard();
  }

  @Override
  @Test
  void basicTest() {
    checkCardConstruction(MagicCard::new, MonsterCard::new);
  }

  @Override
  @Test
  void playCardTest() {
    checkPlayedCard(testMat::getMagicZone, testMat::getMonsterZone);
  }
}