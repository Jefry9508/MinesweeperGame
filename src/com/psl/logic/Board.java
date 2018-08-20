/**
 * Minesweeper Game PSL-Challenge
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    //ATTRIBUTES

    /**
     * Graph of the mines and squares, is the board structure.
     */
    private ICell[][] graphCells;

    /**
     * Number of rows on the board
     */
    private int height;

    /**
     * Number of columns on the board
     */
    private int width;

    //METHODS

    /**
     * Constructor of the board.
     * @param newHeight, number of rows.
     * @param newWidth, number of columns.
     * @param minesAmount, amount of mines on the board.
     */
    public Board(int newHeight, int newWidth, int minesAmount){
        height = newHeight;
        width = newWidth;
        graphCells = new ICell[height][width];
        buildGraph(minesAmount);
    }

    /**
     * Builds the graph by adding the mines and squares and assigning adjacent cells for each node.
     * @param minesAmount, amount of mines on the board.
     */
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

    /**
     * Method that adds mines randomly to the graph in different positions.
     * @param minesAmount, amount of mines on the board.
     */
    private void addMinesToGraph(int minesAmount){

        List<Pair<Integer, Integer>> positions = createPositionCombinatorics(minesAmount);

        for(int i = 0; i < positions.size(); i++) {
            Mine mine = new Mine(positions.get(i).getKey(), positions.get(i).getValue());
            graphCells[positions.get(i).getKey()][positions.get(i).getValue()] = mine;
        }
    }

    /**
     * Method that shows the current state of the board in the game.
     */
    public void displayBoard(){

        for(int i = 0; i < height; i++){
            String text = "";
            for(int j = 0; j < width; j++){
                text += " "+graphCells[i][j].getStateCell();
            }
            System.out.println(text);
        }
    }

    /**
     * Method that adds the adjacents cells to the squares on the board.
     */
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

    /**
     * Method that allows to know if the user has lost the game when he/she selected a mine on the board.
     * @param xPosition, row number on the board.
     * @param yPosition, column number on the board.
     * @return boolean, true if the game is over or true in opposite case.
     */
    public boolean isGameOver(int xPosition, int yPosition){
        boolean gameOver = false;
        if(graphCells[xPosition][yPosition] instanceof Mine){
            gameOver = true;
        }

        return gameOver;
    }

    /**
     * Method that uncovers the mines switching the states of the cells that contain mines.
     */
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

    /**
     * Method that creates all positions of the matrix that represents to the graph.
     * @param minesAmount, amount of mines on the board.
     * @return List<Pair<Integer, Integer>>, list with a tuple that contain the  position on the X and Y axis.
     */
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


    /**
     * Method that allows know the amount of mines around in the current cell.
     * @param xPosition, position on the X axis.
     * @param yPosition, position on the Y axis.
     */
    public void amountMinesAround(int xPosition, int yPosition){
        //System.out.println("Paso 1");
        Square square = (Square)graphCells[xPosition][yPosition];
        //System.out.println("Paso 2");
        square.searchMinesAround();
        //System.out.println("Paso 3");
        displayBoard();
    }


    /**
     * Method the allows mark with a flag the cell which the user beliaves there is a mine.
     * @param xPosition, position on the X axis.
     * @param yPosition, position on the Y axis.
     */
    public void markCell(int xPosition, int yPosition){
        graphCells[xPosition][yPosition].mark();
        displayBoard();
    }


    /**
     * Method that allows know if the user won the game validating that all cells with mines are marked with the flag.
     * @param minesAmount, amount of mines on the board.
     * @return boolean, true if the user won the game or false in opposite case.
     */
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


    /**
     * Method that checks if the cell given by the coordinates passed by parameter is uncovered.
     * @param xPosition, position on the X axis.
     * @param yPosition, position on the Y axis.
     * @return boolean, true if the cell is uncovered or fase in opposite case.
     */
    public boolean isBlankCell(int xPosition, int yPosition){
        String state = graphCells[xPosition][yPosition].getStateCell();
        if((state.equals(Square.EMPTY) || Integer.getInteger(state) == null) && !state.equals(Square.COVER)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Method that gives the number rows of the board.
     * @return int, amount of rows on the board.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Method that gives the number columns of the board.
     * @return int, amount of columns on the board.
     */
    public int getWidth() {
        return width;
    }
}
