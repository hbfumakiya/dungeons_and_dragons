/**
 * 
 */
package dungeons_and_dragons.exception;

/**
 * @author Mihir Pujara
 *
 */
public class NotFoundException extends Exception {

	private String message;
	
	/**
	 * 
	 */
	public NotFoundException() {
		this.message = "Item not found";
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
