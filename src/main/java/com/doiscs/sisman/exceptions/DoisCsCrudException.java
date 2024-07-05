package com.doiscs.sisman.exceptions;

public class DoisCsCrudException extends Exception {

    public DoisCsCrudException(String msg) {
        super(msg);
    }

    public DoisCsCrudException(String msg, Throwable t) {
        super(msg, t);
    }
}
