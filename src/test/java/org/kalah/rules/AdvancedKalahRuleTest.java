package org.kalah.rules;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.kalah.model.Board;
import org.kalah.model.House;
import org.kalah.model.PlayerType;

class AdvancedKalahRuleTest {

  @Test
  void testAdvancedKalahRule() {
    Board board = new Board(2, 3);
    AdvancedKalahRule rule = new AdvancedKalahRule(PlayerType.PLAYER1);

    int selectedHouse = rule.move(board.getHouses(PlayerType.PLAYER1));
    List<House> houses = board.getHouses(PlayerType.PLAYER1);
    assertTrue(houses.stream().anyMatch(h -> h.getIndex() == selectedHouse));
  }
}