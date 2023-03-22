package game.myStrategy.ui.game.gamePanel.states;

public enum State {
    IN_MAIN_MENU {
        public IN_MAIN_MENU inner_state;
        enum IN_MAIN_MENU {
            IN_SETTINGS,
            IN_GALLERY,
            NONE
        }
    },
    IN_GAME,
    IN_GAME_MENU

}
