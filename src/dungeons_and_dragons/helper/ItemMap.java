package dungeons_and_dragons.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is to create a map for items and their possible set of abilities provided in requirement
 * 
 * @author : Tejas Sadrani
 */

public class ItemMap {

	/**
	 * Create a hashmap of items with their possible range of abilities
	 * 
	 * @type Hashmap
	 */
    HashMap<String, List<String>> item_map = new HashMap<>();
	    
    /**
	 * create Abilities array list for helmet item 
	 * 
	 * @type ArrayList
	 */
    List<String> helmet_abilities = new ArrayList<String>();
    
    /**
   	 * create Abilities array list for armor item 
   	 * 
   	 * @type ArrayList
   	 */
    List<String> armor_abilities = new ArrayList<String>();
    
    /**
   	 * create Abilities array list for shield item 
   	 * 
   	 * @type ArrayList
   	 */
    List<String> shield_abilities = new ArrayList<String>();
    
    /**
   	 * create Abilities array list for ring item 
   	 * 
   	 * @type ArrayList
   	 */
    List<String> ring_abilities = new ArrayList<String>();
    
    /**
   	 * create Abilities array list for belt item 
   	 * 
   	 * @type ArrayList
   	 */
    List<String> belt_abilities = new ArrayList<String>();
    
    /**
   	 * create Abilities array list for boots item 
   	 * 
   	 * @type ArrayList
   	 */
    List<String> boots_abilities = new ArrayList<String>();
    
    /**
   	 * create Abilities array list for weapon item 
   	 * 
   	 * @type ArrayList
   	 */
   List<String> weapon_abilities = new ArrayList<String>();
    
    
   /**
    * What this method does is that it stores the items and values like abilities scores etc that can be used as a reference inside
    * ItemWindow class for selecting the items and the corresponding value that is supposable to be increased by the user.
    * 
	* @return the hashmap of items as key and their corresponding abilities as values 
	*/
   public HashMap<String, List<String>> getItemMap() {
	   
	
	helmet_abilities.add(Game_constants.INTELLIGENCE);
	helmet_abilities.add(Game_constants.WISDOM);
	helmet_abilities.add(Game_constants.ARMOR_CLASS);
	
	armor_abilities.add(Game_constants.ARMOR_CLASS);
	
	shield_abilities.add(Game_constants.ARMOR_CLASS);
	
	ring_abilities.add(Game_constants.ARMOR_CLASS);
	ring_abilities.add(Game_constants.STRENGTH);
	ring_abilities.add(Game_constants.CONSTITUTION);
	ring_abilities.add(Game_constants.WISDOM);
	ring_abilities.add(Game_constants.CHARISMA);
	
	belt_abilities.add(Game_constants.CONSTITUTION);
	belt_abilities.add(Game_constants.STRENGTH);
	
	boots_abilities.add(Game_constants.ARMOR_CLASS);
	boots_abilities.add(Game_constants.DEXTERITY);
	
	weapon_abilities.add(Game_constants.ATTACK_BONUS);
	weapon_abilities.add(Game_constants.DAMAGE_BONUS);
	
	
	item_map.put(Game_constants.HELMET, helmet_abilities);
	item_map.put(Game_constants.ARMOR, armor_abilities);
	item_map.put(Game_constants.SHIELD, shield_abilities);
	item_map.put(Game_constants.RING, ring_abilities);
	item_map.put(Game_constants.BELT, belt_abilities);
	item_map.put(Game_constants.BOOTS, boots_abilities);
	item_map.put(Game_constants.WEAPON_MELEE, weapon_abilities);
	item_map.put(Game_constants.WEAPON_RANGE, weapon_abilities);
	
	
	return item_map;
	   
   }
}
