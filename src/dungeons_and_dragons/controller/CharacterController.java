package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.view.CharacterView;

/**
 * This class call character controller
 * 
 * @author Hirangi Naik
 *
 */
public class CharacterController implements ActionListener {

	private CharacterModel model;
	private CharacterView view;

	public CharacterController() {
		this.model = new CharacterModel();
		this.view = new CharacterView();

		this.model.addObserver(view);
		this.view.setActionListener(this);
		this.view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(view.save)) {
			String character_name = this.view.charactername_textfield.getText();
			model.setCharacter_name(character_name);
			// ArrayList<ItemModel> items = (ArrayList<ItemModel>)
			// this.view.item_combobox.getSelectedItem();
			// model.setItems(items);
			model.save();
		} else if (e.getSource().equals(view.back)) {
			this.backToCreateGame();
		}
	}

	private void backToCreateGame() {

		new CreateGameController();
		view.dispose();
	}

}
