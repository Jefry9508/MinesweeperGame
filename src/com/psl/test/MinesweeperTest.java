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

    public final static int ROWS = 8;
    public final static int COLUMNS = 8;
    public final static int MINES_AMOUNT = 4;

    private Board board;
    private int height;
    private int width;
    private int minesAmount;

    private void setupStage() {
        this.height = ROWS;
        this.width = COLUMNS;
        this.minesAmount = MINES_AMOUNT;
        this.board = new Board(this.height, this.width, this.minesAmount);
    }

    private void markCells(int amountCells){
        ICell[][] cells = board.getGraphCells();
        for(int i = 0; i < height && amountCells > 0; i++){
            for (int j = 0; j < width && amountCells > 0; j++){
                cells[i][j].mark();
                amountCells--;
            }
        }
    }

    private void markCell(int xPosition, int yPosition){
        ICell[][] cells = board.getGraphCells();
        cells[xPosition][yPosition].mark();
    }

    @Test
    public void testUserNotWonMoreMinesMark() {
        setupStage();
        markCells(8);
        assertFalse("Error in the method WinGame of Board class.", board.winGame(minesAmount));
    }

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
