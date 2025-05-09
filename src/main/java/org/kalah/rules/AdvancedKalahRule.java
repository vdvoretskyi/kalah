package org.kalah.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.kalah.model.House;
import org.kalah.model.PlayerType;

public class AdvancedKalahRule extends KalahRule {

  private final Random random = new Random();

  AdvancedKalahRule(PlayerType playerType) {
    super(playerType);
  }

  @Override
  public int move(List<House> houses) {
    List<Integer> validHouses = new ArrayList<>();

    for (House house : houses) {
      if (!house.isEmpty()) {
        validHouses.add(house.getIndex());
      }
    }

    return validHouses.get(random.nextInt(validHouses.size()));
  }

  @Override
  public String toString() {
    return "Advanced Player";
  }
}