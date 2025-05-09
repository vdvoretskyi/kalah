package org.kalah.model;

public class Game {

  public enum GameStatus {
    IN_PROGRESS,
    PLAYER1_WON,
    PLAYER2_WON,
    DRAW
  }

  private final Board board;
  private final Player player1;
  private final Player player2;

  private Player currentPlayer;
  private GameStatus status = GameStatus.IN_PROGRESS;

  private Game(int size, int beans) {
    this.board = new Board(size, beans);
    //TODO: remove hardcoded player names
    this.player1 = new Player("User", PlayerType.PLAYER1, board);
    this.player2 = new Player("Computer", PlayerType.PLAYER2, board);
    this.currentPlayer = player1;
  }

  public static Game create(int size, int beans) {
    return new Game(size, beans);
  }

  public static Game create() {
    return new Game(6, 6);
  }

  public Board getBoard() {
    return board;
  }

  public GameStatus getStatus() {
    return status;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void move(int houseIndex) {
    if (isGameOver()) {
      throw new IllegalStateException("Game is over. No more moves allowed.");
    }
    Store store = currentPlayer.turn(houseIndex);
    if (currentPlayer.isCompleted()) {
      getOtherPlayer().finish();
      status = determineWinner();
      return;
    }
    currentPlayer = nextPlayer(store);
  }

  private boolean isGameOver() {
    return player1.isCompleted() || player2.isCompleted();
  }

  public Player nextPlayer(Store store) {
    if (store.equals(currentPlayer.getKalah())) {
      return currentPlayer;
    }
    return getOtherPlayer();
  }

  private GameStatus determineWinner() {
    int score1 = player1.score();
    int score2 = player2.score();
    if (score1 > score2) {
      return GameStatus.PLAYER1_WON;
    } else if (score2 > score1) {
      return GameStatus.PLAYER2_WON;
    }
    return GameStatus.DRAW;
  }

  private Player getOtherPlayer() {
    return currentPlayer == player1 ? player2 : player1;
  }


}
