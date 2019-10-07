package com.symantec.exoplanet.exception;

public class ExoPlanetServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public static final String EXOPLANET_NO_DATAFOUND_EXCEPTION="No Data found";

	public ExoPlanetServiceException() {
		super();
	}

	public ExoPlanetServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExoPlanetServiceException(String message) {
		super(message);
	}

	public ExoPlanetServiceException(Throwable cause) {
		super(cause);
	}
}
