package org.kalah.model;

public abstract class Store {

    private final int index;
    protected int beans;
    private final PlayerType owner;

    Store(int index, PlayerType owner, int beans) {
        this.index = index;
        this.owner = owner;
        this.beans = beans;
    }

    public int getIndex() {
        return index;
    }

    public PlayerType getOwner() {
        return owner;
    }

    public int getBeans() {
        return beans;
    }

    public void add() {
        this.beans++;
    }

    public int take() {
        return 0;
    }

    public boolean isEmpty() {
        return beans == 0;
    }

    public abstract boolean isSowable(PlayerType playerType);
    public abstract boolean mightTakeOpposite(PlayerType playerType);

    @Override
    public String toString() {
        return "Store{" +
            "index=" + index +
            ", beans=" + beans +
            ", owner=" + owner +
            '}';
    }
}
