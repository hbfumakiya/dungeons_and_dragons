package dungeons_and_dragons.model;

import com.google.gson.annotations.Expose;

/**
 * Once AbilityScoresModel gets a change state query request from any view they
 * respond to instructions to change the state from AbilityScoresController
 * 
 * @author : Mihir Pujara
 * 
 */

public class AbilityScoresModel {

	/**
	 * Variable for straight
	 * 
	 * @type integer
	 */
	@Expose
	private int strength;

	/**
	 * Variable for dexterity.
	 * 
	 * @type integer
	 */
	@Expose
	private int dexterity;

	/**
	 * Variable for constitution.
	 * 
	 * @type integer
	 */
	@Expose
	private int constitution;

	/**
	 * Variable for intelligence.
	 * 
	 * @type integer
	 */
	@Expose
	private int intelligence;

	/**
	 * Variable for wisdom.
	 * 
	 * @type integer
	 */
	@Expose
	private int wisdom;

	/**
	 * Variable for charisma.
	 * 
	 * @type integer
	 */
	@Expose
	private int charisma;

	/**
	 * constructor for ability scores
	 * <p>
	 * initialize all abilities
	 */
	public AbilityScoresModel() {

		this.strength = 0;
		this.dexterity = 0;
		this.constitution = 0;
		this.intelligence = 0;
		this.wisdom = 0;
		this.charisma = 0;

	}

	/**
	 * @return the straight
	 */
	public int getStraight() {
		return strength;
	}

	/**
	 * @param strength
	 *            the strength to set
	 */
	public void setstrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return the dexterity
	 */
	public int getDexterity() {
		return dexterity;
	}

	/**
	 * @param dexterity
	 *            the dexterity to set
	 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	/**
	 * @return the constitution
	 */
	public int getConstitution() {
		return constitution;
	}

	/**
	 * @param constitution
	 *            the constitution to set
	 */
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	/**
	 * @return the intelligence
	 */
	public int getIntelligence() {
		return intelligence;
	}

	/**
	 * @param intelligence
	 *            the intelligence to set
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	/**
	 * @return the wisdom
	 */
	public int getWisdom() {
		return wisdom;
	}

	/**
	 * @param wisdom
	 *            the wisdom to set
	 */
	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	/**
	 * @return the charisma
	 */
	public int getCharisma() {
		return charisma;
	}

	/**
	 * @param charisma
	 *            the charisma to set
	 */
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

}