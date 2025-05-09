package org.kalah.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

  @Test
  void testHouseInitialization() {
    House house = new House(0, PlayerType.PLAYER1, 4);

    assertEquals(0, house.getIndex());
    assertEquals(PlayerType.PLAYER1, house.getOwner());
    assertEquals(4, house.getBeans());
  }

  @Test
  void testTakeBeans() {
    House house = new House(0, PlayerType.PLAYER1, 4);

    int beans = house.take();
    assertEquals(4, beans);
    assertEquals(0, house.getBeans());
  }

  @Test
  void testIsSowable() {
    House house = new House(0, PlayerType.PLAYER1, 4);

    assertTrue(house.isSowable(PlayerType.PLAYER1));
    assertTrue(house.isSowable(PlayerType.PLAYER2));
  }

  @Test
  void testMightTakeOpposite() {
    House house = new House(0, PlayerType.PLAYER1, 1);

    assertTrue(house.mightTakeOpposite(PlayerType.PLAYER1));
    assertFalse(house.mightTakeOpposite(PlayerType.PLAYER2));

    house = new House(0, PlayerType.PLAYER1, 2);
    assertFalse(house.mightTakeOpposite(PlayerType.PLAYER1));
  }

  @Test
  void testIsEmpty() {
    House house = new House(0, PlayerType.PLAYER1, 0);

    assertTrue(house.isEmpty());

    house = new House(0, PlayerType.PLAYER1, 4);
    assertFalse(house.isEmpty());
  }
}