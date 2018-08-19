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

    }

    @Override
    public void mark() {

    }

    public String getState() {
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
}
