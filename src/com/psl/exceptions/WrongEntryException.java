/**
 * Minesweeper Game PSL-Challenge
 *
 * @Author Jefry Cardona
 */
package com.psl.exceptions;

public class WrongEntryException extends Exception {

    //ATTRIBUTES

    /**
     * Information of the exception.
     */
    private String message;


    //METHODS

    /**
     * Constructor of the WrongEntryException class.
     * @param msg, related information to the exception.
     */
    public WrongEntryException(String msg) {
        super(msg);
        this.message = msg;
    }

    /**
     * Method that gives the information of the exception.
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }
}
