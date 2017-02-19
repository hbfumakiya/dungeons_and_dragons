/**
 * 
 */
package dungeons_and_dragons.helper;

import javax.swing.JButton;

/**
 * @author Mihir Pujara
 *
 */
public class GameButton extends JButton {

	public static final int BUTTON_TYPE_EDIT = 1;
	
	public static final int BUTTON_TYPE_DELETE = 2;
	
	public static final int BUTTON_TYPE_VIEW = 3;
	
	private int id;
	
	private int buttonType;
	
	private Object source;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the buttonType
	 */
	public int getButtonType() {
		return buttonType;
	}

	/**
	 * @param buttonType the buttonType to set
	 */
	public void setButtonType(int buttonType) {
		this.buttonType = buttonType;
	}

	/**
	 * @return the source
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Object source) {
		this.source = source;
	}
	
	
}
