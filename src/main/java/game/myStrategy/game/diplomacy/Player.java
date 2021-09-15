package game.myStrategy.game.diplomacy;

public class Player {

//    private static final int MAX_PLAYER = 2;
//    private static boolean diplomacy[][] = new boolean[MAX_PLAYER][MAX_PLAYER]; //Is enemy
//    static {
//        for (int p1 = 0; p1 < MAX_PLAYER; p1++) {
//            for (int p2 = 0; p2 < MAX_PLAYER; p2++) {
//                diplomacy[p1][p2] = p1 != p2;
//            }
//        }
//    }
//
//    public static boolean isEnemy(Player p1, Player p2) {
//        if (p1.player == p2.player) return false;
//        else return diplomacy[p1.player][p2.player];
//    }
//
//    public static boolean isEnemyForCurrent(Player p) {
//        return isEnemy(getCurrentPlayer(), p);
//    }
//
//    private static Player currentPlayer;
//    public static Player getCurrentPlayer() {
//        if (currentPlayer == null) setCurrentPlayer(new Player(0));
//        return currentPlayer;
//    }
//
//    public static void setCurrentPlayer(Player currentPlayer) {
//        Player.currentPlayer = currentPlayer;
//    }
    //===================================//

    private Diplomacy diplomacy;
    int player;
    Player(int player) {
        this.player = player;
    }
    public boolean isEnemy(Player p) {
        return diplomacy.isEnemy(this, p);
    }
    public boolean isEnemy() {
        return diplomacy.isEnemy(this, diplomacy.getCurrentPlayer());
    }

    public void setPlayer(int player) {
        this.player = player;
    }
    public void setPlayer(Player player) {
        this.player = player.player;
    }

    public void setDiplomacy(Diplomacy diplomacy) {
        this.diplomacy = diplomacy;
    }
}
