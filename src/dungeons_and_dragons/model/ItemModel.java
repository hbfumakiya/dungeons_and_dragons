package dungeons_and_dragons.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

import dungeons_and_dragons.exception.NotFoundException;
import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.LogHelper;

/**
 * Item class to store all items. eg Helmet,Ring
 * 
 * @author : Urmil Kansara & Mihir Pujara
 * 
 * 
 */
public class ItemModel extends Observable implements Model<ItemModel> {

	/**
	 * ID and Name of items
	 * 
	 * @type : Integer
	 * 
	 */
	@Expose
	private int item_id;

	/**
	 * Name of items
	 * 
	 * @type :String
	 */
	@Expose
	private String item_name;

	/**
	 * Point of item.
	 * 
	 * @type : Integer
	 */
	@Expose
	private int item_point;

	/**
	 * Add ability by selecting item.
	 *
	 * @type : AbilityScores
	 */

	/**
	 * Add types of item
	 * 
	 * type : String
	 */
	@Expose
	private String item_type;

	/**
	 * Add types of item
	 * 
	 * type : String
	 */
	@Expose
	private String item_ability;
	
	@Expose
	private String item_weapon_enchantment;
	
	@Expose
	private String item_weapon_enchantment_string;

	/**
	 * 
	 * default Constructor
	 */
	public ItemModel() {

	}
	

	


	/**
	 * This constructor sets id,name, point, type and ability;
	 * 
	 * @param item_id
	 *            Id of item
	 * @param item_name
	 *            Name of Item
	 * @param item_point
	 *            Point of Item
	 * @param item_type
	 *            Type of item
	 * @param item_ability
	 *            ABility of item
	 */

	public ItemModel(int item_id, String item_name, int item_point, String item_type, String item_ability) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_point = item_point;
		this.item_type = item_type;
		this.item_ability = item_ability;
		this.isWeaponSelected = false;

	}

	

	/**
	 * @param item_id
	 *            the item_id to set
	 */
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	/**
	 * @param item_name
	 *            the item_name to set
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	/**
	 * @param item_point
	 *            the item_point to set
	 */
	public void setItem_point(int item_point) {
		this.item_point = item_point;
	}

	/**
	 * @param item_type
	 *            the item_type to set
	 */
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	/**
	 * @param item_ability
	 *            the item_ability to set
	 */
	public void setItem_ability(String item_ability) {
		this.item_ability = item_ability;
	}

	/**
	 * @return the item_id
	 */
	public int getItem_id() {
		return item_id;
	}

	/**
	 * @return the item_name
	 */
	public String getItem_name() {
		return item_name;
	}

	/**
	 * @return the item_point
	 */
	public int getItem_point() {
		return item_point;
	}

	/**
	 * @return the item_type
	 */
	public String getItem_type() {
		return item_type;
	}

	/**
	 * @return the item_ability
	 */
	public String getItem_ability() {
		return item_ability;
	}

	/**
	 * sets item ability combobox model
	 */
	private DefaultComboBoxModel set_item_ability = new DefaultComboBoxModel<>();

	/**
	 * 
	 * @return DefaultComboBoxModel
	 */
	public DefaultComboBoxModel getItemAbility() {
		return set_item_ability;
	}
	
	/**
	 * @return the item_weapon_enchantment
	 */
	public String getItem_weapon_enchantment() {
		return item_weapon_enchantment;
	}

	/**
	 * @param item_weapon_enchantment the item_weapon_enchantment to set
	 */
	public void setItem_weapon_enchantment(String item_weapon_enchantment) {
		this.item_weapon_enchantment = item_weapon_enchantment;
	}
	
	/**
	 * @return the item_weapon_enchantment_string
	 */
	public String getItem_weapon_enchantment_string() {
		return item_weapon_enchantment_string;
	}


	/**
	 * @param item_weapon_enchantment_string the item_weapon_enchantment_string to set
	 */
	public void setItem_weapon_enchantment_string(String item_weapon_enchantment_string) {
		this.item_weapon_enchantment_string = item_weapon_enchantment_string;
	}
	
	public boolean isWeaponSelected;

	/**
	 * method to set item ability
	 * 
	 * @param item_type
	 */
	public void itemTypeSelected(String item_type) {

		// this variable created to get the item ability selected.

		if (item_type.equals(Game_constants.HELMET)) {
			set_item_ability = Game_constants.HELMET_MODEL;
			isWeaponSelected = false;
		} else if (item_type.equals(Game_constants.ARMOR)) {

			set_item_ability = Game_constants.ARMOR_MODEL;
			isWeaponSelected = false;
		} else if (item_type.equals(Game_constants.SHIELD)) {

			set_item_ability = Game_constants.SHIELD_MODEL;
			isWeaponSelected = false;
		} else if (item_type.equals(Game_constants.RING)) {

			set_item_ability = Game_constants.RING_MODEL;
			isWeaponSelected = false;
		} else if (item_type.equals(Game_constants.BELT)) {

			set_item_ability = Game_constants.BELT_MODEL;
			isWeaponSelected = false;
		} else if (item_type.equals(Game_constants.BOOTS)) {

			set_item_ability = Game_constants.BOOTS_MODEL;
			isWeaponSelected = false;
		} else if (item_type.equals(Game_constants.WEAPON_MELEE)) {

			set_item_ability = Game_constants.WEAPON_MODEL;
			isWeaponSelected = true;
		} else if (item_type.equals(Game_constants.WEAPON_RANGE)) {

			set_item_ability = Game_constants.WEAPON_MODEL;
			isWeaponSelected = true;
		}
		// String get_item_ability = null;
		setChanged();
		// notify all attached Observers of a change
		notifyObservers(set_item_ability);
	}

	/**
	 * method to get items
	 * 
	 * @throws JsonSyntaxException,IOException
	 * @return ArrayList
	 */
	@Override
	public ArrayList<ItemModel> getData() throws JsonSyntaxException, IOException {
		return FileHelper.getItems();

	}

	/**
	 * method to save items
	 */
	@Override
	public void save() {
		try {
			this.setCurrentId();
			FileHelper.saveItem(this);
		} catch (JsonSyntaxException | IOException e1) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e1.getMessage());
		}

	}

	/**
	 * method to set current id of item
	 * 
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void setCurrentId() throws JsonSyntaxException, IOException {

		ArrayList<ItemModel> alldata = this.getData();
		if (null != alldata) {
			this.item_id = alldata.get(alldata.size() - 1).getItem_id() + 1;
		} else {
			this.item_id = 1;
		}
	}

	/**
	 * method to update item
	 */
	@Override
	public void update() {

		try {

			FileHelper.updateItem(this);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

	}
	
	

}