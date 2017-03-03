/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.exception.NotFoundException;
import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CharacterInventoryView;

/**
 * This class is to show all created characters, add new character,
 * edit and view character
 * @author mihir
 *
 */
public class CharacterInventoryController implements ActionListener {

	private CharacterModel character;

	private CharacterInventoryView characterInventoryView;

	/**
	 * Parameterized constructor for inventory
	 * @param character
	 */
	public CharacterInventoryController(CharacterModel character) {

		this.character = character;

		characterInventoryView = new CharacterInventoryView(character);

		characterInventoryView.setActionListener(this);

		characterInventoryView.setVisible(true);
	}

	/**
	 * Action event of all events
	 * @param actionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if (actionEvent.getSource().equals(characterInventoryView.moveFromItemToBack)) {

			List<ItemModel> items = this.characterInventoryView.itemList.getSelectedValuesList();
			if ((items == null) || (items.size() < 1))
				return;

			this.character.getItems().removeAll(items);
			while (this.character.getItems().remove(null)) {
			}
			this.character.getBackPackItems().addAll(items);
			this.characterInventoryView.updateList(this.character);

		} else if (actionEvent.getSource().equals(characterInventoryView.moveFromBackToItem)) {

			ItemModel item = this.characterInventoryView.backPackList.getSelectedValue();
			if (item == null)
				return;

			if ((this.character.getItems() == null) || (this.character.getItems().size() < 1)) {
				ArrayList<ItemModel> newItem = new ArrayList<ItemModel>();
				newItem.add(item);
				this.character.getBackPackItems().remove(item);
				this.character.setItems(newItem);
				this.characterInventoryView.updateList(this.character);
			} else {

				if (this.character.getItems().stream().anyMatch(p -> p.getItem_type().equals(item.getItem_type()))) {
					JOptionPane.showMessageDialog(this.characterInventoryView,
							"This item type(" + item.getItem_type() + ") is already in Item List", "Error",
							JOptionPane.ERROR_MESSAGE);

				} else {
					this.character.getItems().add(item);
					this.character.getBackPackItems().remove(item);
					this.characterInventoryView.updateList(this.character);
				}
			}

		} else if (actionEvent.getSource().equals(characterInventoryView.okButton)) {
			
			
			try {
				this.character.calculateModifires();
				this.character.calculateHitPoints(this.character.getCharacter_level());
				this.character.calculateArmorClass();
				this.character.calculateAttackBonus(this.character.getCharacter_level());
				this.character.calculateDamageBonus();
				
				FileHelper.updateCharacter(this.character);
				
				this.characterInventoryView.dispose();
			} catch (JsonSyntaxException | IOException | NotFoundException e) {
				LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
			}
						
		}
	}
}
