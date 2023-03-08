/* Domino Game Project
* Name: Roshan Subedi
* Class: CS351 Project 3 Assignment
* Description: This is a project for Domino Game, and this file contains the implementation of the
* game using System input output rather than a graphical interface (JavaFX). There are two implementation
* requirements for this project and this is the first one. Other implementation will be based on JavaFX
* using the same base game.
*/

import java.util.ArrayList;
import java.util.Scanner;

public class DominoGame {
    private static ArrayList<Domino> boneyard = new ArrayList<>();
    private static ArrayList<Domino> gameBoard = new ArrayList<>();
    private static ArrayList<Domino> computerHand = new ArrayList<>();
    private static ArrayList<Domino> humanHand = new ArrayList<>();
    static boolean humanTurn = true;
    private static boolean canPlay = false;
    private static boolean firstTile = true;
    private static boolean wildCardPlay = false;
    private static boolean computerWildCardPlayed = false;

    /**
     * Initializing game with 28 tiles and 7 tiles for each players
     */
    static void initializeGame() {
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
//
//        humanTurn = true;
    }

    /**
     * Method to shuffle boneyard
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
     * @return
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
        System.out.println("Computer has " + computerHand.size() + " dominos");
        System.out.println("Boneyard contains " + boneyard.size() + " dominos");
        System.out.println(trayToString() + "\n");
        if (humanTurn) {
            System.out.println("Human's turn");
        } else {
            System.out.println("Computer's turn");
        }
    }

    /**
     * Method to display the current Game Board
     * @return
     */
    private static String trayToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gameBoard.size(); i++) {
            sb.append(gameBoard.get(i));
        }
        return sb.toString();
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
            case "p" -> {
                handlePlayDomino(scanner);
                System.out.println(gameBoard.get(gameBoard.size() - 1));
            }
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
     * @param s
     * @return
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
     * @param scanner
     */
    private static void handlePlayDomino(Scanner scanner) {
        System.out.println(trayToString());
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


        if (!canPlayOnTray(domino) && !wildCardPlay) {
            System.out.println("Cannot play that domino on the tray...");
            handlePlayDomino(scanner);
            return;
        }

        System.out.println("Left or Right? (l/r)");
        String direction = scanner.nextLine();
        if (!direction.equals("l") && !direction.equals("r")) {
            System.out.println("Invalid direction");
            handlePlayDomino(scanner);
            return;
        }

        if (gameBoard.get(0).getSide1() == 0 || gameBoard.get(gameBoard.size()-1).getSide2() == 0 ||
                domino.getSide1() == 0 || domino.getSide2() == 0){
            handleWildCard(domino, direction);
            wildCardPlay = false;
            humanTurn = false;
            humanHand.remove(index);
            return;
        }


        rotateTile(domino);
        if (direction.equals("l")) {
//            if ((gameBoard.get(0).getSide1() == 0) && wildCardPlay){
//                gameBoard.add(0, domino);
//                humanHand.remove(index);
//                humanTurn = false;
//                return;
//            } else if (domino.getSide1() == 0) {
//                domino.rotate();
//                 gameBoard.add(0, domino);
//                humanHand.remove(index);
//                humanTurn = false;
//                return;
//            }
            if (domino.getSide1() == gameBoard.get(0).getSide1() || domino.getSide2() == gameBoard.get(0).getSide1()){
                gameBoard.add(0, domino);
            }
            else {
                System.out.println("Wrong placement! Try again!");
                return;}
        } else {
//            if ((gameBoard.get(gameBoard.size()-1).getSide2() == 0) && wildCardPlay){
//                gameBoard.add(domino);
//                humanHand.remove(index);
//                humanTurn = false;
//                return;
//            } else if (domino.getSide2() == 0) {
//                rotateTile(domino);
//                gameBoard.add(domino);
//                humanHand.remove(index);
//                humanTurn = false;
//                return;
//            }
            if (domino.getSide1() == gameBoard.get(gameBoard.size()-1).getSide2() || domino.getSide2() == gameBoard.get(gameBoard.size()-1).getSide2()){
                gameBoard.add(domino);
            }

        }
        if (humanHand.isEmpty()){
            displayWinner();
            return;
        }



//        System.out.println("Rotate first? (y/n)");
//        String rotate = scanner.nextLine();
//        if (rotate.equals("y")) {
//            domino.rotate();
//        }


        humanHand.remove(index);
        humanTurn = false;
    }

    private static void handleWildCard(Domino domino, String direction){

        //Check if there are zeros on the game board
        if (gameBoard.get(0).getSide1() == 0 || gameBoard.get(gameBoard.size()-1).getSide2() == 0){
            if (gameBoard.get(0).getSide1() == 0){
                if (humanTurn){
                    if (direction.equals("l")){
                        gameBoard.add(0, domino);
                    }
                    else {rotateTile(domino);
                        gameBoard.add(domino);}
                } else {gameBoard.add(0, domino);} //For Computer
            }
            else if (gameBoard.get(gameBoard.size()-1).getSide2() == 0){
                if (humanTurn){
                    if (direction.equals("r")){
                        gameBoard.add(domino);
                    }
                    else {
                        rotateTile(domino);
                        gameBoard.add(0, domino);
                    }
                } else {gameBoard.add(domino);
                computerWildCardPlayed = true;}   //Computer
            }

        }

        //Check if there are zeros on the domino/tile in hand
        else if (domino.getSide1() == 0 || domino.getSide2() == 0){
            if (domino.getSide1() == 0){
                if (humanTurn){
                    if (direction.equals("l")){
                        rotateTile(domino);
                        gameBoard.add(0, domino);
                    } else {gameBoard.add(domino);}
                } else {gameBoard.add(domino);}
            } else if (domino.getSide2() == 0) {
                if (humanTurn){
                    if (direction.equals("r")){
                        rotateTile(domino);
                        gameBoard.add(domino);
                    } else {gameBoard.add(0, domino);}
                } else {gameBoard.add(0, domino);
                computerWildCardPlayed = true;}

            }

        }
    }

    private static void wildCardCheck(Domino domino){
        if (domino.getSide1() == 0 || domino.getSide2() == 0 || gameBoard.get(0).getSide1() == 0 ||
                gameBoard.get(gameBoard.size()-1).getSide2() == 0){wildCardPlay = true;}
    }


    /**
     * Method called when player chooses drawFromBoneyard
     */
    private static void handleDrawFromBoneyard(/*Domino dominoHand*/) {

        for (Domino dominoHuman : humanHand) {
            if (canPlayOnTray(dominoHuman)){
                System.out.println("Can't draw while it is possible to extend the line of play!");
                return;
            }
        }
        ArrayList<Domino> turn;
        turn = checkTurn();
        Domino domino = drawFromBoneyard();

        if (domino != null) {
            humanHand.add(domino);
            humanTurn = true;
        } else {
            System.out.println("Boneyard is empty");
            humanTurn = false;
        }
    }

    /**
     * Method to check current turn and return hand
     * @return
     */
    private static ArrayList<Domino> checkTurn(){
        if (humanTurn){
            return humanHand;
        }
        else {
            return computerHand;
        }
    }

    private static boolean checkDominoInTray(int i) {

        ArrayList<Domino> turn;
         turn = checkTurn();
        System.out.println("turn: " + turn);
        System.out.println("computerHand" + computerHand);

        // Check if the computer can play a domino
        Domino domino = turn.get(i);
        System.out.println("domino.getSide1(): " + domino.getSide1() + "| tray.get(0).getSide1(): " + gameBoard.get(0).getSide1());
        System.out.println("domino.getSide2(): " + domino.getSide2() + "| tray.get(0).getSide1(): " + gameBoard.get(0).getSide1());
        System.out.println("domino.getSide1(): " + domino.getSide1() + "| tray.get(tray.size()-1).getSide2(): " + gameBoard.get(gameBoard.size()-1).getSide2());
        System.out.println("domino.getSide2(): " + domino.getSide2() + "| tray.get(tray.size()-1).getSide2(): " + gameBoard.get(gameBoard.size()-1).getSide2());


        return true;
    }

    private static void rotateTile(Domino domino){

        if (domino.getSide2() == gameBoard.get(gameBoard.size() - 1).getSide2() ||
                    domino.getSide1() == gameBoard.get(0).getSide1()) {
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
        int index = 0;
        String position = "";
        System.out.println("computerHand" + computerHand);
        for (int i = 0; i < computerHand.size(); i++) {
//            int newI = (int) Math.floor(Math.random()*i);
            Domino domino = computerHand.get(i);
            if (canPlayOnTray(domino)) {
                index = i;
                if (domino.getSide1() == gameBoard.get(0).getSide1()) {
                    position = "l";
                } else if (domino.getSide2() == gameBoard.get(gameBoard.size() - 1).getSide2()) {
                    position = "r";
                } else if (domino.getSide1() == gameBoard.get(gameBoard.size() - 1).getSide2()) {
                    position = "r";
                } else {
                    position = "l";
                }

                if (gameBoard.get(0).getSide1() == 0 || gameBoard.get(gameBoard.size()-1).getSide2() == 0 ||
                        domino.getSide1() == 0 || domino.getSide2() == 0){
                    handleWildCard(domino, position);
                    computerHand.remove(index);
                    break;
                }

                if (!computerWildCardPlayed){
                    rotateTile(domino);

                    if (position.equals("l")) {
                        gameBoard.add(0, domino);
                    } else if (position.equals("r")) {
                        gameBoard.add(domino);
                    } else if (computerHand.isEmpty()) {displayWinner();}
                    // Remove the domino from the computer's hand
                    computerHand.remove(index);
                    break;
                }
            }
        }

        if (!canPlay){
            // Draw a domino from the boneyard
            Domino domino = drawFromBoneyard();
            if (domino != null) {
                computerHand.add(domino);
            } else {
                System.out.println("Boneyard is empty!");
            }
        }

        isGameOver();
        // Switch to the human player's turn
        humanTurn = true;
    }


    /**
     * Method to check if playing condition is satisfied
     * @param domino
     * @return
     */
    private static boolean canPlayOnTray(Domino domino) {
        wildCardCheck(domino);
        canPlay = true;
        return domino.getSide1() == gameBoard.get(0).getSide1() ||
                domino.getSide2() == gameBoard.get(0).getSide1() ||
                domino.getSide1() == gameBoard.get(gameBoard.size() - 1).getSide2() ||
                domino.getSide2() == gameBoard.get(gameBoard.size() - 1).getSide2();
    }

    /**
     * Check if the game is over
     * @return
     */
    static boolean isGameOver() {
        if ((humanHand.isEmpty() || computerHand.isEmpty()) && boneyard.isEmpty()) {
            return true;
        }

        //Checks if boneyard is empty and human can play or not
        for (Domino dominoHuman : humanHand){
            if (boneyard.isEmpty() && !canPlayOnTray(dominoHuman)) {
                return true;
            }
        }

        //Checks if boneyard is empty and computer can play or not
        for (Domino dominoComputer : computerHand){
            if (boneyard.isEmpty() && !canPlayOnTray(dominoComputer)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Calculate and display winner based on the score
     * Note: Still not complete
     */
    static void displayWinner() {
        int humanScore = 0;
        int computerScore = 0;
        for (Domino domino : humanHand) {
            humanScore += domino.getSide1() + domino.getSide2();
        }
        for (Domino domino : computerHand) {
            computerScore += domino.getSide1() + domino.getSide2();
        }
        System.out.println("Human score: " + humanScore);
        System.out.println("Computer score: " + computerScore);
        if (humanScore < computerScore) {
            System.out.println("Human wins!");
        } else if (computerScore < humanScore) {
            System.out.println("Computer wins!");
        } else if (computerScore == humanScore) {
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
