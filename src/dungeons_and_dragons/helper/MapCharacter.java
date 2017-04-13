/**
 * 
 */
package dungeons_and_dragons.helper;

import com.google.gson.annotations.Expose;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.strategy.CharacterStrategy;

/**
 * @author Mihir Pujara
 *
 */
public class MapCharacter {

	public static final String NORMAL = "Normal";

	public static final String COMPUTER = "Computer";

	public static final String FRIENDLY = "Friendly";

	public static final String ENEMY = "Enemy";
	@Expose
	public boolean Freezing = false;
	@Expose
	public boolean Burning = false;
	@Expose
	public boolean Slaying = false;
	@Expose
	public boolean Frightening = false;
	@Expose
	public boolean Pacifying = false;
	@Expose
	public int frighteningBonus = 0;
	@Expose
	public int freezingBonus = 0;
	@Expose
	public int burningBonus = 0;
	@Expose
	public int frighteningTurn = 0;
	@Expose
	public int freezingTurn = 0;
	@Expose
	public int burningTurn = 0;

	@Expose
	private int x;

	@Expose
	private int y;

	@Expose
	private CharacterModel character;

	@Expose
	private String characterType;

	@Expose
	private CharacterStrategy characterStrategy;

	/**
	 * Default Constructor which set default values of each properties
	 */
	public MapCharacter() {
		this.x = 0;

		this.y = 0;

		this.character = null;

		this.characterType = MapCharacter.NORMAL;

		this.characterStrategy = new CharacterStrategy();
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
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
	 * @param y
	 *            the y to set
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
	 * @param character
	 *            the character to set
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
	 * @param characterType
	 *            the characterType to set
	 */
	public void setCharacterType(String characterType) {
		this.characterType = characterType;
	}

	/**
	 * @return the characterStrategy
	 */
	public CharacterStrategy getCharacterStrategy() {
		return characterStrategy;
	}

	/**
	 * @param characterStrategy
	 *            the characterStrategy to set
	 */
	public void setCharacterStrategy(CharacterStrategy characterStrategy) {
		this.characterStrategy = characterStrategy;
	}

}
