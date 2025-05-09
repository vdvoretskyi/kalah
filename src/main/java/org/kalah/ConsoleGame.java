package org.kalah;

import java.util.Scanner;
import org.kalah.model.Game;
import org.kalah.model.PlayerType;
import org.kalah.rules.KalahRule;
import org.kalah.ui.ConsoleBoardPrinter;

public class ConsoleGame {

  private final Game game;
  private final ConsoleBoardPrinter printer;
  private final KalahRule userRule;
  private final KalahRule computerRule;

  private final Scanner scanner = new Scanner(System.in);

  public ConsoleGame(Game game, KalahRule userRule, KalahRule computerRule) {
    this.game = game;
    this.printer = new ConsoleBoardPrinter();
    this.userRule = userRule;
    this.computerRule = computerRule;
  }

  public void start() {
    while (game.getStatus() == Game.GameStatus.IN_PROGRESS) {
      printer.print(game.getBoard());
      System.out.println("Current player: " + game.getCurrentPlayer());

      if (game.getCurrentPlayer().getPlayerType() == PlayerType.PLAYER1) {
        int houseIndex = userRule.move(game.getBoard().getHouses(PlayerType.PLAYER1));
        game.move(houseIndex);
      } else {
        System.out.println("Computer's turn... Press any key");
        scanner.nextLine();
        int houseIndex = computerRule.move(game.getBoard().getHouses(PlayerType.PLAYER2));
        System.out.println("Computer selects house: " + (houseIndex + 1));
        game.move(houseIndex);
      }
    }

    printer.print(game.getBoard());
    System.out.println("Game Over! Result: " + game.getStatus());
  }
}