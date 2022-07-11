package sk.stuba.fei.uim.oop.cards.duckAndWater;

public abstract class DuckAndWater {
    protected final String owner;
    protected final int duckID;

    protected DuckAndWater(String owner, int duckID) {
        this.owner = owner;
        this.duckID = duckID;
    }
    public String getOwner() {
        return owner;
    }
    public int getDuckID() {
        return duckID;
    }

}
