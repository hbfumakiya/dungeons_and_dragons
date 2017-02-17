package dungeons_and_dragons.model;
/**
 * Item class to store all items.
 * eg Helmet,Ring
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
	 *@type : AbilityScores
	 */
	
	private AbilityScoresModel ability_scores ;
	
	
	
	public ItemModel(int item_id,
			String item_name,
			int item_point) 
	{
		// TODO Auto-generated constructor stub
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_point = item_point;
		ability_scores = new AbilityScoresModel();
		
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
	 * @return the ability_scores
	 */
	public AbilityScoresModel getAbility_scores() {
		return ability_scores;
	}
}