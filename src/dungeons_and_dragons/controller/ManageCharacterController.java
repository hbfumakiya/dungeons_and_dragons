/**
 * 
 */
package dungeons_and_dragons.controller;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.view.ManageCharacterView;

/**
 * @author mihir
 *
 */
public class ManageCharacterController {

	
	private CharacterModel characterModel;
	
	private ManageCharacterView manageCharacterView;
	
	/**
	 * 
	 */
	public ManageCharacterController() {
		
		characterModel = new CharacterModel();
		
		manageCharacterView = new ManageCharacterView();
		
		manageCharacterView.setVisible(true);
	}

}
