package org.kalah;

import java.util.Scanner;
import org.kalah.model.Game;
import org.kalah.model.PlayerType;
import org.kalah.rules.KalahRule;
import org.kalah.rules.KalahRuleFactory;
import org.kalah.rules.UserInput;
import org.kalah.rules.UserKalahRule;
import org.kalah.ui.ConsoleUserInput;

public class ConsoleGameRunner {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    final Game game = Game.create();

    System.out.println("Select a rule (novice, normal, advanced):");
    String rule = scanner.nextLine().toLowerCase();
    KalahRule computerRule = null;
    while ((computerRule = makeComputerRule(rule)) == null) {
      System.out.println("Invalid rule. Please select again (novice, normal, advanced):");
      rule = scanner.next().toLowerCase();
    }
    UserInput userInput = new ConsoleUserInput();
    KalahRule userRule = new UserKalahRule(PlayerType.PLAYER1, userInput);

    ConsoleGame consoleGame = new ConsoleGame(game, userRule, computerRule);
    consoleGame.start();
  }

  private static KalahRule makeComputerRule(String rule) {
    try {
      return KalahRuleFactory.createRule(rule, PlayerType.PLAYER2);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}