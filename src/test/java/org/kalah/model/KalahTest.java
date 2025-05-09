package org.kalah.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KalahTest {

  private Kalah kalah;

  @BeforeEach
  void setUp() {
    kalah = new Kalah(1, PlayerType.PLAYER1);
  }

  @Test
  void testAddBeans() {
    kalah.add(5);
    assertEquals(5, kalah.getBeans(), "Kalah should have 5 beans after adding.");
  }

  @Test
  void testIsSowable_TrueForOwner() {
    assertTrue(kalah.isSowable(PlayerType.PLAYER1), "Kalah should be sowable for its owner.");
  }

  @Test
  void testIsSowable_FalseForOpponent() {
    assertFalse(kalah.isSowable(PlayerType.PLAYER2), "Kalah should not be sowable for the opponent.");
  }

  @Test
  void testMightTakeOpposite_AlwaysFalse() {
    assertFalse(kalah.mightTakeOpposite(PlayerType.PLAYER1), "Kalah should never take opposite.");
    assertFalse(kalah.mightTakeOpposite(PlayerType.PLAYER2), "Kalah should never take opposite.");
  }
}