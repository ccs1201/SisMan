package com.doiscs.sisman.exceptions;

public class DoisCsCrudException extends Exception {


    private static final long serialVersionUID = -1342836291193770033L;

    public DoisCsCrudException(String msg) {
        super(msg);

    }

    public DoisCsCrudException(String msg, Throwable t) {
        super(msg, t);

    }

}
