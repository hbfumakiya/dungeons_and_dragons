package dungeons_and_dragons.model;

import java.util.ArrayList;
import java.util.Observable;

import dungeons_and_dragons.helper.Game_constants;

/**
 * character class to store all character data
 * 
 * @author : Hirangi Naik
 * 
 * 
 */

public class CharacterModel extends Observable implements Model<CharacterModel>{

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
	
	public CharacterModel(int character_id, String character_name, int strength) {
		// TODO Auto-generated constructor stub
		this.character_id=character_id;
		this.character_name=character_name;
		this.strength=strength;

	}

	public int getCharacter_id() {
		return character_id;
	}

	public String getCharacter_name() {
		return character_name;
	}

	public int getStrength() {
		return strength;
	}

	public void itemTypeSelected(String item_type){
		
		 // this variable created to get the item ability selected.
		 
	}

	@Override
	public void save(CharacterModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<CharacterModel> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CharacterModel t) {
		// TODO Auto-generated method stub
		
	}

}