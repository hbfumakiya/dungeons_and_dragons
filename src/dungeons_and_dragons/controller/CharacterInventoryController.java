/**
 * 
 */
package dungeons_and_dragons.controller;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.view.CharacterInventoryView;

/**
 * @author mihir
 *
 */
public class CharacterInventoryController {

	
	private CharacterInventoryView characterInventoryView;
	/**
	 * 
	 */
	public CharacterInventoryController(CharacterModel character) {
		
		characterInventoryView = new CharacterInventoryView(character);
	}

}
