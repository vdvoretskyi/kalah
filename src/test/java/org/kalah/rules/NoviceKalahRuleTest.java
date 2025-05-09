package org.kalah.rules;

import org.junit.jupiter.api.Test;
import org.kalah.model.Board;
import org.kalah.model.PlayerType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoviceKalahRuleTest {

  @Test
  void testNoviceKalahRule() {
    Board board = new Board(2, 3);
    NoviceKalahRule rule = new NoviceKalahRule(PlayerType.PLAYER1);

    board.getHouses(PlayerType.PLAYER1).get(0).take();
    assertEquals(1, rule.move(board.getHouses(PlayerType.PLAYER1)));

    board.getHouses(PlayerType.PLAYER1).get(0).add();
    assertEquals(0, rule.move(board.getHouses(PlayerType.PLAYER1)));
  }
}