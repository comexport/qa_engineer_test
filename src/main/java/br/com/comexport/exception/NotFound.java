package br.com.comexport.exception;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFound extends QATestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5055607301027693550L;

	public NotFound (final String message) {
		super(NOT_FOUND, message);
		
	}	
	
}
