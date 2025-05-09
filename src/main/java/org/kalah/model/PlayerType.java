package org.kalah.model;

public enum PlayerType {

    PLAYER1,
    PLAYER2,
    NONE;

    public static PlayerType getOpponent(PlayerType player) {
        return player == PLAYER1 ? PLAYER2 : PLAYER1;
    }
}
