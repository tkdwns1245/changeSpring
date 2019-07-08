package member.exception;


public class IdPasswordNotMatchingException extends RuntimeException{
	public IdPasswordNotMatchingException(String message) {
		super(message);
	}
	public IdPasswordNotMatchingException(){}
}
