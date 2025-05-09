package org.kalah.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class BoardTest {

  @Test
  void testBoardInitialization() {
    int size = 6;
    int beans = 4;
    Board board = new Board(size, beans);

    assertEquals(size, board.getSize());
    for (int i = 0; i < size; i++) {
      assertEquals(beans, board.getHouse(PlayerType.PLAYER1, i).getBeans());
      assertEquals(beans, board.getHouse(PlayerType.PLAYER2, i).getBeans());
    }
    assertEquals(0, board.getKalah(PlayerType.PLAYER1).getBeans());
    assertEquals(0, board.getKalah(PlayerType.PLAYER2).getBeans());
  }

  @Test
  void testGetHouse() {
    Board board = new Board(6, 4);

    House house = board.getHouse(PlayerType.PLAYER1, 0);
    assertNotNull(house);
    assertEquals(PlayerType.PLAYER1, house.getOwner());
    assertEquals(4, house.getBeans());

    house = board.getHouse(PlayerType.PLAYER1, 5);
    assertNotNull(house);
    assertEquals(PlayerType.PLAYER1, house.getOwner());
    assertEquals(4, house.getBeans());

    house = board.getHouse(PlayerType.PLAYER2, 0);
    assertNotNull(house);
    assertEquals(PlayerType.PLAYER2, house.getOwner());
    assertEquals(4, house.getBeans());

    house = board.getHouse(PlayerType.PLAYER2, 5);
    assertNotNull(house);
    assertEquals(PlayerType.PLAYER2, house.getOwner());
    assertEquals(4, house.getBeans());

    assertThrows(IllegalArgumentException.class, () -> board.getHouse(PlayerType.PLAYER1, -1));
    assertThrows(IllegalArgumentException.class, () -> board.getHouse(PlayerType.PLAYER1, 6));
  }

  @Test
  void testGetKalah() {
    Board board = new Board(6, 4);

    Kalah kalah = board.getKalah(PlayerType.PLAYER1);
    assertNotNull(kalah);
    assertEquals(PlayerType.PLAYER1, kalah.getOwner());
    assertEquals(0, kalah.getBeans());

    kalah = board.getKalah(PlayerType.PLAYER2);
    assertNotNull(kalah);
    assertEquals(PlayerType.PLAYER2, kalah.getOwner());
    assertEquals(0, kalah.getBeans());
  }

  @Test
  void testGetHouses() {
    int size = 6;
    int beans = 4;
    Board board = new Board(size, beans);

    List<House> player1Houses = board.getHouses(PlayerType.PLAYER1);
    assertNotNull(player1Houses);
    assertEquals(size, player1Houses.size());
    for (int i = 0; i < size; i++) {
      House house = player1Houses.get(i);
      assertNotNull(house);
      assertEquals(PlayerType.PLAYER1, house.getOwner());
      assertEquals(beans, house.getBeans());
      assertEquals(i, house.getIndex());
    }

    List<House> player2Houses = board.getHouses(PlayerType.PLAYER2);
    assertNotNull(player2Houses);
    assertEquals(size, player2Houses.size());
    for (int i = 0; i < size; i++) {
      House house = player2Houses.get(i);
      assertNotNull(house);
      assertEquals(PlayerType.PLAYER2, house.getOwner());
      assertEquals(beans, house.getBeans());
      assertEquals(i, house.getIndex());
    }
  }

  @Test
  void testGetNextStore() {
    Board board = new Board(6, 4);

    House house = board.getHouse(PlayerType.PLAYER1, 0);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER1, 1), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER1, 1);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER1, 2), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER1, 2);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER1, 3), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER1, 3);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER1, 4), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER1, 4);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER1, 5), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER1, 5);
    assertNotNull(house);
    assertEquals(board.getKalah(PlayerType.PLAYER1), board.getNextStore(house));

    Kalah kalah = board.getKalah(PlayerType.PLAYER1);
    assertNotNull(kalah);
    assertEquals(board.getHouse(PlayerType.PLAYER2, 0), board.getNextStore(kalah));

    house = board.getHouse(PlayerType.PLAYER2, 0);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER2, 1), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER2, 1);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER2, 2), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER2, 2);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER2, 3), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER2, 3);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER2, 4), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER2, 4);
    assertNotNull(house);
    assertEquals(board.getHouse(PlayerType.PLAYER2, 5), board.getNextStore(house));

    house = board.getHouse(PlayerType.PLAYER2, 5);
    assertNotNull(house);
    assertEquals(board.getKalah(PlayerType.PLAYER2), board.getNextStore(house));

    kalah = board.getKalah(PlayerType.PLAYER2);
    assertNotNull(kalah);
    assertEquals(board.getHouse(PlayerType.PLAYER1, 0), board.getNextStore(kalah));
  }

  @Test
  void testGetOppositeHouse() {
    Board board = new Board(6, 4);

    for (int i = 0; i < board.getSize(); i++) {
      House house = board.getHouse(PlayerType.PLAYER1, i);
      House oppositeHouse = board.getOppositeHouse(house);

      assertNotNull(oppositeHouse);
      assertEquals(PlayerType.PLAYER2, oppositeHouse.getOwner());
      assertEquals(4, oppositeHouse.getBeans());
      assertEquals(board.getSize() - i - 1, oppositeHouse.getIndex());

      house = board.getHouse(PlayerType.PLAYER2, i);
      oppositeHouse = board.getOppositeHouse(house);

      assertNotNull(oppositeHouse);
      assertEquals(PlayerType.PLAYER1, oppositeHouse.getOwner());
      assertEquals(4, oppositeHouse.getBeans());
      assertEquals(board.getSize() - i - 1, oppositeHouse.getIndex());
    }
  }
}