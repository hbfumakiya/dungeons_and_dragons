/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.NPCItemView;

/**
 * @author Hirangi Naik & Mihir Pujara
 *
 */
public class NPCItemController implements ActionListener {

	private NPCItemView view;
	public ArrayList<ItemModel> items;
	public boolean isEnemy;
	private GamePlayModel model;
	public ItemModel selectedItem;
	private CharacterModel character;

	/**
	 * parameterized constructor
	 * @param gamePlayModel
	 * @param items
	 * @param isEnemy
	 * @param character
	 */
	public NPCItemController(GamePlayModel gamePlayModel, ArrayList<ItemModel> items, boolean isEnemy,
			CharacterModel character) {

		this.model = gamePlayModel;
		this.isEnemy = isEnemy;
		this.items = items;

		this.character = character;
		this.view = new NPCItemView(this.items);
		view.setActionListener(this);
		this.view.setVisible(true);
	}
	
	/**
	 * Action event of all the events
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(view.okButton)) {
			if (isEnemy) {
				List<ItemModel> items = this.view.itemList.getSelectedValuesList();
				selectedItem = items.get(0);
				if ((items == null) || (items.size() < 1))
					return;
				// this.controller.gamePlayModel.getCharacterModel().getBackPackItems().addAll(items);

				if (this.model.getCharacterModel().getBackPackItems().size() < 10) {
					for (int i = 0; this.model.getCharacterModel().getBackPackItems().size() < 10
							&& i < items.size(); i++) {
						this.model.getCharacterModel().getBackPackItems().add(items.get(i));
						this.items.remove(items.get(i));
						if(this.character.getBackPackItems().contains(items.get(i)))
						{
							this.character.getBackPackItems().remove(items.get(i));
						}
						else if(this.character.getItems().contains(items.get(i)))
						{
							this.character.getItems().remove(items.get(i));
						}
					}

					this.character.update();
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Sorry your backpack is full.So cannot add any new Item");
				}

			} else {

				List<ItemModel> items = this.view.itemList.getSelectedValuesList();
				if ((items == null) || (items.size() < 1))
					return;

				if (this.character.getBackPackItems().size() < 10) {

					Collections.shuffle(this.character.getBackPackItems());

					ItemModel item = this.character.getBackPackItems().get(0);

					this.character.getBackPackItems().remove(item);

					this.character.getBackPackItems().add(items.get(0));

					this.items.remove(items.get(0));

					this.items.add(item);

					JOptionPane.showMessageDialog(new JFrame(), "You received this " + item.getItem_name() + "("
							+ item.getItem_type() + ") item from friendly player which is added into your backpack");
					this.character.update();

				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Sorry " + this.character.getCharacter_name()
							+ "'s (Friendly Player) backpack is full.So cannot exchange any Item");
				}

				this.character.updateView();
			}

			this.view.dispose();
		}
	}
}
