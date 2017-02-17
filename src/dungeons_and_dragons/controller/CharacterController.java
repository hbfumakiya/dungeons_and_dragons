package dungeons_and_dragons.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.ItemView;
/**
 * This class call character controller 
 * 
 * @author Hirangi Naik
 *
 */
import dungeons_and_dragons.view.ManageCharacterView;


public class CharacterController implements ActionListener{
	
	CharacterModel model;
	ManageCharacterView view;
	
	public CharacterController() {
		this.model=new CharacterModel();
		this.view=new ManageCharacterView();
		
		this.model.addObserver(view);
		this.view.setListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
