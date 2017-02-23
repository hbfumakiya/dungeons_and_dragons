package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dungeons_and_dragons.helper.DiceHelper;
import dungeons_and_dragons.model.AbilityScoresModel;
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

			this.performCalculation(1);

			/*
			 * this.view.save.setEnabled(true);
			 * 
			 * String level = this.view.level_textfield.getText(); try { int i =
			 * Integer.parseInt(level); // int strength = (sum + modifier) * i;
			 * //System.out.println("strength" + strength); } catch
			 * (NumberFormatException ex) { JOptionPane.showMessageDialog(new
			 * JFrame(), "Please enter level");
			 * this.view.save.setEnabled(false); }
			 */
			System.out.println("ok");
		}
	}

	public void performCalculation(int level) {

		AbilityScoresModel ability = this.model.getAbilityScores();
		AbilityScoresModel modifiers = this.model.getModifiers();

		int strength = this.model.calculate4D6();
		int dexterity = this.model.calculate4D6();
		int constitution = this.model.calculate4D6();
		int intelligence = this.model.calculate4D6();
		int wisdom = this.model.calculate4D6();
		int charisma = this.model.calculate4D6();

		// calculate ability modifiers

		modifiers.setstrength(getModifiersFromScore(strength));
		modifiers.setDexterity(getModifiersFromScore(dexterity));
		modifiers.setConstitution(getModifiersFromScore(constitution));
		modifiers.setIntelligence(getModifiersFromScore(intelligence));
		modifiers.setWisdom(getModifiersFromScore(wisdom));
		modifiers.setCharisma(getModifiersFromScore(charisma));

		// calculate ability scores

		ability.setstrength((strength + modifiers.getStraight()) * level);
		ability.setDexterity((dexterity + modifiers.getDexterity()) * level);
		ability.setConstitution((constitution + modifiers.getConstitution()) * level);
		ability.setIntelligence((intelligence + modifiers.getIntelligence()) * level);
		ability.setWisdom((wisdom + modifiers.getWisdom()) * level);
		ability.setCharisma((charisma + modifiers.getCharisma()) * level);
		
		this.model.setAbilityScores(ability);
		this.model.setModifiers(modifiers);
	}

	private int getModifiersFromScore(int score) {
		int modifier = -5;
		if (score == 1) {
			modifier = -5;
		} else if (score == 2 || score == 3) {
			modifier = -4;
		} else if (score == 4 || score == 5) {
			modifier = -3;
		} else if (score == 6 || score == 7) {
			modifier = -2;
		} else if (score == 8 || score == 9) {
			modifier = -1;
		} else if (score == 10 || score == 11) {
			modifier = 0;
		} else if (score == 12 || score == 13) {
			modifier = 1;
		} else if (score == 14 || score == 15) {
			modifier = 2;
		} else if (score == 16 || score == 17) {
			modifier = 3;
		} else if (score == 18 || score == 19) {
			modifier = 4;
		} else if (score == 20 || score == 21) {
			modifier = 5;
		}
		return modifier;
	}

	private void backToCreateGame() {
		new CreateGameController();
		view.dispose();
	}

}