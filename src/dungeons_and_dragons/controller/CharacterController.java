package dungeons_and_dragons.controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dungeons_and_dragons.helper.DiceHelper;
import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CharacterView;
import dungeons_and_dragons.view.ItemView;

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

	public CharacterController(CharacterModel characterModel) {

		this.model = characterModel;
		this.view = new CharacterView(characterModel);
		this.model.addObserver(view);
		this.view.setActionListener(this);
		this.view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(view.save)) {
			String character_name = this.view.charactername_textfield.getText();
			// model.setCharacter_name(character_name);
			if (character_name.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "Please enter character name");
				return;
			} else {
				model.setCharacter_name(character_name);
			}
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

				if (this.view.backPackList.getSelectedIndices().length > 0) {
					ArrayList<ItemModel> backPackList = (ArrayList<ItemModel>) this.view.backPackList
							.getSelectedValuesList();
					model.setBackPackItems(backPackList);
					model.save();
					new ManageCharacterController();
					this.view.dispose();
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Please select backpack items");
				}

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(new JFrame(), "Please enter valid level");
			}
		} else if (e.getSource().equals(view.back)) {
			this.backToCreateGame();
		} else if (e.getSource().equals(view.rolldice)) {
			this.view.save.setEnabled(true);
			LinkedList<Integer> temp = new LinkedList<Integer>();
			int sum = 0;
			int modifier = 0;
			for (int i = 0; i < 4; i++) {
				Integer[] dice = new Integer[] { 1, 2, 3, 4, 5, 6 };
				Random rand = new Random();
				temp.add(dice[rand.nextInt(dice.length)]);
				sum = sum + temp.get(i);
				// temp[i] = dice[rand.nextInt(dice.length)];
				// System.out.println("roll"+temp.get(i));
			}
			int min = temp.get(0);
			for (int i = 0; i < temp.size(); i++) {
				if (min > temp.get(i)) {
					min = temp.get(i);
				}
			}
			sum = sum - min;
			// System.out.println("min"+min);
			System.out.println("sum" + sum);
			temp.remove(min);

			if (sum == 1) {
				modifier = -5;
			} else if (sum == 2 || sum == 3) {
				modifier = -4;
			} else if (sum == 4 || sum == 5) {
				modifier = -3;
			} else if (sum == 6 || sum == 7) {
				modifier = -2;
			} else if (sum == 8 || sum == 9) {
				modifier = -1;
			} else if (sum == 10 || sum == 11) {
				modifier = 0;
			} else if (sum == 12 || sum == 13) {
				modifier = 1;
			} else if (sum == 14 || sum == 15) {
				modifier = 2;
			} else if (sum == 16 || sum == 17) {
				modifier = 3;
			} else if (sum == 18 || sum == 19) {
				modifier = 4;
			} else if (sum == 20 || sum == 21) {
				modifier = 5;
			}
			System.out.println("modifier" + modifier);
			String level = this.view.level_textfield.getText();
			try {
				int i = Integer.parseInt(level);
				int strength = (sum + modifier) * i;
				System.out.println("strength" + strength);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(new JFrame(), "Please enter level");
				this.view.save.setEnabled(false);
			}
		}
	}

	private void backToCreateGame() {
		new CreateGameController();
		view.dispose();
	}

}