package member.exception;

public class FaultyAccessException extends RuntimeException{
	public FaultyAccessException(String message) {
		super(message);
	}
	public FaultyAccessException(){}
}
