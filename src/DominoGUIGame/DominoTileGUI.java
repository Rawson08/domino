package DominoGUIGame;
import DominoConsoleGame.Domino;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.InputStream;

public class DominoTileGUI {
    private Image[] tileImages;

public ImageView handleTileGUI(Domino domino){
    int left = domino.getLeft();
    int right = domino.getRight();

    InputStream inputStream;

    ImageView tile = new ImageView();

    tileImages = new Image[28];
    for (int i = 0; i < 7; i++){
        for (int j= i; j < 7; j++){
            int index = i * 7 + j - (i * (i  + 1) / 2);
            tileImages[index] = new Image(getClass().getResourceAsStream(i + "_" + j + ".png"));
        }
    }

    GridPane gridPane = new GridPane();

    int rowIndex = 0;
    int colIndex = 0;
    for (int i = 0; i < 28; i++){
        ImageView imageView = new ImageView(tileImages[i]);
        imageView.setFitWidth(50);
        imageView.setFitHeight(100);
        gridPane.add(imageView, colIndex, rowIndex);

        colIndex++;
        if (colIndex == 7){
            colIndex = 0;
            rowIndex++;
        }
    }

//    if (left == 0 && right == 0){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/0-0.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 0 && right == 1) || (left == 1 && right == 0)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/0-1.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 0 && right == 2) || (left == 2 && right == 0)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/0-2.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 0 && right == 3) || (left == 3 && right == 0)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/0-3.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 0 && right == 4) || (left == 4 && right == 0)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/0-4.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 0 && right == 5) || (left == 5 && right == 0)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/0-5.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 0 && right == 6) || (left == 6 && right == 0)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/0-6.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if (left == 1 && right == 1){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/1-1.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 1 && right == 2) || (left == 2 && right == 1)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("resources/1-2.png");
////        assert inputStream != null;
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 1 && right == 3) || (left == 3 && right == 1)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/1-3.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 1 && right == 4) || (left == 4 && right == 1)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/1-4.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 1 && right == 5) || (left == 5 && right == 1)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/1-5.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 1 && right == 6) || (left == 6 && right == 1)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/1-6.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if (left == 2 && right == 2){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/2-2.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 2 && right == 3) || (left == 3 && right == 2)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/2-3.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 2 && right == 4) || (left == 4 && right == 2)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/2-4.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 2 && right == 5) ||(left == 5 && right == 2)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/2-5.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 2 && right == 6) || (left == 6 && right == 2)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/2-6.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if (left == 3 && right == 3){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/3-3.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 3 && right == 4) || (left == 4 && right == 3)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/3-4.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 3 && right == 5) || (left == 5 && right == 3)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/3-5.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 3 && right == 6) || (left == 6 && right == 3)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/3-6.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if (left == 4 && right == 4){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/4-4.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 4 && right == 5) || (left == 5 && right == 4)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/4-5.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 4 && right == 6) || (left == 6 && right == 4)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/4-6.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if (left == 5 && right == 5){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/5-5.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if ((left == 5 && right == 6) || (left == 6 && right == 5)){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/5-6.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
//
//    else if (left == 6 && right == 6){
//        inputStream = DominoTileGUI.class.getClassLoader().getResourceAsStream("src/resources/6-6.png");
//        assert inputStream != null;
//        tile.setImage(new Image(inputStream));
//    }
    return tile;
}
}
