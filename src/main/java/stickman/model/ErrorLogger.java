package stickman.model;

/** prints out an error and exits the program if an error is raised in any parts of the program */
public class ErrorLogger {
  private String message;

  /**
   * Called by different parts of the program whenever an error is raised Prints out the relevant
   * error message and quits the program.
   */
  protected void log() {
    System.err.println(this.getMessage());
    System.exit(-1);
  }

  /**
   * Updates the message that is printed by the error logger to System.err. Updates error logger's
   * message every time it is called.
   *
   * @param message The new message to replace the old one
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Returns the message that is used by the object to print errors on the console. Used when in the
   * log error when writing the error to console
   *
   * @return The message the object must printed out ot the console.
   */
  public String getMessage() {
    return this.message;
  }
}
