package org.kalah.model;

public class Player {

  private final String name;
  private final PlayerType playerType;
  private final Board board;

  public Player(String name, PlayerType playerType, Board board) {
    this.name = name;
    this.playerType = playerType;
    this.board = board;
  }

  public PlayerType getPlayerType() {
    return playerType;
  }

  Kalah getKalah() {
    return board.getKalah(playerType);
  }

  public boolean isCompleted() {
    for (House house : board.getHouses(playerType)) {
      if (house.getBeans() > 0) {
        return false;
      }
    }
    return true;
  }

  public void finish() {
    Kalah kalah = board.getKalah(playerType);
    for (House house : board.getHouses(playerType)) {
      kalah.add(house.take());
    }
  }

  public int score() {
    return board.getKalah(playerType).getBeans();
  }

  public Store turn(int num) {
    if (num < 0 || num > board.getSize()) {
      throw new IllegalArgumentException("Invalid house number");
    }

    House house = board.getHouse(playerType, num);
    checkHouseNotEmpty(house);
    Store store = doTurn(house);
    if (shouldTakeOpposite(store)) {
      Store opposite = board.getOppositeHouse((House)store);
      int beans = store.take() + opposite.take();
      board.getKalah(playerType).add(beans);
    }

    return store;
  }

  private Store doTurn(House house) {
    int beans = house.take();
    Store store = house;
    while (beans > 0) {
      store = board.getNextStore(store);
      if (store.isSowable(playerType)) {
        store.add();
        beans--;
      }
    }
    return store;
  }

  private void checkHouseNotEmpty(House house) {
    if (house.isEmpty()) {
      throw new IllegalStateException("House must have beans to take turn!");
    }
  }

  private boolean shouldTakeOpposite(Store store) {
    if (store.mightTakeOpposite(playerType)) {
      Store opposite = board.getOppositeHouse((House)store);
      return !opposite.isEmpty();
    }
    return false;
  }

  @Override
  public String toString() {
    return name;
  }
}
