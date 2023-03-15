package DominoConsoleGame;

/* Domino Game Project
 * Name: Roshan Subedi
 * Class: CS351 Project 3 Assignment
 * Description: This is the main class for the Console version of the game.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

import static DominoConsoleGame.DominoGame.*;
//import static DominoGUIGame.DominoTileGUI.handleTileGUI;

public class DominoMain extends Application {

    private static VBox root;
    private final int screenWidth = 1280;
    private final int screenHeight =screenWidth - (screenWidth / 2);
    private static VBox gameBoardVBox;
    private DominoGame game;
    private Image[] tileImages;
    public static void main(String[] args) {
        Thread consoleThread = new Thread(DominoMain::startGame);

        Thread guiThread = new Thread(() -> launch(args));
        consoleThread.start();
        guiThread.start();
    }

    @Override
    public void start(Stage stage) throws Exception{



//        ImageView tile = new ImageView();

        importTileImages();
        GridPane gridPane = new GridPane();

        int rowIndex = 0;
        int colIndex = 0;
        for (int i = 0; i < 28; i++){
            ImageView imageView = new ImageView(tileImages[i]);
//            imageView.setFitWidth(50);
//            imageView.setFitHeight(100);
            gridPane.add(imageView, colIndex, rowIndex);

            colIndex++;
            if (colIndex == 7){
                colIndex = 0;
                rowIndex++;
            }
        }

        Scene scene = new Scene(gridPane, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
//
//        BorderPane borderPane = new BorderPane();
//        VBox gameBoardVBox = createGameBoard(DominoGame.gameBoard);
//        borderPane.setCenter(gameBoardVBox);
////        startGame();
//        // add other nodes to the borderPane as desired
//        Scene scene = new Scene(borderPane);
//        stage.setScene(scene);
//        stage.show();
////        stage.setTitle("Domino Game");
        root = new VBox();
////        Scene scene = new Scene(root, screenWidth, screenHeight);
//////        Domino domino = new Domino(1,2);
//////        ImageView imageView = handleTileGUI(domino);
//////        root.getChildren().add(imageView);
////            startGame();
////
////        stage.setScene(scene);
////        stage.show();
    }

    private static void startGame(){
        initializeGame();
        while (true) {
            DominoGame.displayGameState();
//            createGameBoard(gameBoard);
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

    private void importTileImages(){
        tileImages = new Image[28];
        for (int i = 0; i < 7; i++){
            for (int j= i; j < 7; j++){
                int index = i * 7 + j - (i * (i  + 1) / 2);
                tileImages[index] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/" + i + "-" + j + ".png")));
            }
        }
    }
    public static VBox createGameBoard(ArrayList<Domino> gameBoard) {
        VBox vBox = new VBox();
        for (Domino domino : gameBoard) {
            HBox hBox = new HBox();
            Label label1 = new Label(Integer.toString(domino.getLeft()));
            Label label2 = new Label(Integer.toString(domino.getRight()));
            hBox.getChildren().addAll(label1, label2);
            vBox.getChildren().add(hBox);
        }
        return vBox;
    }

    private static void displayGameBoard(){
        gameBoardVBox.getChildren().clear();
        for (Domino value : DominoGame.gameBoard) {
            int left = value.getLeft();
            int right = value.getRight();
            Domino domino = new Domino(left, right);
//            ImageView imageView = handleTileGUI(domino);
//            gameBoardVBox.getChildren().add(imageView);
        }
    }

}
