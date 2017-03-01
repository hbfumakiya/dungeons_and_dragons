/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.GameButton;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CharacterInventoryView;
import dungeons_and_dragons.view.ManageCharacterView;

/**
 * @author mihir
 *
 */
public class ManageCharacterController implements ActionListener {

	private CharacterModel characterModel;

	private ManageCharacterView manageCharacterView;

	/**
	 * Default constructor for manage character 
	 * initialize model and view
	 */
	public ManageCharacterController() {

		try {

			characterModel = new CharacterModel();

			ArrayList<CharacterModel> characters = characterModel.getData();

			manageCharacterView = new ManageCharacterView(this, characters);

			manageCharacterView.setVisible(true);

			manageCharacterView.setActionListener(this);

		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

	}

	/**
	 * Action events for all the events
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		if (actionEvent.getSource().equals(manageCharacterView.addCharacterButton)) {
			new CharacterController();
			
			manageCharacterView.dispose();
			
		} else if (actionEvent.getSource().equals(manageCharacterView.backButton)) {
			
			new CreateGameController();
			
			manageCharacterView.dispose();

		} else if (((GameButton) actionEvent.getSource()).getButtonType() == GameButton.BUTTON_TYPE_VIEW) {

			GameButton button = (GameButton) actionEvent.getSource();
			CharacterModel character = (CharacterModel) button.getSource();
			
			if(character != null) {
				new CharacterInventoryController(character);
			}
		}
		else if (((GameButton) actionEvent.getSource()).getButtonType() == GameButton.BUTTON_TYPE_EDIT) {
			GameButton button = (GameButton) actionEvent.getSource();
			int characterId = button.getId();	
			CharacterModel character = (CharacterModel) button.getSource();		    
			
			new CharacterController(character);
			manageCharacterView.dispose();
		}
	}

}
