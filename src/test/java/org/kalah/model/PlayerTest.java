package org.kalah.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PlayerTest {

  @Test
  void testIsCompleted() {
    Board board = new Board(6, 0);
    Player player = new Player("", PlayerType.PLAYER1, board);

    assertTrue(player.isCompleted());

    board = new Board(6, 4);
    player = new Player("", PlayerType.PLAYER1, board);

    assertFalse(player.isCompleted());
  }

  @Test
  void testFinish() {
    Board board = new Board(6, 4);
    Player player = new Player("", PlayerType.PLAYER1, board);

    player.finish();

    assertEquals(24, player.getKalah().getBeans());
    for (House house : board.getHouses(PlayerType.PLAYER1)) {
      assertEquals(0, house.getBeans());
    }
  }

  @Test
  void testScore() {
    Board board = new Board(6, 4);
    Player player = new Player("", PlayerType.PLAYER1, board);

    assertEquals(0, player.score());

    player.getKalah().add(10);
    assertEquals(10, player.score());
  }

  @Test
  void testTurnValidMove() {
    Board board = new Board(6, 6);
    Player player = new Player("", PlayerType.PLAYER1, board);

    Store lastStore = player.turn(0);

    assertEquals(0, board.getHouse(PlayerType.PLAYER1, 0).getBeans());
    assertEquals(7, board.getHouse(PlayerType.PLAYER1, 1).getBeans());
    assertEquals(6, lastStore.getIndex());
  }

  @Test
  void testTurnEmptyHouse() {
    Board board = new Board(6, 0);
    Player player = new Player("", PlayerType.PLAYER1, board);

    assertThrows(IllegalStateException.class, () -> player.turn(0));
  }

  @Test
  void testTurnInvalidHouseNumber() {
    Board board = new Board(6, 4);
    Player player = new Player("", PlayerType.PLAYER1, board);

    assertThrows(IllegalArgumentException.class, () -> player.turn(-1));
    assertThrows(IllegalArgumentException.class, () -> player.turn(6));
  }

  @Test
  void testTakeOpposite() {
    Board board = new Board(6, 4);
    Player player1 = new Player("", PlayerType.PLAYER1, board);
    Player player2 = new Player("", PlayerType.PLAYER2, board);

    board.getHouse(PlayerType.PLAYER1, 0).take();
    board.getHouse(PlayerType.PLAYER1, 0).add();
    board.getHouse(PlayerType.PLAYER1, 1).take();

    Store lastStore = player1.turn(0);

    assertEquals(0, board.getHouse(PlayerType.PLAYER1, 0).getBeans());
    assertEquals(0, board.getHouse(PlayerType.PLAYER1, 1).getBeans());
    assertEquals(0, board.getHouse(PlayerType.PLAYER2, 4).getBeans());
    assertEquals(5, player1.getKalah().getBeans());
  }
}