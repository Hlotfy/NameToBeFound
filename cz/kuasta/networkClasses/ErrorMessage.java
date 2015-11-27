package cz.kuasta.networkClasses;

public class ErrorMessage extends Response {
	public String errorTitle, message;
	
	public ErrorMessage(String errorTitle, String message){
		this.errorTitle = errorTitle;
		this.message = message;
	}
	
}
