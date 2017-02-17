package dungeons_and_dragons.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CreateGameView;
import dungeons_and_dragons.view.ItemView;
/**
 * This class call character controller 
 * 
 * @author Hirangi Naik
 *
 */
import dungeons_and_dragons.view.ManageCharacterView;


public class CharacterController implements ActionListener{

	private CharacterModel model;
	private ManageCharacterView view;
	
	public CharacterController() {
		this.model=new CharacterModel();
		this.view=new ManageCharacterView();
		
		this.model.addObserver(view);
		this.view.setActionListener(this);
		this.view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(view.save))
		{
			String character_name = this.view.charactername_textfield.getText();
			model.setCharacter_name(character_name);
			
			
			model.save();
		}
		else if(e.getSource().equals(view.back))
		{
			this.backToCreateGame();
		}
	}

	private void backToCreateGame() {
		
		new CreateGameController();
		view.dispose();
	}

}
