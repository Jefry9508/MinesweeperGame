/**
 * Minesweeper Game PSL
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
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

        List<Pair<Integer, Integer>> positions = createPositionCombinatorics(minesAmount);

        for(int i = 0; i < positions.size(); i++) {
            Mine mine = new Mine(positions.get(i).getKey(), positions.get(i).getValue());
            graphCells[positions.get(i).getKey()][positions.get(i).getValue()] = mine;
        }
    }

    public void displayBoard(){

        for(int i = 0; i < height; i++){
            String text = "";
            for(int j = 0; j < width; j++){
                text += " "+graphCells[i][j].getStateCell();
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

                    do {
                        if(indexHeight >= 0 && indexWidth >= 0 && indexWidth < width) {
                            square.assignAdjacentsSquares(graphCells[indexHeight][indexWidth]);
                        }
                        indexWidth++;
                    }while(indexWidth < width && indexWidth < (j+2));



                    //Add adjacent cells to the left and the right of the table
                    if((j-1)>=0){
                        square.assignAdjacentsSquares(graphCells[i][j-1]);
                    }
                    if((j+1) < width){
                        square.assignAdjacentsSquares(graphCells[i][j+1]);
                    }

                    //Add adjacent cells to the bottom of the table
                    indexHeight = i+1;
                    indexWidth = j-1;

                    do {
                        if(indexHeight < height && indexWidth >= 0 && indexWidth < width) {
                            square.assignAdjacentsSquares(graphCells[indexHeight][indexWidth]);
                        }
                        indexWidth++;
                    }while(indexWidth < width && indexWidth < (j+2));

                }
            }
        }
    }

    public boolean isGameOver(int xPosition, int yPosition){
        boolean gameOver = false;
        if(graphCells[xPosition][yPosition] instanceof Mine){
            gameOver = true;
        }

        return gameOver;
    }

    public void lostGame(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if(graphCells[i][j] instanceof Mine){
                    Mine mine = (Mine)graphCells[i][j];
                    mine.uncover();
                }
            }
        }
        displayBoard();
        System.out.println("\n***************** GAME OVER ******************\n");
        System.out.println("\nDeveloped by Jefry Cardona - Universidad Icesi");
    }

    private List<Pair<Integer, Integer>> createPositionCombinatorics(int minesAmount){
        List<Pair<Integer, Integer>> positions = new ArrayList<Pair<Integer, Integer>>();
        List<Pair<Integer, Integer>> positionsSelected = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                Pair<Integer, Integer> pair = new Pair<Integer, Integer>(i, j);
                positions.add(pair);
            }
        }
        Random r = new Random();
        for (int i = 0; i < minesAmount; i++) {
            int index = r.nextInt(positions.size());
            positionsSelected.add(positions.get(index));
            positions.remove(index);
        }
        return positionsSelected;
    }


    public void amountMinesAround(int xPosition, int yPosition){
        //System.out.println("Paso 1");
        Square square = (Square)graphCells[xPosition][yPosition];
        //System.out.println("Paso 2");
        square.searchMinesAround();
        //System.out.println("Paso 3");
        displayBoard();
    }

    public void markCell(int xPosition, int yPosition){
        graphCells[xPosition][yPosition].mark();
        displayBoard();
    }

    public boolean winGame(int minesAmount){
        boolean winner = false;
        int markedMines = 0;
        int markedSquares = 0;

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (graphCells[i][j].getStateCell().equals(Mine.MARK) && graphCells[i][j] instanceof Mine) {
                    markedMines++;
                }
                if(graphCells[i][j].getStateCell().equals(Square.MARK) && graphCells[i][j] instanceof Square){
                    markedSquares++;
                }
            }
        }
        if((markedMines-markedSquares) == minesAmount){
            winner = true;
        }
        return winner;
    }

    public boolean isBlankCell(int xPosition, int yPosition){
        if(graphCells[xPosition][yPosition].getStateCell().equals(Square.EMPTY)){
            return true;
        }else{
            return false;
        }
    }
}
