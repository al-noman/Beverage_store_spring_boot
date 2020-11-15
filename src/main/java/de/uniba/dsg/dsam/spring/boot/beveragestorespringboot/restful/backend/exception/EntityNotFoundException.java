package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.exception;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String paramName;

	private String paramValue;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(int id){
		super("Entity not found with Id: "+id);
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String paramName, String paramValue) {
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
