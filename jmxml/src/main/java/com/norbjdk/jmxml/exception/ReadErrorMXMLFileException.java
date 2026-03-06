package com.norbjdk.jmxml.exception;

import java.io.IOException;

public class ReadErrorMXMLFileException extends RuntimeException {
  public ReadErrorMXMLFileException(String message) {
    super(message);
  }
  public ReadErrorMXMLFileException(String message, IOException e) {
    super(message, e);
  }
}
