package DominoConsoleGame;

import static DominoConsoleGame.DominoGame.*;

public class DominoConsole {

//    Board board = new Board();
//    Gameplay gameplay = new Gameplay();
//    DominoConsoleGame.Domino domino = new DominoConsoleGame.Domino();

    public static void main(String[] args) {
        initializeGame();
        while (true) {
            displayGameState();
            if (isGameOver()) {
                break;
            }
            if (humanTurn) {
                handleHumanTurn();
            } else {
                handleComputerTurn();
            }
        }
        displayWinner();
    }

//    private static GameSetup gameSetup;
//    public static void main(String[] args) {
//         gameSetup = new GameSetup();
//         gameSetup.drawStartingHand();
//    }
}

