package com.oscarcpz.jce.exceptions;

public class FileException extends Exception {

    public FileException(final String msg) {
        super(msg);
    }

    public FileException(final String msg, final Exception e) {
        super(msg, e);
    }
}
