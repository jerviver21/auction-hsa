package edu.auctionhsa.model.exception;

public class ErrorInfo {
	public enum TYPE{INVALID_AMOUNT}
	
	private TYPE type;
	
	private String message;
	
	public ErrorInfo(TYPE type, String message){
		this.type = type;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}
	
	

}
