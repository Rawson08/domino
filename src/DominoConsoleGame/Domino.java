package DominoConsoleGame;

import javafx.scene.Group;
import javafx.scene.layout.HBox;

/* Domino Game Project
 * Name: Roshan Subedi
 * Class: CS351 Project 3 Assignment
 * Description: This is the class which stores left and right sides of the domino and
 * returns when get functions are called. Basically, this is the class which creates the domino tile.
 */
public class Domino {
    private int side1;
    private int side2;
    private HBox hBox;

    public Domino(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
        this.hBox = new HBox();
    }

    public int getLeft() {return side1;}

    public int getRight() {
        return side2;
    }

    public void rotate() {
        int temp = side1;
        side1 = side2;
        side2 = temp;
//        Group side1 = (Group) hBox.getChildren().get(0);
    }

    public String toString() {
        return "[" + side1 + "|" + side2 + "]";
    }
}
