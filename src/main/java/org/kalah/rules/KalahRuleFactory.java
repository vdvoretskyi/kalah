package org.kalah.rules;

import org.kalah.model.Board;
import org.kalah.model.PlayerType;

public class KalahRuleFactory {

  public static KalahRule createRule(String level, PlayerType playerType) {
    return switch (level.toLowerCase()) {
      case "novice" -> new NoviceKalahRule(playerType);
      case "normal" -> new NormalKalahRule(playerType);
      case "advanced" -> new AdvancedKalahRule(playerType);
      default -> throw new IllegalArgumentException("Invalid level: " + level);
    };
  }
}