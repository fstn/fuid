package org.fuid.exception;

public class FuidException extends Exception {
	private FuildExceptionType type;

	public FuidException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FuidException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FuidException(String message, FuildExceptionType type,
			Throwable cause) {
		super(message, cause);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public FuidException(String message, FuildExceptionType type) {
		super(message);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public FuildExceptionType getType() {
		return type;
	}

	public void setType(FuildExceptionType type) {
		this.type = type;
	}

}
