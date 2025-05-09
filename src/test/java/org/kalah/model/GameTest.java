package org.kalah.model;

import org.junit.jupiter.api.Test;
import org.kalah.model.*;
import org.kalah.model.Game.GameStatus;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  @Test
  void testGameEndsWithPlayer2Win() {
    Game game = Game.create(2, 1);
    assertEquals(Game.GameStatus.IN_PROGRESS, game.getStatus());
    assertEquals(PlayerType.PLAYER1, game.getCurrentPlayer().getPlayerType());

    game.move(0);
    assertEquals(Game.GameStatus.IN_PROGRESS, game.getStatus());
    assertEquals(PlayerType.PLAYER2, game.getCurrentPlayer().getPlayerType());

    game.move(0);
    assertEquals(Game.GameStatus.IN_PROGRESS, game.getStatus());
    assertEquals(PlayerType.PLAYER1, game.getCurrentPlayer().getPlayerType());

    game.move(1);
    assertEquals(GameStatus.PLAYER2_WON, game.getStatus());

    assertTrue(game.getBoard().getHouses(PlayerType.PLAYER1).stream().allMatch(House::isEmpty));
    assertTrue(game.getBoard().getHouses(PlayerType.PLAYER2).stream().allMatch(House::isEmpty));
    assertEquals(1, game.getBoard().getKalah(PlayerType.PLAYER1).getBeans());
    assertEquals(3, game.getBoard().getKalah(PlayerType.PLAYER2).getBeans());
  }

  @Test
  void testCreateGame() {
    Game game = Game.create(3, 2);
    assertNotNull(game);
    assertEquals(Game.GameStatus.IN_PROGRESS, game.getStatus());
    assertEquals(3, game.getBoard().getHouses(PlayerType.PLAYER1).size());
    assertEquals(2, game.getBoard().getHouses(PlayerType.PLAYER1).get(0).getBeans());
  }

  @Test
  void testGetBoard() {
    Game game = Game.create(2, 1);
    assertNotNull(game.getBoard());
    assertEquals(2, game.getBoard().getHouses(PlayerType.PLAYER1).size());
    assertEquals(1, game.getBoard().getHouses(PlayerType.PLAYER1).get(0).getBeans());
  }

  @Test
  void testGetStatus() {
    Game game = Game.create(2, 1);
    assertEquals(Game.GameStatus.IN_PROGRESS, game.getStatus());
    game.move(0);
    game.move(0);
    game.move(1);
    assertTrue(game.getStatus() == Game.GameStatus.PLAYER1_WON || game.getStatus() == Game.GameStatus.PLAYER2_WON);
  }

  @Test
  void testGetCurrentPlayer() {
    Game game = Game.create(2, 1);
    assertEquals(PlayerType.PLAYER1, game.getCurrentPlayer().getPlayerType());
    game.move(0);
    assertEquals(PlayerType.PLAYER2, game.getCurrentPlayer().getPlayerType());
  }

  @Test
  void testMoveThrowsWhenGameOver() {
    Game game = Game.create(2, 1);
    game.move(0);
    game.move(0);
    game.move(1);
    assertThrows(IllegalStateException.class, () -> game.move(0));
  }

  @Test
  void testNextPlayer() {
    Game game = Game.create(2, 1);
    game.move(0);
    assertEquals(PlayerType.PLAYER2, game.getCurrentPlayer().getPlayerType());
    game.move(0);
    assertEquals(PlayerType.PLAYER1, game.getCurrentPlayer().getPlayerType());
  }

  @Test
  void testLastStoreIsKalahCurrentUserGetsNextMove() {
    Game game = Game.create(2, 2);
    assertEquals(PlayerType.PLAYER1, game.getCurrentPlayer().getPlayerType());

    game.move(0);

    assertEquals(PlayerType.PLAYER1, game.getCurrentPlayer().getPlayerType());
    assertEquals(Game.GameStatus.IN_PROGRESS, game.getStatus());
  }

  @Test
  void testDetermineWinner() {
    Game game = Game.create(2, 1);
    game.move(0);
    game.move(0);
    game.move(1);
    assertTrue(game.getStatus() == Game.GameStatus.PLAYER1_WON || game.getStatus() == Game.GameStatus.PLAYER2_WON);
  }

}