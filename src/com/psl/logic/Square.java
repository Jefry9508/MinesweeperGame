package com.psl.logic;

import java.util.ArrayList;
import java.util.List;

public class Square implements ICell {

    public static final String COVER = ".";
    public static final String EMPTY = "-";
    public static final String MARK = "P";
    public static final String FLAG = "P";

    private String state;
    private int xPosition;
    private int yPosition;
    private List<ICell> adjacentsCells;

    public Square(int newXPosition, int newYPosition) {
        state = COVER;
        xPosition = newXPosition;
        yPosition = newYPosition;
        adjacentsCells = new ArrayList<ICell>();
    }

    @Override
    public void uncover() {

    }

    @Override
    public void mark() {

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

    }

    private int searchMinesAroundassistant(){
        return 0;
    }
}
