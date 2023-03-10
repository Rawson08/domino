public class DominoConsole {

    Board board = new Board();
//    Gameplay gameplay = new Gameplay();
    DominoGame dominoGame = new DominoGame();
//    Domino domino = new Domino();

    public static void main(String[] args) {
        DominoGame.initializeGame();
        while (true) {
            DominoGame.displayGameState();
            if (DominoGame.isGameOver()) {
                break;
            }
            if (DominoGame.humanTurn) {
                DominoGame.handleHumanTurn();
            } else {
                DominoGame.handleComputerTurn();
            }
        }
        DominoGame.displayWinner();
    }

//    private static GameSetup gameSetup;
//    public static void main(String[] args) {
//         gameSetup = new GameSetup();
//         gameSetup.drawStartingHand();
//    }
}

