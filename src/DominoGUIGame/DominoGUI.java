package DominoGUIGame;


import DominoConsoleGame.DominoGame;
import static DominoConsoleGame.Domino.*;
import DominoConsoleGame.Domino;
import DominoConsoleGame.DominoGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static DominoGUIGame.DominoTileGUI.*;


public class DominoGUI extends Application {

    public DominoGUI(){
//        DominoConsoleGame.DominoGame dominoGame = new DominoConsoleGame.DominoGame();
    }
    private static VBox root;
    private final int screenWidth = 1280;
    private final int screenHeight =screenWidth - (screenWidth / 2);
    private static VBox gameBoard;

    private DominoGame game;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Domino Game");
        root = new VBox();
        Scene scene = new Scene(root, screenWidth, screenHeight);
//        Domino domino = new Domino(1,2);
//        ImageView imageView = handleTileGUI(domino);
//        root.getChildren().add(imageView);

        runGame();
        stage.setScene(scene);
        stage.show();
    }

    public static void runGame(){
        DominoGame dominoGame = new DominoGame();
        DominoGame.initializeGame();
        while (true){
            displayGameBoard(DominoGame.gameBoard);
//            game.displayGameState();
            if (DominoGame.checkGameOver()){
                break;
            }
            if (DominoGame.humanTurn){
                DominoGame.handleHumanTurn();
            } else {
                DominoGame.handleComputerTurn();
            }
        }
        DominoGame.displayWinner();
    }

    /**
     * This method runs through all the elements of gameBoard and add the respective tiles to the root
     * @param gameBoardArray
     */
    private static void displayGameBoard(ArrayList<Domino> gameBoardArray){
        gameBoard = new VBox();
        for (Domino value : gameBoardArray) {
            int left = value.getLeft();
            int right = value.getRight();
            Domino domino = new Domino(left, right);
//            ImageView imageView = handleTileGUI(domino);
//            root.getChildren().add(imageView);
        }
    }
}
