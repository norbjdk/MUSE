package com.norbjdk.jmxml.exception;

import java.io.IOException;

public class NotFoundMXMLFileException extends RuntimeException {
    public NotFoundMXMLFileException(String message) {
        super(message);
    }
    public NotFoundMXMLFileException(String message, IOException e) {
        super(message, e);
    }
}
