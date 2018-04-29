package com.accent.handler;

public class GenericError extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public GenericError() {
    super();
  }

  public GenericError(String arg0) {
    super(arg0);
  }

  public GenericError(Throwable arg0) {
    super(arg0);
  }

  public GenericError(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public GenericError(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }
}
