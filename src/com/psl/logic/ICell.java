package com.psl.logic;

public interface ICell<T> {

    void uncover();
    void mark();
    String getState();

}
