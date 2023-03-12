package DominoConsoleGame;

/* Domino Game Project
 * Name: Roshan Subedi
 * Class: CS351 Project 3 Assignment
 * Description: This is a project for DominoConsoleGame.Domino Game, and this file contains the implementation of the
 * game using System input output rather than a graphical interface (JavaFX). There are two implementation
 * requirements for this project and this is the first one. Other implementation will be based on JavaFX
 * using the same base game.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class DominoGame {
    private static final ArrayList<Domino> boneyard = new ArrayList<>();
    private static final ArrayList<Domino> gameBoard = new ArrayList<>();
    private static final ArrayList<Domino> computerHand = new ArrayList<>();
    private static final ArrayList<Domino> humanHand = new ArrayList<>();
    static boolean humanTurn;
    private static boolean computerCanPlay = false;
    private static boolean firstTile = true;
    private static boolean wildCardPlay = false;

    /**
     * Initializing game with 28 tiles and 7 tiles for each players
     */
    public static void initializeGame() {
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                boneyard.add(new Domino(i, j));
            }
        }
        shuffleBoneyard();
        //Adding 7 tiles for each player
        for (int i = 0; i < 7; i++) {
            computerHand.add(drawFromBoneyard());
            humanHand.add(drawFromBoneyard());
        }
        humanTurn = true;
    }

    /**
     * Method to shuffle boneyard used by initializeGame method
     */
    private static void shuffleBoneyard() {
        for (int i = boneyard.size() - 1; i > 0; i--) {
            int j = (int) Math.floor(Math.random() * (i + 1));
            Domino temp = boneyard.get(i);
            boneyard.set(i, boneyard.get(j));
            boneyard.set(j, temp);
        }
    }

    /**
     * Method to distribute bones and delete them from boneyard
     * @return domino from index 0
     */
    private static Domino drawFromBoneyard() {
        if (boneyard.isEmpty()) {
            return null;
        }

        Domino domino = boneyard.get(0);
        boneyard.remove(0);
        return domino;
    }

    /**
     * Method to display current game state
     */
    static void displayGameState() {
        String display = "Computer has " + computerHand.size() + " dominos\n" +
                "Boneyard contains " + boneyard.size() + " dominos\n";
        printGameBoard();
        String[] player = {"Human's turn", "Computer's turn"};
        System.out.println(display);
        if (humanTurn) {
            System.out.println(player[0]);
        } else {
            System.out.println(player[1]);
        }
    }

    /**
     * Method to display the current Game Board
     */
    private static void printGameBoard(){
        StringBuilder firstLine = new StringBuilder();
        StringBuilder secondLine = new StringBuilder();
        int counter = 0;
        for (Domino domino: gameBoard){
            if (counter == 0){
                firstLine.append(domino);
                counter++;
            } else if (counter == 1) {
                secondLine.append(domino);
                counter--;
            }
        }
        System.out.println(firstLine);
        System.out.println("  " + secondLine);
    }


    /**
     * Method to handle human's turn
     */
    static void handleHumanTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tray: " + humanHand);
        System.out.println("[p] Play Domino");
        System.out.println("[d] Draw from boneyard");
        System.out.println("[q] Quit");
        String input = scanner.nextLine();
        switch (input) {
            case "p" -> handlePlayDomino(scanner);
            case "d" -> handleDrawFromBoneyard();
            case "q" -> System.exit(0);
            default -> {
                System.out.println("Invalid input");
                handleHumanTurn();
            }
        }
    }

    /**
     * Handle exception if user inputs anything except number
     * @param s taken as Double
     * @return b
     */
    public static boolean isDigit(String s){
        boolean b;
        try{
            Double.parseDouble(s);
            b = true;
        }catch (NumberFormatException e){
            b = false;
        }return b;
    }

    /**
     * Method to handle domino play decision
     * @param scanner from handleHumanTurn
     */
    private static void handlePlayDomino(Scanner scanner) {
        printGameBoard();
        System.out.println("Select a domino to play:");
        String inputWord = scanner.next();

        if (inputWord.equals("d")){
            handleDrawFromBoneyard();
            return;
        }

        if (!isDigit(inputWord)){
            handlePlayDomino(scanner);
            return;
        }

        int index = Integer.parseInt(inputWord);
        scanner.nextLine(); // Consume newline left-over

        //For the player to play first tile
        if (firstTile){
            gameBoard.add(humanHand.get(index));
            humanHand.remove(index);
            humanTurn = false;
            firstTile = false;
            return;
        }

        if (index < 0 || index >= humanHand.size()) {
            System.out.println("Invalid index");
            handlePlayDomino(scanner);
            return;
        }

        Domino domino = humanHand.get(index);
        wildCardCheck(domino);

        if (!canPlayOnTray(domino) && boneyard.isEmpty()){
            displayWinner();
            return;
        }

        if (!wildCardPlay){
            if (!canPlayOnTray(domino)) {
                System.out.println("Cannot play that domino on the tray...");
                handlePlayDomino(scanner);
                return;
            }
        }

        System.out.println("Left or Right? (l/r)");
        String direction = scanner.nextLine();
        if (!direction.equals("l") && !direction.equals("r")) {
            System.out.println("Invalid direction");
            handlePlayDomino(scanner);
            return;
        }

        if (wildCardPlay){
            handleWildCard(domino, direction);
            humanTurn = false;
            humanHand.remove(index);
            return;
        }

        if (direction.equals("l")) {
            if (!canPlayOnLeft(domino)){
                System.out.println("Can't play on left with this. Try again.");
                handlePlayDomino(scanner);
                return;
            }
            rotateTile(domino, direction);
            gameBoard.add(0, domino);
        } else {
            if (!canPlayOnRight(domino)){
                System.out.println("Can't play on right with this. Try again.");
                handlePlayDomino(scanner);
                return;
            }
            rotateTile(domino, direction);
            gameBoard.add(domino);
        }
        humanHand.remove(index);
        humanTurn = false;
    }

    private static void rotateTile(Domino domino, String direction){
        String error = "Can't play here. Try again.";
        System.out.println("Rotate first? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String rotate = scanner.nextLine();
        if (direction.equals("l")){
            if (rotate.equals("y")){
                if (domino.getLeft() == 0 || domino.getLeft() == gameBoard.get(0).getLeft()){
                    domino.rotate();
                }
                else {
                    System.out.println(error);
                    rotateTile(domino, direction);
                }
            } else if (rotate.equals("n")) {
                if (domino.getRight() == gameBoard.get(0).getLeft()){return;}
                if (domino.getLeft() == 0 || domino.getRight() != 0 || domino.getRight() != gameBoard.get(0).getLeft()){
                    System.out.println(error);
                    rotateTile(domino, direction);
                }
            }
            else {
                System.out.println("Wrong command. Try again.");
                rotateTile(domino, direction);
            }
        } else if (direction.equals("r")) {
            if (rotate.equals("y")){
                if (domino.getRight() == 0 || domino.getRight() == gameBoard.get(gameBoard.size()-1).getRight()){
                    domino.rotate();
                }
                else {
                    System.out.println(error);
                    rotateTile(domino, direction);
                }
            } else if (rotate.equals("n")) {
                if (domino.getLeft() == 0 || domino.getLeft() == gameBoard.get(gameBoard.size()-1).getRight()){
                    return;
                }
                if (domino.getRight() == 0 || domino.getLeft() != 0 || domino.getLeft() != gameBoard.get(gameBoard.size()-1).getRight()){
                    System.out.println(error);
                    rotateTile(domino, direction);
                }
            }
            else {
                System.out.println(error);
                rotateTile(domino, direction);
            }
        }
    }

    private static void rotateWildCardOnly(Domino domino){
        System.out.println("Rotate first? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String rotate = scanner.nextLine();
        if (rotate.equals("y")){
            domino.rotate();
        }
        else {
            System.out.println("Wrong Input! Try again.");
            rotateWildCardOnly(domino);
        }
    }

    private static void handleWildCard(Domino domino, String direction){

        //Check if there are zeros on the game board
        if (gameBoard.get(0).getLeft() == 0 || gameBoard.get(gameBoard.size()-1).getRight() == 0){
            if (gameBoard.get(0).getLeft() == 0 && gameBoard.get(gameBoard.size()-1).getRight() == 0){
                if (humanTurn){
                    rotateWildCardOnly(domino);
                    if (direction.equals("r")){
                        gameBoard.add(domino);
                    }
                    else {gameBoard.add(0, domino);
                    }
                }
                else {
                    gameBoard.add(0, domino);       //Computer adds at index 0 if there are 0 on both sides
                }
            }
            else if (gameBoard.get(0).getLeft() == 0 && gameBoard.get(gameBoard.size()-1).getRight() != 0){
                if (humanTurn){
                    if (direction.equals("l")){
                        rotateWildCardOnly(domino);
                        gameBoard.add(0, domino);
                    }
                    else {
                        rotateTile(domino, direction);
                        gameBoard.add(domino);}
                } else {gameBoard.add(0, domino);} //For Computer
            }
            else if (gameBoard.get(gameBoard.size()-1).getRight() == 0 && gameBoard.get(0).getLeft() != 0){
                if (humanTurn){
                    if (direction.equals("r")){
                        rotateWildCardOnly(domino);
                        gameBoard.add(domino);
                    }
                    else {
                        rotateTile(domino, direction);
                        gameBoard.add(0, domino);
                    }
                } else {gameBoard.add(domino);
                }   //Computer
            }
        }

        //Check if there are zeros on the domino/tile in hand
        else if (domino.getLeft() == 0 || domino.getRight() == 0){
            if (domino.getLeft() == 0 && domino.getRight() == 0){
                if (humanTurn){
                    rotateWildCardOnly(domino);
                    if (direction.equals("l")){
                        gameBoard.add(0, domino);
                    }
                    else {gameBoard.add(domino);}
                }
                else {gameBoard.add(domino);}       //Computer places at right if both zero in tile
            }
            else if (domino.getLeft() == 0 && domino.getRight() != 0){
                if (humanTurn){
                    if (direction.equals("l")){
                        rotateTile(domino, direction);
                        gameBoard.add(0, domino);
                    } else {
                        rotateTile(domino, direction);
                        gameBoard.add(domino);}
                } else {gameBoard.add(domino);}     //Computer
            }
        }
        wildCardPlay = false;
    }


    private static void wildCardCheck(Domino domino){
        if (domino.getLeft() == 0 || domino.getRight() == 0 || gameBoard.get(0).getLeft() == 0 ||
                gameBoard.get(gameBoard.size()-1).getRight() == 0){wildCardPlay = true;}
    }


    /**
     * Method called when player chooses drawFromBoneyard
     */
    private static void handleDrawFromBoneyard() {

        if (humanTurn){
            for (Domino dominoHuman : humanHand) {
                wildCardCheck(dominoHuman);
                if (canPlayOnTray(dominoHuman) || wildCardPlay){
                    System.out.println("Can't draw while it is possible to extend the line of play!");
                    return;
                }
            }
        }
        else {
            for (Domino dominoComputer : computerHand){
                wildCardCheck(dominoComputer);
                if (canPlayOnTray(dominoComputer) || wildCardPlay){
                    break;
                }
            }
        }

        Domino domino = drawFromBoneyard();
        if (domino != null) {
            if (humanTurn){
                humanHand.add(domino);
                humanTurn = true;
            }
            else {
                computerHand.add(domino);
                humanTurn = false;
            }
        } else {
            System.out.println("Boneyard is empty");
            humanTurn = false;
        }
    }

    private static void rotateTile(Domino domino){

        if (domino.getRight() == gameBoard.get(gameBoard.size() - 1).getRight() ||
                domino.getLeft() == gameBoard.get(0).getLeft()) {
            domino.rotate();
        }
    }

    /**
     * Method to control computer gameplay logic
     */
    static void handleComputerTurn() {

        // Simulate thinking time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the computer can play a domino
        int index;
        String position;
        System.out.println("computerHand" + computerHand);      //debug
        for (int i = 0; i < computerHand.size(); i++) {
            Domino domino = computerHand.get(i);
            index = i;
            wildCardCheck(domino);

            if (domino.getLeft() == gameBoard.get(0).getLeft()) {
                position = "l";
            } else if (domino.getRight() == gameBoard.get(gameBoard.size() - 1).getRight()) {
                position = "r";
            } else if (domino.getLeft() == gameBoard.get(gameBoard.size() - 1).getRight()) {
                position = "r";
            } else {
                position = "l";
            }

            if (!canPlayOnTray(domino) && boneyard.isEmpty()){
                displayWinner();
                return;
            }

            if (wildCardPlay){
                computerCanPlay = true;
                handleWildCard(domino, position);
                computerHand.remove(index);
                break;
            }

            else if (canPlayOnTray(domino)) {
                computerCanPlay = true;
                rotateTile(domino);

                if (position.equals("l")) {
                    gameBoard.add(0, domino);
                } else {
                    gameBoard.add(domino);
                }

                // Remove the domino from the computer's hand
                computerHand.remove(index);
                break;
            }

            else if (!canPlayOnTray(domino)){
                computerCanPlay = false;
            }
        }

        if (!computerCanPlay || computerHand.isEmpty()) {
            handleDrawFromBoneyard();
            handleComputerTurn();
        }
        // Switch to the human player's turn
        humanTurn = true;
    }

    /**
     * Method to check if playing condition is satisfied
     * @param domino: variable to check
     * @return : boolean
     */
    private static boolean canPlayOnTray(Domino domino) {
        return domino.getLeft() == gameBoard.get(0).getLeft() ||
                domino.getRight() == gameBoard.get(0).getLeft() ||
                domino.getLeft() == gameBoard.get(gameBoard.size() - 1).getRight() ||
                domino.getRight() == gameBoard.get(gameBoard.size() - 1).getRight();
    }

    private static boolean canPlayOnLeft(Domino domino){
        return domino.getLeft() == gameBoard.get(0).getLeft() || domino.getRight() == gameBoard.get(0).getLeft();
    }

    private static boolean canPlayOnRight(Domino domino){
        return domino.getLeft() == gameBoard.get(gameBoard.size()-1).getRight() ||
                domino.getRight() == gameBoard.get(gameBoard.size()-1).getRight();
    }

    /**
     * Check if the game is over
     * @return : boolean for GameOver
     */
    static boolean checkGameOver(){
        return (computerHand.isEmpty() && boneyard.isEmpty() && humanTurn) ||
                humanHand.isEmpty() && boneyard.isEmpty() && !humanTurn ||
                !computerHand.isEmpty() && boneyard.isEmpty() && !computerCanPlay;
    }

    /**
     * Calculate and display winner based on the score
     * Note: Still not complete
     */
    static void displayWinner() {
        int humanScore = 0;
        int computerScore = 0;
        for (Domino domino : humanHand) {
            humanScore += domino.getLeft() + domino.getRight();
        }
        for (Domino domino : computerHand) {
            computerScore += domino.getLeft() + domino.getRight();
        }
        System.out.println("Human score: " + humanScore);
        System.out.println("Computer score: " + computerScore);
        if (humanScore < computerScore) {
            System.out.println("Human wins!");
        } else if (computerScore < humanScore) {
            System.out.println("Computer wins!");
        } else {
            if (humanTurn){
                System.out.println("Human wins!");
            }
            else {
                System.out.println("Computer wins!");
            }
        }
        System.exit(1);
    }
}
