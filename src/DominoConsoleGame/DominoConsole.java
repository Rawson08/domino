package DominoConsoleGame;

/* Domino Game Project
 * Name: Roshan Subedi
 * Class: CS351 Project 3 Assignment
 * Description: This is the main class for the Console version of the game.
 */

import static DominoConsoleGame.DominoGame.*;

public class DominoConsole {
    public static void main(String[] args) {
        initializeGame();
        while (true) {
            displayGameState();
            if (checkGameOver()) {
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
}
