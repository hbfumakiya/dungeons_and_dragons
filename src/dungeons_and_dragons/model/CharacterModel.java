package dungeons_and_dragons.model;
public class CharacterModel {

	/**
	 * Variable for identity of character. Value of all these character must be unique.
	 * 
	 * @type integer
	 */
	private int character_id;
	
	/**
	 * Variable for character name. 
	 * 
	 * @type String
	 */
	private String character_name;
	
	
	/**
	 * Type of character.
	 * 
	 * @type CharacterType
	 */
	private CharacterTypeModel character_type;
	
	
	
	/**
	 * Variable for strength of character
	 * 
	 * @type integer 
	 */
	private int strength;
	
	/**
	 * 
	 * @param charecterType
	 * @param strength
	 */
	public CharacterModel() {
		
	}
}