package game.myStrategy.game.diplomacy;

public class Diplomacy {
    private Player currentPlayer;
    private final int MAX_PLAYER = 2;
    private boolean diplomacy[][] = new boolean[MAX_PLAYER][MAX_PLAYER]; //Is enemy
    public Diplomacy() {
        for (int p1 = 0; p1 < MAX_PLAYER; p1++) {
            for (int p2 = 0; p2 < MAX_PLAYER; p2++) {
                diplomacy[p1][p2] = p1 != p2;
            }
        }
    }

    public boolean isEnemy(Player p1, Player p2) {
        if (p1.player == p2.player) return false;
        else return diplomacy[p1.player][p2.player];
    }

    public boolean isEnemyForCurrent(Player p) {
        return isEnemy(getCurrentPlayer(), p);
    }

    public Player getCurrentPlayer() {
        if (currentPlayer == null) setCurrentPlayer(new Player(0));
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player create(int i) {
        Player player = new Player(i);
        player.setDiplomacy(this);
        return player;
    }
}
