/**
 * Minesweeper Game PSL-Challenge
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

public interface ICell<T> {

    //METHODS

    /**
     * Method that allows uncover the current cell.
     */
    void uncover();

    /**
     * Method thet allows mark with a flag the current cell.
     */
    void mark();

    /**
     * Method that allows know the current state of the cell.
     * @return
     */
    String getStateCell();

}
