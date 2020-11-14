package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.exception;

public class BadRequestParamValueException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String paramName;

	private String paramValue;

	public BadRequestParamValueException() {
		super();
	}

	public BadRequestParamValueException(String message) {
		super(message);
	}

	public BadRequestParamValueException(String paramName, String paramValue) {
		super();
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return this.paramName;
	}

	public String getParamValue() {
		return this.paramValue;
	}
}
