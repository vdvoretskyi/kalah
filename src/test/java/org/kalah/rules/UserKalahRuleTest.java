package org.kalah.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kalah.model.Board;
import org.kalah.model.House;
import org.kalah.model.PlayerType;

class UserKalahRuleTest {

  private UserInput mockUserInput;
  private UserKalahRule userKalahRule;
  private Board board;

  @BeforeEach
  void setUp() {
    mockUserInput = mock(UserInput.class);
    userKalahRule = new UserKalahRule(PlayerType.PLAYER1, mockUserInput);
    board = new Board(2, 3);
  }

  @Test
  void testMove_ValidInput() {
    when(mockUserInput.getHouseNumber()).thenReturn(1);

    int result = userKalahRule.move(board.getHouses(PlayerType.PLAYER1));

    assertEquals(0, result);
    verify(mockUserInput, times(1)).getHouseNumber();
  }

  @Test
  void testMove_InvalidIndex() {
    when(mockUserInput.getHouseNumber())
        .thenReturn(0)
        .thenReturn(7)
        .thenReturn(2);

    int result = userKalahRule.move(board.getHouses(PlayerType.PLAYER1));

    assertEquals(1, result);
    verify(mockUserInput, times(3)).getHouseNumber();
    verify(mockUserInput, times(2)).printError(anyString());
  }

  @Test
  void testMove_EmptyHouse() {
    List<House> houses = board.getHouses(PlayerType.PLAYER1);
    houses.get(1).take();

    when(mockUserInput.getHouseNumber())
        .thenReturn(2)
        .thenReturn(1);

    int result = userKalahRule.move(houses);

    assertEquals(0, result);
    verify(mockUserInput, times(2)).getHouseNumber();
    verify(mockUserInput, times(1)).printError("House is empty. Please choose a non-empty house: ");
  }
}