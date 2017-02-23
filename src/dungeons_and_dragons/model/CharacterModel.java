package dungeons_and_dragons.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

import dungeons_and_dragons.helper.DiceHelper;
import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.LogHelper;

/**
 * character class to store all character data
 * 
 * @author : Hirangi Naik
 * 
 * 
 */

public class CharacterModel extends Observable implements Model<CharacterModel> {

	/**
	 * Variable for identity of character. Value of all these character must be
	 * unique.
	 * 
	 * @type integer
	 */
	@Expose
	private int character_id;

	/**
	 * Variable for character name.
	 * 
	 * @type String
	 */
	@Expose
	private String character_name;

	/**
	 * Variable for strength of character
	 * 
	 * @type integer
	 */
	@Expose
	private int strength;

	/**
	 * 
	 * 
	 */
	@Expose
	private ArrayList<ItemModel> items;

	/**
	 * 
	 * 
	 */
	@Expose
	private ArrayList<ItemModel> backPackItems;

	/**
	 * Variable for level of character
	 * 
	 * @type integer
	 */
	@Expose
	private int character_level;

	/**
	 * @return the character_level
	 */
	public int getCharacter_level() {
		return character_level;
	}

	/**
	 * @param character_level
	 *            the character_level to set
	 */
	public void setCharacter_level(int character_level) {
		this.character_level = character_level;
	}

	public CharacterModel() {
		this.character_id = 0;
		this.character_name = "";
		this.strength = 0;
		this.items = new ArrayList<ItemModel>();
		this.backPackItems = new ArrayList<ItemModel>();
	}

	/**
	 * 
	 * @param charecterType
	 * @param strength
	 */
	public CharacterModel(int character_id, String character_name, int strength) {
		// TODO Auto-generated constructor stub
		this.character_id = character_id;
		this.character_name = character_name;
		this.strength = strength;
		this.items = new ArrayList<ItemModel>();
		this.backPackItems = new ArrayList<ItemModel>();
	}

	/**
	 * @return the character_id
	 */
	public int getCharacter_id() {
		return character_id;
	}

	/**
	 * @param character_id
	 *            the character_id to set
	 */
	public void setCharacter_id(int character_id) {
		this.character_id = character_id;
	}

	/**
	 * @return the items
	 */
	public ArrayList<ItemModel> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(ArrayList<ItemModel> items) {
		this.items = items;
	}

	/**
	 * @return the character_name
	 */
	public String getCharacter_name() {
		return character_name;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param character_name
	 *            the character_name to set
	 */
	public void setCharacter_name(String character_name) {
		this.character_name = character_name;
	}

	/**
	 * @param strength
	 *            the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return the backPackItems
	 */

	public ArrayList<ItemModel> getBackPackItems() {
		return backPackItems;
	}

	/**
	 * @param backPackItems
	 *            the backPackItems to set
	 */
	public void setBackPackItems(ArrayList<ItemModel> backPackItems) {
		this.backPackItems = backPackItems;
	}

	@Override
	public ArrayList<CharacterModel> getData() throws JsonSyntaxException, IOException {

		return FileHelper.getCharcters();
	}

	private void setCurrentId() throws JsonSyntaxException, IOException {

		ArrayList<CharacterModel> alldata = this.getData();

		if ((alldata == null) || (alldata.size() < 1)) {
			this.character_id = 1;
			return;
		}

		CharacterModel lastData = Collections.max(alldata, new Comparator<CharacterModel>() {

			@Override
			public int compare(CharacterModel o1, CharacterModel o2) {

				if (o1.getCharacter_id() > o2.getCharacter_id())
					return -1; // highest value first
				else if (o1.getCharacter_id() == o2.getCharacter_id())
					return 0;
				else
					return 1;
			}
		});

		this.character_id = lastData.getCharacter_id() + 1;
	}

	@Override
	public void save() {
		try {
			this.setCurrentId();
			FileHelper.saveCharacter(this);
		} catch (JsonSyntaxException | IOException e1) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e1.getMessage());
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}