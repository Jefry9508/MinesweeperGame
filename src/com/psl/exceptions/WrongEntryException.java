/**
 * Minesweeper Game PSL
 *
 * @Author Jefry Cardona
 */
package com.psl.exceptions;

public class WrongEntryException extends Exception {

    private String message;

    public WrongEntryException() {
        super("Invalid input");
        this.message = "Invalid input";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
