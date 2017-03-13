/**
 * 
 */
package dungeons_and_dragons.helper;

import dungeons_and_dragons.model.CharacterModel;

/**
 * @author Mihir Pujara
 *
 */
public class MapCharacter {

	public static final String NORMAL = "Normal";
	
	public static final String FRIENDLY = "Friendly";
	
	public static final String ENEMY = "Enemy";
	
	private int x;
	
	private int y;
	
	private CharacterModel character;
	
	private String characterType;
	
	/**
	 *	 Default Constructor which set default values of each properties 
	 */
	public MapCharacter() {
		this.x = 0;
		
		this.y = 0;
		
		this.character = null;
		
		this.characterType = MapCharacter.NORMAL;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
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

	/**
	 * @return the characterType
	 */
	public String getCharacterType() {
		return characterType;
	}

	/**
	 * @param characterType the characterType to set
	 */
	public void setCharacterType(String characterType) {
		this.characterType = characterType;
	}
}
