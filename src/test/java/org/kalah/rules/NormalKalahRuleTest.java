package org.kalah.rules;

import org.junit.jupiter.api.Test;
import org.kalah.model.Board;
import org.kalah.model.PlayerType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalKalahRuleTest {

  @Test
  void testNormalKalahRule() {
    Board board = new Board(2, 3);
    NormalKalahRule rule = new NormalKalahRule(PlayerType.PLAYER1);

    int selectedHouse = rule.move(board.getHouses(PlayerType.PLAYER1));
    assertEquals(0, selectedHouse);

    board.getHouses(PlayerType.PLAYER1).get(1).add();
    selectedHouse = rule.move(board.getHouses(PlayerType.PLAYER1));
    assertEquals(1, selectedHouse);
  }
}