package by.pavka.xmltask;

public class ParsingException extends Exception {
  public ParsingException() {
  }

  public ParsingException(String message) {
    super(message);
  }

  public ParsingException(String message, Throwable cause) {
    super(message, cause);
  }
}
