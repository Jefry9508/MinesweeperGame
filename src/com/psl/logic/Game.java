package com.psl.logic;

import java.io.IOException;
import java.util.Scanner;

public class Game {

    private Board board;

    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.startGame();
    }

    public void startGame(){
        System.out.println("*************************** Welcome to Minesweeper Game ***************************\n");
        System.out.println("Please, write 3 numbers, the first two numbers are the height and width of the board \n" +
                "and the last is the mine amount. Separate each number with blank spaces\n");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int height = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);
        int minesAmount = Integer.parseInt(input[2]);
        board = new Board(height, width, minesAmount);
        board.displayBoard();
        playRoundGame();
    }

    public void playRoundGame(){
        System.out.println("");
        System.out.println("At each round, you can select one cell by entering its row and column index and an\n" +
                "action, either U (uncover) or M (mark). (e.g. '4 6 U' or '4 6 M').\n");
        System.out.println("Let's go play!\n");

        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        String xPosition = input[0];
        String yPosition = input[1];
        String action = input[2];


    }
}
