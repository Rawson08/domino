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
    private static ArrayList<Domino> tray = new ArrayList<>();
    private static ArrayList<Domino> computerHand = new ArrayList<>();
    private static ArrayList<Domino> humanHand = new ArrayList<>();
    private static boolean humanTurn = true;
    private static boolean canPlay = false;

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

    //Main initialization of game
    private static void initializeGame() {
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                boneyard.add(new Domino(i, j));
            }
        }
        shuffleBoneyard();
        //Adding 7 bones for each player
        for (int i = 0; i < 7; i++) {
            computerHand.add(drawFromBoneyard());
            humanHand.add(drawFromBoneyard());
        }

        //Adding the rest of the bones to tray
        tray.add(drawFromBoneyard());
        humanTurn = true;
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
    private static void displayGameState() {
        System.out.println("Computer has " + computerHand.size() + " dominos");
        System.out.println("Boneyard contains " + boneyard.size() + " dominos");
        System.out.println("Board: " + trayToString());
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
//        sb.append("[");
        for (int i = 0; i < tray.size(); i++) {
            sb.append(tray.get(i));
            if (i != tray.size() - 1) {
                sb.append(", ");
            }
        }
//        sb.append("]");
        return sb.toString();
    }


    /**
     * Method to handle human's turn
     */
    private static void handleHumanTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Board: " + trayToString());
        System.out.println("Tray: " + humanHand);   //Debug Purpose
        System.out.println("ComputerTray: " + computerHand);    //Debug Purpose
        System.out.println("[p] Play Domino");
        System.out.println("[d] Draw from boneyard");
        System.out.println("[q] Quit");
        String input = scanner.nextLine();
        switch (input) {
            case "p" -> {
                handlePlayDomino(scanner);
                System.out.println(tray.get(tray.size() - 1));
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
     * Method to handle domino play decision
     * @param scanner
     */
    private static void handlePlayDomino(Scanner scanner) {
        System.out.println("Board: " + trayToString());
        System.out.println("Select a domino to play:");
        for (int i = 0; i < humanHand.size(); i++) {
            System.out.println(i + ": " + humanHand.get(i));
        }
        String inputWord = scanner.next();

        if (inputWord.equals("d")){
            handleDrawFromBoneyard();
        }

        int index = Integer.parseInt(inputWord);
        scanner.nextLine(); // Consume newline left-over


        if (index < 0 || index >= humanHand.size()) {
            System.out.println("Invalid index");
            handlePlayDomino(scanner);
            return;
        }
        Domino domino = humanHand.get(index);
        if (!canPlayOnTray(domino)) {
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
        System.out.println("Rotate first? (y/n)");
        String rotate = scanner.nextLine();
        if (rotate.equals("y")) {
            domino.rotate();
        }
        if (direction.equals("l")) {
            tray.add(0, domino);
        } else {
            tray.add(domino);
        }
        humanHand.remove(index);
        humanTurn = false;
    }


    /**
     * Method called when player chooses drawFromBoneyard
     */
    private static void handleDrawFromBoneyard(/*Domino dominoHand*/) {

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

//    private static void handleComputerTurn() {
//        for (Domino domino : computerHand) {
//            if (canPlayOnTray(domino)) {
//                computerHand.remove(domino);
//                tray.add(domino);
//                humanTurn = true;
//                return;
//            }
//        }
//        Domino domino = drawFromBoneyard();
//        if (domino != null) {
//            computerHand.add(domino);
//            humanTurn = true;
//        } else {
//            humanTurn = true;
//        }
//    }

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
        System.out.println("domino.getSide1(): " + domino.getSide1() + "| tray.get(0).getSide1(): " + tray.get(0).getSide1());
        System.out.println("domino.getSide2(): " + domino.getSide2() + "| tray.get(0).getSide1(): " + tray.get(0).getSide1());
        System.out.println("domino.getSide1(): " + domino.getSide1() + "| tray.get(tray.size()-1).getSide2(): " + tray.get(tray.size()-1).getSide2());
        System.out.println("domino.getSide2(): " + domino.getSide2() + "| tray.get(tray.size()-1).getSide2(): " + tray.get(tray.size()-1).getSide2());


        return true;
    }

    /**
     * Method to control computer gameplay logic
     */
    private static void handleComputerTurn() {

        // Simulate thinking time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the computer can play a domino
        int index = -1;
        String position = "";
        for (int i = 0; i < computerHand.size(); i++) {
            Domino domino = computerHand.get(i);
            if (canPlayOnTray(domino)) {
                canPlay = true;
                System.out.println("domino.getSide1(): " + domino.getSide1() + "| tray.get(0).getSide1(): " + tray.get(0).getSide1());
                System.out.println("domino.getSide2(): " + domino.getSide2() + "| tray.get(tray.size()-1).getSide2(): " + tray.get(tray.size()-1).getSide2());
                System.out.println("domino.getSide1(): " + domino.getSide1() + "| tray.get(tray.size()-1).getSide2(): " + tray.get(tray.size()-1).getSide2());
                System.out.println("domino.getSide2(): " + domino.getSide2() + "| tray.get(0).getSide1(): " + tray.get(0).getSide1());

                index = i;
                if (domino.getSide1() == tray.get(0).getSide1()) {
                    domino.rotate();
                    position = "l";
                } else if (domino.getSide2() == tray.get(tray.size() - 1).getSide2()) {
                    domino.rotate();
                    position = "r";
                } else if (domino.getSide1() == tray.get(tray.size() - 1).getSide2()) {
//                    domino.rotate();
                    position = "r";
                } else {
//                    domino.rotate();
                    position = "l";
                }
            }

        }

        // If the computer can play a domino, play it
        if (canPlay) {

            // Remove the domino from the computer's hand
            Domino domino = computerHand.remove(index);

            // Rotate the domino if necessary
//            boolean rotate = false;
//            if (domino.getSide1() == tray.get(tray.size() - 1).getSide2() ||
//                    domino.getSide2() == tray.get(0).getSide1()) {
//                rotate = true;
//                domino.rotate();
//            }

            // Add the domino to the tray
            if (position.equals("l")) {
                tray.add(0, domino);
            } else if (position.equals("r")) {
                tray.add(domino);
            }

            // Check if the computer won
            if (computerHand.isEmpty()) {
                System.out.println("Computer won!");
                System.exit(0);
            }
        } else {
            // Draw a domino from the boneyard
            Domino domino = drawFromBoneyard();
            if (domino != null) {
                computerHand.add(domino);
            } else {
                System.out.println("Boneyard is empty!");
            }
        }

        // Switch to the human player's turn
        humanTurn = true;
    }


    /**
     * Method to check if playing condition is satisfied
     * @param domino
     * @return
     */
    private static boolean canPlayOnTray(Domino domino) {
        return domino.getSide1() == tray.get(0).getSide1() ||
                domino.getSide2() == tray.get(0).getSide1() ||
                domino.getSide1() == tray.get(tray.size() - 1).getSide2() ||
                domino.getSide2() == tray.get(tray.size() - 1).getSide2();
    }

    /**
     * Check if the game is over
     * @return
     */
    private static boolean isGameOver() {
        if (humanHand.isEmpty() || computerHand.isEmpty() || boneyard.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Calculate and display winner based on the score
     * Note: Still not complete
     */
    private static void displayWinner() {
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
        } else {
            System.out.println("It's a tie!");
        }
    }
}
