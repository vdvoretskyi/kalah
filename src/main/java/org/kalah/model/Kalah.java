package org.kalah.model;

public final class Kalah extends Store {

    Kalah(int index, PlayerType playerType) {
        super(index, playerType, 0);
    }

    public void add(int beans) {
        this.beans += beans;
    }

    @Override
    public boolean isSowable(final PlayerType playerType) {
        return playerType == this.getOwner();
    }

    @Override
    public boolean mightTakeOpposite(final PlayerType playerType) {
        return false;
    }

}
