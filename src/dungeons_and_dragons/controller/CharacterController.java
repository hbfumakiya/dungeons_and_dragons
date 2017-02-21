package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;
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
			String level = this.view.level_textfield.getText();
			try {
				int i = Integer.parseInt(level);
				model.setCharacter_level(i);
				ItemModel armer = (ItemModel) this.view.armer_combobox.getSelectedItem();
				ItemModel belt = (ItemModel) this.view.belt_combobox.getSelectedItem();
				ItemModel boot = (ItemModel) this.view.boot_combobox.getSelectedItem();
				ItemModel helmet = (ItemModel) this.view.helmet_combobox.getSelectedItem();
				ItemModel ring = (ItemModel) this.view.ring_combobox.getSelectedItem();
				ItemModel weapon = (ItemModel) this.view.weapon_combobox.getSelectedItem();
				ItemModel shield = (ItemModel) this.view.shield_combobox.getSelectedItem();

				ArrayList<ItemModel> items = new ArrayList<ItemModel>();
				items.add(armer);
				items.add(belt);
				items.add(boot);
				items.add(helmet);
				items.add(ring);
				items.add(weapon);
				items.add(shield);
				model.setItems(items);
				model.save();
				new ManageCharacterController();
				this.view.dispose();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(new JFrame(), "Please enter integer value in level");
			}
		} else if (e.getSource().equals(view.back)) {
			this.backToCreateGame();
		}
	}

	private void backToCreateGame() {

		new CreateGameController();
		view.dispose();
	}

}