package member.exception;

public class DatabaseException extends RuntimeException{
	public DatabaseException(String message) {
		super(message);
	}
	public DatabaseException(){}
}
