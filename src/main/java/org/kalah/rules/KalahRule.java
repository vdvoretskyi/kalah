package org.kalah.rules;

import java.util.List;
import org.kalah.model.House;
import org.kalah.model.PlayerType;

public abstract class KalahRule {

  protected final PlayerType playerType;

  public KalahRule(final PlayerType playerType) {
    this.playerType = playerType;
  }

  public abstract int move(List<House> houses);
}
