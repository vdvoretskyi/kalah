package org.kalah.rules;

import java.util.List;
import org.kalah.model.House;
import org.kalah.model.PlayerType;

public class UserKalahRule extends KalahRule {

  private final UserInput userInput;

  public UserKalahRule(PlayerType playerType, UserInput userInput) {
    super(playerType);
    this.userInput = userInput;
  }

  @Override
  public int move(List<House> houses) {
    while(true) {
      var index = userInput.getHouseNumber();
      if (index < 1 || index > houses.size()) {
        userInput.printError("Invalid index. Please enter a number between 0 and " + houses.size());
        continue;
      }
      index--;
      if (houses.get(index).isEmpty()) {
        userInput.printError("House is empty. Please choose a non-empty house: ");
        continue;
      }
      return index;
    }
  }

    @Override
    public String toString() {
        return "User Player";
    }
}
