/**
 * Minesweeper Game PSL-Challenge
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


    public Mine(int newXPosition, int newYPosition){
        state = COVER;
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

}
