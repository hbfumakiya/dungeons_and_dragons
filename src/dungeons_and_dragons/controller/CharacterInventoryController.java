/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.view.CharacterInventoryView;

/**
 * @author mihir
 *
 */
public class CharacterInventoryController implements ActionListener {

	
	private CharacterModel character;
	
	private CharacterInventoryView characterInventoryView;
	/**
	 * 
	 */
	public CharacterInventoryController(CharacterModel character) {
		
		this.character = character;
		
		characterInventoryView = new CharacterInventoryView(character);
		
		characterInventoryView.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if(actionEvent.getSource().equals(characterInventoryView.moveFromItemToBack)) {
			
		} else if(actionEvent.getSource().equals(characterInventoryView.moveFromBackToItem)) {
			
		}
	}

}
