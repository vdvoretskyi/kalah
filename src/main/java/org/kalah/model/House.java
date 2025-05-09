package org.kalah.model;

public final class House extends Store {

    House(int index, PlayerType playerType, int beans) {
        super(index, playerType, beans);
    }

    @Override
    public int take() {
        int beans = this.beans;
        this.beans = 0;
        return beans;
    }

    @Override
    public boolean isSowable(final PlayerType playerType) {
        return true;
    }

    @Override
    public boolean mightTakeOpposite(final PlayerType playerType) {
        return getBeans() == 1 && getOwner().equals(playerType);
    }

}
