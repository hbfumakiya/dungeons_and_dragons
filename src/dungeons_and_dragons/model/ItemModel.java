package dungeons_and_dragons.model;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;

import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.view.ItemView;

//import .helper.Game_constants;
//import game.play.ItemWindow;

/**
 * Item class to store all items. eg Helmet,Ring
 * 
 * @author : Urmil Kansara
 * 
 * 
 */
public class ItemModel extends Observable implements Model<ItemModel>{

	/**
	 * ID and Name of items
	 * 
	 * @type : Integer
	 * 
	 */
	private int item_id;

	/**
	 * Name of items
	 * 
	 * @type :String
	 */
	private String item_name;

	/**
	 * Point of item.
	 * 
	 * @type : Integer
	 */

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
	private String item_type;

	/**
	 * Add types of item
	 * 
	 * type : String
	 */
	private String item_ability;
	
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
		// TODO Auto-generated constructor stub
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_point = item_point;
		this.item_type = item_type;
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
	
DefaultComboBoxModel get_item_ability = null;
	
	public DefaultComboBoxModel getItemAbility()
	{return get_item_ability;}
	public void itemTypeSelected(String item_type){
		
		
		
		 // this variable created to get the item ability selected.
		 
		
		
		 
			
			if(item_type.equals(Game_constants.HELMET))
			{
				get_item_ability = Game_constants.HELMET_MODEL;
			}
			else if (item_type.equals(Game_constants.ARMOR))
			{
				
				get_item_ability = Game_constants.ARMOR_MODEL;
			}
			else if (item_type.equals(Game_constants.SHIELD))
			{
				
				get_item_ability = Game_constants.SHIELD_MODEL;
			}
			else if (item_type.equals(Game_constants.RING))
			{
				
				get_item_ability = Game_constants.RING_MODEL;
			}
			else if (item_type.equals(Game_constants.BELT))
			{
				
				get_item_ability = Game_constants.BELT_MODEL;
			}
			else if (item_type.equals(Game_constants.BOOTS))
			{
				
				get_item_ability = Game_constants.BOOTS_MODEL;
			}
			else if (item_type.equals(Game_constants.WEAPON))
			{
				
				get_item_ability = Game_constants.WEAPON_MODEL;
			}
			//String get_item_ability = null;
			setChanged();
	    	// notify all attached Observers of a change
	    	notifyObservers(get_item_ability);
		}

	

	@Override
	public ArrayList<ItemModel> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public void save(ItemModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ItemModel t) {
		// TODO Auto-generated method stub
		
	}
	
	

}