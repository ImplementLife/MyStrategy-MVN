package game.myStrategy.game.map;

import java.util.ArrayList;

public class Tile {
    private static final ArrayList<Tile> typeFilter;

    static {
        typeFilter = new ArrayList<>();

        typeFilter.add(new Tile());
        typeFilter.get(0).height = -0.0;
        typeFilter.get(0).typeTile = TypeTile.WATER;

        typeFilter.add(new Tile());
        typeFilter.get(1).height = 1.0;
        typeFilter.get(1).typeTile = TypeTile.GRASS;
    }

    //========================================//
    private TypeTile typeTile;
    private TypeTile newTypeTile;
    private String subTile = null;
    private double height;

    //========================================//
    private Tile() {}
    public Tile(Double height) {
        this.height = height;
        setTypeTile();
        newTypeTile = typeTile;
    }
    public Tile(TypeTile type, float height) {
        this.height = height;
        this.typeTile = type;
    }

    //========================================//
    public TypeTile getType() {
        return typeTile;
    }
    public void setTypeTile(TypeTile type) {
        newTypeTile = type;
    }
    public void resetTypeTile() {
        typeTile = newTypeTile;
    }
    private void setTypeTile() {
        for (Tile tile : typeFilter) {
            if (this.height <= tile.height) {
                this.typeTile = tile.typeTile;
                break;
            }
        }
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    public String getSubTile() {
        return subTile;
    }
    public void setSubTile(String subTile) {
        this.subTile = subTile;
    }
    public void removeSubTile() {
        subTile = null;
    }
}
