/**
 * Minesweeper Game PSL
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

import java.util.List;

public class Mine implements ICell {

    public static final String COVER = ".";
    public static final String MARK = "P";
    public static final String MINE = "*";

    private String state;
    private int xPosition;
    private int yPosition;

    public Mine(int newXPosition, int newYPosition){
        state = COVER;
        xPosition = newXPosition;
        yPosition = newYPosition;
    }

    @Override
    public void uncover() {
        this.state = MINE;
    }

    @Override
    public void mark() {
        this.state = MARK;
    }

    @Override
    public String getStateCell() {
        return state;
    }

    public void setStateCell(String state) {
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

    public int getyPosition() {
        return yPosition;
    }
}
