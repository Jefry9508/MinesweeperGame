/**
 * Minesweeper Game PSL-Challenge
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

public interface ICell<T> {

    void uncover();
    void mark();
    String getStateCell();

}
