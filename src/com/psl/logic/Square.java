/**
 * Minesweeper Game PSL
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

import java.util.ArrayList;
import java.util.List;

public class Square implements ICell {

    public static final String COVER = ".";
    public static final String EMPTY = "-";
    public static final String MARK = "P";

    private String state;
    private int xPosition;
    private int yPosition;
    private List<ICell> adjacentsCells;
    private boolean isVisited;

    public Square(int newXPosition, int newYPosition) {
        state = COVER;
        xPosition = newXPosition;
        yPosition = newYPosition;
        adjacentsCells = new ArrayList<ICell>();
    }

    @Override
    public void uncover() {
        this.state = EMPTY;
    }

    @Override
    public void mark() {
        this.state = MARK;
    }

    @Override
    public String getStateCell() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void assignAdjacentsSquares(ICell adjacentCell){
        adjacentsCells.add(adjacentCell);
    }

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

    private int minesCounter(){
        int amountMines = 0;

        for (int i = 0; i < adjacentsCells.size(); i++){
            if(adjacentsCells.get(i) instanceof Mine){
                amountMines++;
            }
        }

        return amountMines;
    }

    private int searchMinesAroundAssistant(){
        int amountMines = minesCounter();
        if(amountMines > 0){
            return amountMines;
        }else{
            return amountMines;
        }
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public List<ICell> getAdjacentsCells() {
        return adjacentsCells;
    }
}
