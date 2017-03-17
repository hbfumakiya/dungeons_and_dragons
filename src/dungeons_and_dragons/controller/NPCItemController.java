/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.NPCItemView;

/**
 * @author Hirangi Naik
 *
 */
public class NPCItemController implements ActionListener {

	private NPCItemView view;
	public ArrayList<ItemModel> items;
	public boolean isEnemy;
	private GamePlayModel model;

	private CharacterModel character;

	/**
	 * 
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(view.okButton)) {
			if (isEnemy) {
				List<ItemModel> items = this.view.itemList.getSelectedValuesList();
				if ((items == null) || (items.size() < 1))
					return;
				// this.controller.gamePlayModel.getCharacterModel().getBackPackItems().addAll(items);
				
				
				if (this.model.getCharacterModel().getBackPackItems().size() < 10) {
					for (int i = 0; this.model.getCharacterModel().getBackPackItems().size() < 10 && i<items.size(); i++) {
						this.model.getCharacterModel().getBackPackItems().add(items.get(i));
						this.items.remove(items.get(i));
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Sorry your backpack is full.So cannot add any new Item");
				}
				this.view.dispose();
			} else {
				
				List<ItemModel> items = this.view.itemList.getSelectedValuesList();
				if ((items == null) || (items.size() < 1))
					return;
				
				
				
			}
		} 
	}
}
