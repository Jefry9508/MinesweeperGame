/**
 * Minesweeper Game PSL-Challenge
 *
 * @Author Jefry Cardona
 */
package com.psl.test;

import com.psl.logic.Board;
import com.psl.logic.ICell;
import com.psl.logic.Mine;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MinesweeperTest {

    //CONSTANTS ATTRIBUTES

    /**
     * Represent amount of rows on the board.
     */
    public final static int ROWS = 8;

    /**
     * Represent amount of columns on the board.
     */
    public final static int COLUMNS = 8;

    /**
     * Represent amount of mines on the board.
     */
    public final static int MINES_AMOUNT = 4;

    //ATTRIBUTES

    /**
     * Relationship with Board class.
     */
    private Board board;

    /**
     * Represent amount of rows on the board.
     */
    private int height;

    /**
     * Represent amount of columns on the board.
     */
    private int width;

    /**
     * Represent amount of mines on the board.
     */
    private int minesAmount;


    //METHODS

    /**
     * A method of creating a controlled scenario for testing.
     */
    private void setupStage() {
        this.height = ROWS;
        this.width = COLUMNS;
        this.minesAmount = MINES_AMOUNT;
        this.board = new Board(this.height, this.width, this.minesAmount);
    }

    /**
     * Method that marks a certain number of cells passed per parameter.
     * @param amountCells, amount of cells to mark.
     */
    private void markCells(int amountCells){
        ICell[][] cells = board.getGraphCells();
        for(int i = 0; i < height && amountCells > 0; i++){
            for (int j = 0; j < width && amountCells > 0; j++){
                cells[i][j].mark();
                amountCells--;
            }
        }
    }

    /**
     * Method that marks a cell passed per parameter.
     * @param xPosition, position on the X axis.
     * @param yPosition, position on the Y axis.
     */
    private void markCell(int xPosition, int yPosition){
        ICell[][] cells = board.getGraphCells();
        cells[xPosition][yPosition].mark();
    }

    /**
     * Verifies that the user not won if he/she marked more cell that mines.
     */
    @Test
    public void testUserNotWonMoreMinesMark() {
        setupStage();
        markCells(8);
        assertFalse("Error in the method WinGame of Board class.", board.winGame(minesAmount));
    }

    /**
     * Verifies that the user loses the game when he/she selected a cell that contain a miine.
     */
    @Test
    public void testGameOver(){
        setupStage();
        ICell[][] cells = board.getGraphCells();
        boolean gameOver = false;
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if(cells[i][j] instanceof Mine){
                    gameOver = board.isGameOver(i, j);
                    break;
                }
            }
            break;
        }

        assertFalse("Error in method isGameOver", gameOver);
    }

    /**
     * Verifies that the user win the game if and only of he/she marked all cells that contain mines.
     */
    @Test
    public void testWinGame(){
        setupStage();
        ICell[][] cells = board.getGraphCells();
        int minesFound = 0;
        for(int i = 0; i < height && minesFound < MINES_AMOUNT; i++){
            for (int j = 0; j < width && minesFound < MINES_AMOUNT; j++){
                if(cells[i][j] instanceof Mine){
                    markCell(i, j);
                    minesFound++;
                }
            }
        }
        assertTrue("Error in winGame method", board.winGame(minesAmount));
    }
}
