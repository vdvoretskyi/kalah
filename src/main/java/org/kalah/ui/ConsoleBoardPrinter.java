package org.kalah.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.kalah.model.Board;
import org.kalah.model.House;
import org.kalah.model.PlayerType;
import org.kalah.model.Store;

public class ConsoleBoardPrinter implements BoardPrinter {

  private static final String TEMPLATE = """
                          Computer
           | %02d | %02d | %02d | %02d | %02d | %02d |
      (%02d)                                 (%02d)
           | %02d | %02d | %02d | %02d | %02d | %02d |
                            You
      """;

  @Override
  public void print(final Board board) {
    List<House> player2Houses = board.getHouses(PlayerType.PLAYER2);
    Collections.reverse(player2Houses);
    List<Store> stores = new ArrayList<>(player2Houses);
    stores.add(board.getKalah(PlayerType.PLAYER2));
    stores.add(board.getKalah(PlayerType.PLAYER1));
    stores.addAll(board.getHouses(PlayerType.PLAYER1));

    String dashboard = String.format(TEMPLATE, stores.stream()
        .map(Store::getBeans)
        .toList()
        .toArray());
    System.out.println(dashboard);
  }
}
