/**
 * Minesweeper Game PSL-Challenge
 *
 * @Author Jefry Cardona
 */
package com.psl.logic;

import com.psl.exceptions.WrongEntryException;


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
        System.out.println("");
        System.out.println("At each round, you can select one cell by entering its row and column index and an\n" +
                "action, either U (uncover) or M (mark). (e.g. '4 6 U' or '4 6 M').\n");
        System.out.println("Let's go play!\n");
        boolean finished = false;
        do {
            try {
                playRoundGame(minesAmount);
                finished = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while (!finished);
    }

    private void playRoundGame(int minesAmount) throws Exception{

        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        do {

                String[] input = scanner.nextLine().split(" ");
                int xPosition = Integer.parseInt(input[0])-1;
                int yPosition = Integer.parseInt(input[1])-1;
                if(xPosition < 0 || xPosition >= board.getHeight() || yPosition < 0 || yPosition >= board.getWidth()){
                    throw new WrongEntryException("\nInvalid index of row or column");
                }
                String action = input[2].toUpperCase();
                if (action.equals("U")) {
                    if (board.isGameOver(xPosition, yPosition)) {
                        board.lostGame();
                        gameOver = true;
                    } else {
                        if (!board.isBlankCell(xPosition, yPosition)) {
                            board.amountMinesAround(xPosition, yPosition);

                        } else {
                            throw new WrongEntryException("\nThis cell is locked");
                        }
                    }
                }else {
                    if (action.equals("M")) {
                        if (!board.isBlankCell(xPosition, yPosition)) {
                            board.markCell(xPosition, yPosition);
                            if (board.winGame(minesAmount)) {
                                System.out.println("\nYou Win, Congratulations!");
                                break;
                            }
                        } else {
                            throw new WrongEntryException("\nThis cell is locked");
                        }
                    }else{
                        throw new WrongEntryException("\nInvalid Action");
                    }
                }

        }while (!gameOver);

    }

}
