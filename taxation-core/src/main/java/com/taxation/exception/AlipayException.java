package com.taxation.exception;

public class AlipayException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -792641512095093804L;
	private String exceptionCode;
	private String exceptionMessage;

	public AlipayException(String exceptionCode, String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionCode = exceptionCode;
		this.exceptionMessage = exceptionMessage;

	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
