package com.psl.logic;

import java.util.Random;

public class Board {

    private ICell[][] graphCells;
    private int height;
    private int width;

    public Board(int newHeight, int newWidth, int minesAmount){
        height = newHeight;
        width = newWidth;
        graphCells = new ICell[height][width];
        buildGraph(minesAmount);
    }

    private void buildGraph(int minesAmount){
        addMinesToGraph(minesAmount);
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if(graphCells[i][j] == null) {
                    ICell<Square> square = new Square(i, j);
                    graphCells[i][j] = square;
                }
            }
        }
        assignAdjacentsCells();
    }

    private void addMinesToGraph(int minesAmount){
        Random r = new Random();
        boolean minesAdded = false;

        for(int i = 0; i < minesAmount-1 && !minesAdded; i++) {
            int xPosition = r.nextInt(height-1);
            int yPosition = r.nextInt(width-1);

            if(graphCells[xPosition][yPosition] != null){
                ICell<Mine> mine = new Mine(xPosition, yPosition);
                graphCells[xPosition][yPosition] = mine;
                minesAdded = true;
            }
        }
    }

    public void displayBoard(){

        for(int i = 0; i < height; i++){
            String text = "";
            for(int j = 0; j < width; j++){
                text += " "+graphCells[i][j].getState();
            }
            System.out.println(text);
        }
    }

    private void assignAdjacentsCells(){

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                //Add adjacent cells to the top of the table
                if(graphCells[i][j] instanceof Square){
                    int indexHeight = i-1;
                    int indexWidth = j-1;
                    Square square = (Square) graphCells[i][j];
                    if(indexHeight >= 0 && indexWidth >= 0 && indexWidth < width){
                        do {
                            square.assignAdjacentsCells(graphCells[indexHeight][indexWidth]);
                            indexWidth++;
                        }while(indexWidth < width && indexWidth < (indexWidth+3));
                    }


                    //Add adjacent cells to the left and the right of the table
                    if((j-1)>=0){
                        square.assignAdjacentsCells(graphCells[i][j-1]);
                    }
                    if((j+1) < width){
                        square.assignAdjacentsCells(graphCells[i][j+1]);
                    }

                    //Add adjacent cells to the bottom of the table
                    indexHeight = i+1;
                    indexWidth = j-1;
                    if(indexHeight < height && indexWidth >= 0 && indexWidth < width){
                        do {
                            square.assignAdjacentsCells(graphCells[indexHeight][indexWidth]);
                            indexWidth++;
                        }while(indexWidth < width && indexWidth < (indexWidth+3));
                    }
                }
            }
        }
    }
}
