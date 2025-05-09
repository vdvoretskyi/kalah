package org.kalah.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

  private final Store[] stores;
  private final int size;


  public Board(int size, int beans) {
    if (size < 1) {
      throw new IllegalArgumentException("Size must be greater than 0");
    }
    if (beans < 0) {
      throw new IllegalArgumentException("Beans must be non-negative");
    }

    this.size = size;
    this.stores = new Store[size * 2 + 2];

    for (int i = 0; i < size; i++) {
      stores[i] = new House(i, PlayerType.PLAYER1, beans);
      stores[i + size + 1] = new House(i, PlayerType.PLAYER2, beans);
    }

    stores[size] = new Kalah(size, PlayerType.PLAYER1);
    stores[stores.length - 1] = new Kalah(size, PlayerType.PLAYER2);
  }

  public int getSize() {
    return size;
  }

  public List<House> getHouses(PlayerType playerType) {
    List<House> houses = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      houses.add(getHouse(playerType, i));
    }
    return houses;
  }

  public House getHouse(PlayerType playerType, int num) {
    if (num < 0 || num >= size) {
      throw new IllegalArgumentException("Invalid house number: " + num);
    }

    if (playerType == PlayerType.PLAYER1) {
      return (House) stores[num];
    } else if (playerType == PlayerType.PLAYER2) {
      return (House) stores[size + num + 1];
    }

    throw new IllegalArgumentException("Invalid player type: " + playerType);
  }

  public Kalah getKalah(PlayerType playerType) {
    if (playerType == PlayerType.PLAYER1) {
      return (Kalah) stores[size];
    } else if (playerType == PlayerType.PLAYER2) {
      return (Kalah) stores[stores.length - 1];
    }

    throw new IllegalArgumentException("Invalid player type: " + playerType);
  }

  public Store getNextStore(Store store) {
    var index = 0;
    if (store.getOwner() == PlayerType.PLAYER2) {
      index = size + 1;
    }
    int next = (index + store.getIndex() + 1) % stores.length;
    return stores[next];
  }

  public House getOppositeHouse(House house) {
    PlayerType oppositePlayerType = PlayerType.getOpponent(house.getOwner());
    return getHouse(oppositePlayerType, size - house.getIndex() - 1);
  }

  @Override
  public String toString() {
    return "Board{" +
        "stores=" + Arrays.toString(stores) +
        ", size=" + size +
        '}';
  }
}
