/**
 * Minesweeper Game PSL
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

import com.psl.exceptions.WrongEntryException;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    private Board board;

    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.startGame();
    }

    private void startGame(){
        System.out.println("*************************** Welcome to Minesweeper Game ***************************\n");
        System.out.println("Please, write 3 numbers, the first two numbers are the height and width of the board \n" +
                "and the last is the mine amount. Separate each number with blank spaces\n");
        Scanner scanner = new Scanner(System.in);
        int minesAmount = 0;

        do {
            try {
                String[] input = scanner.nextLine().split(" ");
                int height = Integer.parseInt(input[0]);
                int width = Integer.parseInt(input[1]);
                minesAmount = Integer.parseInt(input[2]);
                board = new Board(height, width, minesAmount);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid number format\n");
            }
        }while (board == null);
        board.displayBoard();
        playRoundGame(minesAmount);
    }

    private void playRoundGame(int minesAmount) {
        System.out.println("");
        System.out.println("At each round, you can select one cell by entering its row and column index and an\n" +
                "action, either U (uncover) or M (mark). (e.g. '4 6 U' or '4 6 M').\n");
        System.out.println("Let's go play!\n");

        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        do {
            try {
                String[] input = scanner.nextLine().split(" ");
                int xPosition = Integer.parseInt(input[0]);
                int yPosition = Integer.parseInt(input[1]);
                String action = input[2].toUpperCase();
                if (action.equals("U")) {
                    if (board.isGameOver(xPosition, yPosition)) {
                        board.lostGame();
                        gameOver = true;
                    } else {
                        if (!board.isBlankCell(xPosition, yPosition)) {
                            board.amountMinesAround(xPosition, yPosition);
                            if (board.winGame(minesAmount)) {
                                System.out.println("You Win, Congratulations!");
                                break;
                            }
                        } else {
                            throw new WrongEntryException();
                        }
                    }
                }
                if (action.equals("M")) {
                    board.markCell(xPosition, yPosition);
                    if (board.winGame(minesAmount)) {
                        System.out.println("You Win, Congratulations!");
                        break;
                    }
                }
            }catch (Exception e){
                System.out.println("\n"+e.getMessage()+"\n");
            }
        }while (!gameOver);

    }

}
