/**
 * 
 */
package dungeons_and_dragons.helper;

import javax.swing.JButton;

import com.google.gson.annotations.Expose;

import dungeons_and_dragons.model.CharacterModel;

/**
 * this class is for creating buttons that are used in map
 * 
 * @author Mihir Pujara
 *
 */
public class MapButton extends JButton {

	private String button_type = Game_constants.GRID_BUTTON_TYPE;

	@Expose
	private int xPos;
	
	@Expose
	private int yPos;

	// 0 if it is wall and 2 if it is an enemy
	@Expose
	private int pointValue = 1;

	// 0 if the point is not yet traversed and 1 if the point is traversed already
	@Expose
	private int dirty_flag = 0;
	
	// 0 if there is no path and 1 if there is a path 
	@Expose
	public int path = 0;
	
	@Expose
	private int characterType;
	
	public static final int FRIENDLY_PLAYER = 2;
	
	public static final int ENEMY = 3;
	
	public static final int PLAYER = 1;
	
	@Expose
	private CharacterModel character;

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @param xPos
	 *            the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @param yPos
	 *            the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * 
	 * @return button_type
	 */
	public String getButton_type() {
		return button_type;
	}

	/**
	 * 
	 * @param button_type
	 */
	public void setButton_type(String button_type) {
		this.button_type = button_type;
	}

	/**
	 * 
	 * @return pointValue
	 */
	public int getPointValue() {
		return pointValue;
	}

	/**
	 * 
	 * @param pointValue
	 */
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	/**
	 * 
	 * @return dirty_flag
	 */
	public int getDirty_flag() {
		return dirty_flag;
	}

	/**
	 * 
	 * @param dirty_flag
	 */
	public void setDirty_flag(int dirty_flag) {
		this.dirty_flag = dirty_flag;
	}

	/**
	 * @return the characterType
	 */
	public int getCharacterType() {
		return characterType;
	}

	/**
	 * @param characterType the characterType to set
	 */
	public void setCharacterType(int characterType) {
		this.characterType = characterType;
	}

	/**
	 * @return the character
	 */
	public CharacterModel getCharacter() {
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(CharacterModel character) {
		this.character = character;
	}
	
	
}
