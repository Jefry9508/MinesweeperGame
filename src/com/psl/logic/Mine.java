/**
 * Minesweeper Game PSL-Challenge
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

import java.util.List;

public class Mine implements ICell {

    //CONSTANTS ATTRIBUTES

    /**
     * Constant that represents a cover mine.
     */
    public static final String COVER = ".";

    /**
     * Constant that represents a mark mine with a flag.
     */
    public static final String MARK = "P";

    /**
     * Constant that represents a mine.
     */
    public static final String MINE = "*";


    //ATTRIBUTES

    /**
     * Represent the current state of the mine.
     */
    private String state;


    //METHODS

    /**
     * Constructor of the Minie class.
     * @param newXPosition, position on the X axis on the board.
     * @param newYPosition, position on the Y axis on the board.
     */
    public Mine(int newXPosition, int newYPosition){
        state = COVER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uncover() {
        this.state = MINE;
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
     * Method for change the current state of the mine.
     * @param state, new state for the mine.
     */
    public void setStateCell(String state) {
        this.state = state;
    }

}
