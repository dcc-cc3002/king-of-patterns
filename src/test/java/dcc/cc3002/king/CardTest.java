package dcc.cc3002.king;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dcc.cc3002.king.cards.Card;
import dcc.cc3002.king.cards.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardTest {

  private Card testMonsterCard;
  private Card testMagicCard;
  private PlayerMat testMat;

  @BeforeEach
  void setUp() {
    testMonsterCard = new Card(CardType.MONSTER);
    testMagicCard = new Card(CardType.MAGIC);
    testMat = new PlayerMat();
  }

  @Test
  void basicTest() {
    var expectedMonsterCard = new Card(CardType.MONSTER);
    var sameMonsterCard = testMonsterCard;
    assertEquals(sameMonsterCard, testMonsterCard);
    assertEquals(expectedMonsterCard, testMonsterCard);
    assertEquals(expectedMonsterCard.hashCode(), testMonsterCard.hashCode());
    var expectedMagicCard = new Card(CardType.MAGIC);
    var sameMagicCard = testMagicCard;
    assertEquals(sameMagicCard, testMagicCard);
    assertEquals(expectedMagicCard, testMagicCard);
    assertEquals(expectedMagicCard.hashCode(), testMagicCard.hashCode());
    assertNotEquals(testMonsterCard, testMagicCard);
    assertNotEquals(testMonsterCard, new Object());
  }

  @Test
  void playMonsterCardTest() {
    assertFalse(testMat.getMonsterZone().contains(testMonsterCard));
    assertFalse(testMat.getMagicZone().contains(testMonsterCard));
    testMonsterCard.playTo(testMat);
    assertTrue(testMat.getMonsterZone().contains(testMonsterCard));
    assertFalse(testMat.getMagicZone().contains(testMonsterCard));
  }

  @Test
  void playMagicCardTest() {
    assertFalse(testMat.getMonsterZone().contains(testMagicCard));
    assertFalse(testMat.getMagicZone().contains(testMagicCard));
    testMagicCard.playTo(testMat);
    assertFalse(testMat.getMonsterZone().contains(testMagicCard));
    assertTrue(testMat.getMagicZone().contains(testMagicCard));
  }
}