package dungeons_and_dragons.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

import dungeons_and_dragons.exception.NotFoundException;
import dungeons_and_dragons.helper.DiceHelper;
import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.LogHelper;

/**
 * character class to store all character data
 * 
 * @author : Hirangi Naik & Mihir Pujara
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

	@Expose
	private ArrayList<ItemModel> items;

	@Expose
	private ArrayList<ItemModel> backPackItems;

	/**
	 * Variable for level of character
	 * 
	 * @type integer
	 */
	@Expose
	private int character_level;

	@Expose
	private AbilityScoresModel abilityScores;

	@Expose
	private AbilityScoresModel rawAbilityScores;

	@Expose
	private AbilityScoresModel modifiers;

	@Expose
	private int hitpoints;

	@Expose
	private int attackBonus;

	@Expose
	private int damageBonus;

	@Expose
	private int armorClass;

	/**
	 * Default constructor used to initialize character features
	 */
	public CharacterModel() {
		this.character_id = 0;
		this.character_name = "";
		this.strength = 0;
		this.items = new ArrayList<ItemModel>();
		this.backPackItems = new ArrayList<ItemModel>();
		this.abilityScores = new AbilityScoresModel();
		this.rawAbilityScores = new AbilityScoresModel();
		this.modifiers = new AbilityScoresModel();
		this.hitpoints = 0;
		this.attackBonus = 0;
		this.damageBonus = 0;
		this.armorClass = 0;
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
	 * @return the character_name
	 */
	public String getCharacter_name() {
		return character_name;
	}

	/**
	 * @param character_name
	 *            the character_name to set
	 */
	public void setCharacter_name(String character_name) {
		this.character_name = character_name;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param strength
	 *            the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
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

	/**
	 * @return the abilityScores
	 */
	public AbilityScoresModel getAbilityScores() {
		return abilityScores;
	}

	/**
	 * @param abilityScores
	 *            the abilityScores to set
	 */
	public void setAbilityScores(AbilityScoresModel abilityScores) {
		this.abilityScores = abilityScores;
	}

	/**
	 * @return the modifiers
	 */
	public AbilityScoresModel getModifiers() {
		return modifiers;
	}

	/**
	 * @param modifiers
	 *            the modifiers to set
	 */
	public void setModifiers(AbilityScoresModel modifiers) {
		this.modifiers = modifiers;
	}

	/**
	 * @return the hitpoints
	 */
	public int getHitpoints() {
		return hitpoints;
	}

	/**
	 * @param hitpoints
	 *            the hitpoints to set
	 */
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	/**
	 * @return the attackBonus
	 */
	public int getAttackBonus() {
		return attackBonus;
	}

	/**
	 * @param attackBonus
	 *            the attackBonus to set
	 */
	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}

	/**
	 * @return the damageBonus
	 */
	public int getDamageBonus() {
		return damageBonus;
	}

	/**
	 * @param damageBonus
	 *            the damageBonus to set
	 */
	public void setDamageBonus(int damageBonus) {
		this.damageBonus = damageBonus;
	}

	/**
	 * @return the armorClass
	 */
	public int getArmorClass() {
		return armorClass;
	}

	/**
	 * @param armorClass
	 *            the armorClass to set
	 */
	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	/**
	 * @return the rawAbilityScores
	 */
	public AbilityScoresModel getRawAbilityScores() {
		return rawAbilityScores;
	}

	/**
	 * @param rawAbilityScores
	 *            the rawAbilityScores to set
	 */
	public void setRawAbilityScores(AbilityScoresModel rawAbilityScores) {
		this.rawAbilityScores = rawAbilityScores;
	}

	/**
	 * Method to get all created characters
	 * 
	 * @return ArrayList characters
	 */
	@Override
	public ArrayList<CharacterModel> getData() throws JsonSyntaxException, IOException {

		return FileHelper.getCharcters();
	}

	/**
	 * method to set current id of character
	 * 
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	private void setCurrentId() throws JsonSyntaxException, IOException {

		ArrayList<CharacterModel> alldata = this.getData();
		if (null != alldata) {
			this.character_id = alldata.get(alldata.size() - 1).getCharacter_id() + 1;
		} else {
			this.character_id = 1;
		}
	}

	/**
	 * method to save character
	 */
	@Override
	public void save() {
		try {
			this.setCurrentId();
			FileHelper.saveCharacter(this);
		} catch (JsonSyntaxException | IOException e1) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e1.getMessage());
		}
	}

	/**
	 * method to update character
	 */
	@Override
	public void update() {
		try {
			FileHelper.updateCharacter(this);
		} catch (JsonSyntaxException | IOException | NotFoundException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
	}

	/**
	 * method to calculate all 6 ability scores
	 */
	public void calculateAbilityScores() {
		this.abilityScores.setstrength(this.calculate4D6());
		this.abilityScores.setCharisma(this.calculate4D6());
		this.abilityScores.setConstitution(this.calculate4D6());
		this.abilityScores.setDexterity(this.calculate4D6());
		this.abilityScores.setIntelligence(this.calculate4D6());
		this.abilityScores.setWisdom(this.calculate4D6());

		this.rawAbilityScores.setstrength(this.abilityScores.getStraight());
		this.rawAbilityScores.setCharisma(this.abilityScores.getCharisma());
		this.rawAbilityScores.setConstitution(this.abilityScores.getConstitution());
		this.rawAbilityScores.setDexterity(this.abilityScores.getDexterity());
		this.rawAbilityScores.setIntelligence(this.abilityScores.getIntelligence());
		this.rawAbilityScores.setWisdom(this.abilityScores.getWisdom());
		
		setChanged();
		
		notifyObservers();

	}

	/**
	 * method that calculate modifiers based on items selected
	 */
	public void calculateModifires() {

		ItemModel item;
		for (int i = 0; i < this.items.size(); i++) {
			item = this.items.get(i);

			if (item != null) {
				if (item.getItem_type().equals(Game_constants.HELMET)) {

					if (item.getItem_ability().equals(Game_constants.INTELLIGENCE)) {

						this.abilityScores
								.setIntelligence(this.rawAbilityScores.getIntelligence() + item.getItem_point());

					} else if (item.getItem_ability().equals(Game_constants.WISDOM)) {

						this.abilityScores.setWisdom(this.rawAbilityScores.getWisdom() + item.getItem_point());

					}

				} else if (item.getItem_type().equals(Game_constants.RING)) {

					if (item.getItem_ability().equals(Game_constants.STRENGTH)) {

						this.abilityScores.setstrength(this.rawAbilityScores.getStraight() + item.getItem_point());

					} else if (item.getItem_ability().equals(Game_constants.CONSTITUTION)) {

						this.abilityScores
								.setConstitution(this.rawAbilityScores.getConstitution() + item.getItem_point());

					} else if (item.getItem_ability().equals(Game_constants.WISDOM)) {

						this.abilityScores.setWisdom(this.rawAbilityScores.getWisdom() + item.getItem_point());

					} else if (item.getItem_ability().equals(Game_constants.CHARISMA)) {

						this.abilityScores.setCharisma(this.rawAbilityScores.getCharisma() + item.getItem_point());

					}

				} else if (item.getItem_type().equals(Game_constants.BELT)) {

					if (item.getItem_ability().equals(Game_constants.STRENGTH)) {

						this.abilityScores.setstrength(this.rawAbilityScores.getStraight() + item.getItem_point());

					} else if (item.getItem_ability().equals(Game_constants.CONSTITUTION)) {

						this.abilityScores
								.setConstitution(this.rawAbilityScores.getConstitution() + item.getItem_point());

					}

				} else if (item.getItem_type().equals(Game_constants.BOOTS)) {

					if (item.getItem_ability().equals(Game_constants.DEXTERITY)) {

						this.abilityScores.setDexterity(this.rawAbilityScores.getDexterity() + item.getItem_point());

					}

				}
			}

		}

		this.modifiers.setstrength(getModifiersFromScore(this.abilityScores.getStraight()));
		this.modifiers.setDexterity(getModifiersFromScore(this.abilityScores.getDexterity()));
		this.modifiers.setConstitution(getModifiersFromScore(this.abilityScores.getConstitution()));
		this.modifiers.setIntelligence(getModifiersFromScore(this.abilityScores.getIntelligence()));
		this.modifiers.setWisdom(getModifiersFromScore(this.abilityScores.getWisdom()));
		this.modifiers.setCharisma(getModifiersFromScore(this.abilityScores.getCharisma()));
		
		setChanged();
		
		notifyObservers();

	}

	/**
	 * method to calculate hit points based on level
	 * 
	 * @param level
	 */
	public void calculateHitPoints(int level) {

		int hitPoints = 0;
		if (level > 0) {
			for (int i = 1; i <= level; i++) {
				if (i == 1) {
					hitPoints += 10 + this.modifiers.getConstitution();
				} else {
					hitPoints += DiceHelper.rollD10() + this.modifiers.getConstitution();
				}
			}
		}

		this.hitpoints = hitPoints;
		
		setChanged();
		
		notifyObservers();
	}

	/**
	 * method to calculate armor class
	 */
	public void calculateArmorClass() {
		int armorClass = 10 + this.modifiers.getDexterity();
		ItemModel item;
		for (int i = 0; i < this.items.size(); i++) {
			item = this.items.get(i);
			if (item != null) {
				if (item.getItem_type().equals(Game_constants.HELMET)) {

					if (item.getItem_ability().equals(Game_constants.ARMOR_CLASS)) {
						armorClass += item.getItem_point();
					}

				} else if (item.getItem_type().equals(Game_constants.ARMOR)) {

					if (item.getItem_ability().equals(Game_constants.ARMOR_CLASS)) {
						armorClass += item.getItem_point();
					}

				} else if (item.getItem_type().equals(Game_constants.SHIELD)) {

					if (item.getItem_ability().equals(Game_constants.ARMOR_CLASS)) {
						armorClass += item.getItem_point();
					}

				} else if (item.getItem_type().equals(Game_constants.RING)) {

					if (item.getItem_ability().equals(Game_constants.ARMOR_CLASS)) {
						armorClass += item.getItem_point();
					}
				} else if (item.getItem_type().equals(Game_constants.RING)) {

					if (item.getItem_ability().equals(Game_constants.ARMOR_CLASS)) {
						armorClass += item.getItem_point();
					}
				} else if (item.getItem_type().equals(Game_constants.BOOTS)) {

					if (item.getItem_ability().equals(Game_constants.ARMOR_CLASS)) {
						armorClass += item.getItem_point();
					}
				}
			}
		}
		this.armorClass = armorClass;
		
		setChanged();
		
		notifyObservers();
	}

	/**
	 * method to calculate attack bonus based on level
	 * 
	 * @param level
	 */
	public void calculateAttackBonus(int level) {

		int attackBonus = level;

		ItemModel item;
		for (int i = 0; i < this.items.size(); i++) {
			item = this.items.get(i);
			if (item != null) {
				if (item.getItem_type().equals(Game_constants.WEAPON_MELEE)) {
					attackBonus += this.modifiers.getStraight();
					if (item.getItem_ability().equals(Game_constants.ATTACK_BONUS)) {
						attackBonus += item.getItem_point();
					}
				} else if (item.getItem_type().equals(Game_constants.WEAPON_RANGE)) {
					attackBonus += this.modifiers.getDexterity();
					if (item.getItem_ability().equals(Game_constants.ATTACK_BONUS)) {
						attackBonus += item.getItem_point();
					}
				}
			}
		}

		this.attackBonus = attackBonus;
		
		
		setChanged();
		
		notifyObservers();
	}

	/**
	 * method to calculate damage bonus
	 */
	public void calculateDamageBonus() {

		int damageBonus = DiceHelper.rollD6();

		ItemModel item;
		for (int i = 0; i < this.items.size(); i++) {
			item = this.items.get(i);
			if (item != null) {
				if (item.getItem_type().equals(Game_constants.WEAPON_MELEE)) {
					damageBonus += this.modifiers.getStraight();
					if (item.getItem_ability().equals(Game_constants.DAMAGE_BONUS)) {
						damageBonus += item.getItem_point();
					}
				} else if (item.getItem_type().equals(Game_constants.WEAPON_RANGE)) {
					if (item.getItem_ability().equals(Game_constants.DAMAGE_BONUS)) {
						damageBonus += item.getItem_point();
					}
				}
			}
		}

		this.damageBonus = damageBonus;
		
		setChanged();
		
		notifyObservers();

	}

	/**
	 * method to calculate 4D6 roll dice
	 * 
	 * @return
	 */
	public int calculate4D6() {

		ArrayList<Integer> data = new ArrayList<Integer>();

		for (int i = 0; i < 4; i++) {
			data.add(DiceHelper.rollD6());
		}

		Integer min = Collections.min(data);

		data.remove(min);

		int sum = data.stream().mapToInt(Integer::intValue).sum();
		return sum;
	}

	/**
	 * method to get modifier from the calculated score
	 * 
	 * @param score
	 * @return modifier
	 */
	public int getModifiersFromScore(int score) {
		int modifier = -5;
		if (score == 1) {
			modifier = -5;
		} else if (score == 2 || score == 3) {
			modifier = -4;
		} else if (score == 4 || score == 5) {
			modifier = -3;
		} else if (score == 6 || score == 7) {
			modifier = -2;
		} else if (score == 8 || score == 9) {
			modifier = -1;
		} else if (score == 10 || score == 11) {
			modifier = 0;
		} else if (score == 12 || score == 13) {
			modifier = 1;
		} else if (score == 14 || score == 15) {
			modifier = 2;
		} else if (score == 16 || score == 17) {
			modifier = 3;
		} else if (score == 18 || score == 19) {
			modifier = 4;
		} else if (score == 20 || score == 21) {
			modifier = 5;
		} else if (score == 22 || score == 23) {
			modifier = 6;
		} else if (score == 24 || score == 25) {
			modifier = 7;
		} else if (score == 26 || score == 27) {
			modifier = 8;
		} else if (score == 28 || score == 29) {
			modifier = 9;
		} else if (score == 30 || score == 31) {
			modifier = 10;
		} else if (score == 32 || score == 33) {
			modifier = 11;
		} else if (score == 34 || score == 35) {
			modifier = 12;
		} else if (score == 36 || score == 37) {
			modifier = 13;
		} else if (score == 38 || score == 39) {
			modifier = 14;
		} else if (score == 40 || score == 41) {
			modifier = 15;
		} else if (score == 42 || score == 43) {
			modifier = 16;
		} else if (score == 44 || score == 45) {
			modifier = 17;
		} else if (score == 46 || score == 47) {
			modifier = 18;
		}

		return modifier;
	}
}