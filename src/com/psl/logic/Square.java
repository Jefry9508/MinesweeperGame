/**
 * Minesweeper Game PSL-Challenge
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

import java.util.ArrayList;
import java.util.List;

public class Square implements ICell {

    //CONSTANTS ATTRIBUTES

    /**
     * Constant that represents a cover square.
     */
    public static final String COVER = ".";

    /**
     * Constant that represents a empty square.
     */
    public static final String EMPTY = "-";

    /**
     * Constant that represents a mark square with a flag.
     */
    public static final String MARK = "P";


    //ATTRIBUTES

    /**
     * Represents the current state of the square.
     */
    private String state;

    /**
     * Represents the
     */
    private List<ICell> adjacentsCells;

    /**
     * adjacents cells of the current square.
     */
    private boolean isVisited;


    //METHODS

    /**
     * Constructor of the Square class.
     * @param newXPosition, position on the X axis on the board.
     * @param newYPosition, position on the Y axis on the board.
     */
    public Square(int newXPosition, int newYPosition) {
        state = COVER;
        adjacentsCells = new ArrayList<ICell>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uncover() {
        this.state = EMPTY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mark() {
        this.state = MARK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStateCell() {
        return state;
    }

    /**
     * Method that gives the current state of the square.
     * @param state, new state for the square.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Method that allows assign the adjacents cells to the current square.
     * @param adjacentCell
     */
    public void assignAdjacentsSquares(ICell adjacentCell){
        adjacentsCells.add(adjacentCell);
    }

    /**
     * Concurrent method that allows search the mines around the position of the current square.
     */
    public void searchMinesAround(){
        int amountMines = searchMinesAroundAssistant();
        if(amountMines > 0){
            this.state = amountMines+"";
            this.isVisited = true;
        }else{
            for (int i = 0; i < adjacentsCells.size(); i++){
                this.uncover();
                this.isVisited = true;
                Square square = (Square)adjacentsCells.get(i);
                if(!square.isVisited()) {
                    square.searchMinesAround();
                }
            }
        }
    }

    /**
     * Method that counts the total number of mines found around of the current square.
     * @return int, amount of mines around of the current square.
     */
    private int minesCounter(){
        int amountMines = 0;

        for (int i = 0; i < adjacentsCells.size(); i++){
            if(adjacentsCells.get(i) instanceof Mine){
                amountMines++;
            }
        }

        return amountMines;
    }

    /**
     * Method that assited to the concurrent method searchMinesAround() managing the base case.
     * @return int, amount mines.
     */
    private int searchMinesAroundAssistant(){
        int amountMines = minesCounter();
        if(amountMines > 0){
            return amountMines;
        }else{
            return amountMines;
        }
    }

    /**
     * Method that gives if the current square has been visited before.
     * @return boolean, true if the square has been visited or false in opposite case.
     */
    public boolean isVisited() {
        return this.isVisited;
    }

    /**
     * Method that allows change the state of the current square.
     * @param visited, new value of the state.
     */
    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    /**
     * Method that gives the list of the adjacents cells of the current square.
     * @return List<ICell>, list with the adjacents cells.
     */
    public List<ICell> getAdjacentsCells() {
        return adjacentsCells;
    }
}
