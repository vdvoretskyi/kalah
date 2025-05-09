package org.kalah.rules;

import java.util.List;
import org.kalah.model.Board;
import org.kalah.model.House;
import org.kalah.model.PlayerType;

public class NormalKalahRule extends KalahRule {

  NormalKalahRule(PlayerType playerType) {
    super(playerType);
  }

  @Override
  public int move(List<House> houses) {
    int maxBeans = Integer.MIN_VALUE;
    int selectedHouse = -1;

    for (House house : houses) {
      int beans = house.getBeans();
      if (beans > 0 && beans > maxBeans) {
        maxBeans = beans;
        selectedHouse = house.getIndex();
      }
    }

    return selectedHouse;
  }

  @Override
  public String toString() {
    return "Normal Player";
  }
}