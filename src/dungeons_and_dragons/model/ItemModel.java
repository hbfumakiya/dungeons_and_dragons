package dungeons_and_dragons.model;

/**
 * Item class to store all items. eg Helmet,Ring
 * 
 * @author : Urmil Kansara
 * 
 * 
 */
public class ItemModel {

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

}