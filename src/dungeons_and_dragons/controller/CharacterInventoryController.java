/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;
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

		characterInventoryView.setActionListener(this);

		characterInventoryView.setVisible(true);
	}

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
			
		}
	}

}
