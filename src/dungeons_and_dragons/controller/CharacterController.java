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
	
	private ManageCharacterView managecharacterview;
	CharacterModel model;
	ManageCharacterView view;
	
	public CharacterController() {
		this.model=new CharacterModel();
		this.view=new ManageCharacterView();
		
		this.model.addObserver(view);
		this.view.setActionListener(this);
		this.view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("save clicked fgf");
		if(e.getSource().equals(view.save))
		{
			System.out.println("save clicked");
			
		}
		else if(e.getSource().equals(view.back))
		{
			this.backToCreateGame();
		}
	}

	private void backToCreateGame() {
		
		new CreateGameController();
		managecharacterview.dispose();
	}

}
