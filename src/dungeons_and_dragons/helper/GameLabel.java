/**
 * 
 */
package dungeons_and_dragons.helper;

import javax.swing.JLabel;

/**
 * @author Mihir Pujara
 *
 */
public class GameLabel extends JLabel {

	private int xPosition;

	private int yPosition;

	/**
	 * @return the xPosition
	 */
	public int getxPosition() {
		return xPosition;
	}

	/**
	 * @param xPosition
	 *            the xPosition to set
	 */
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * @return the yPosition
	 */
	public int getyPosition() {
		return yPosition;
	}

	/**
	 * @param yPosition
	 *            the yPosition to set
	 */
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

}
