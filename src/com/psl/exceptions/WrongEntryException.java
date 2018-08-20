/**
 * Minesweeper Game PSL
 *
 * @Author Jefry Cardona
 */
package com.psl.exceptions;

public class WrongEntryException extends Exception {

    private String message;

    public WrongEntryException(String msg) {
        super(msg);
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
