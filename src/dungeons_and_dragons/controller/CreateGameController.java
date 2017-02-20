/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dungeons_and_dragons.view.CreateGameView;
import dungeons_and_dragons.view.GameView;
import dungeons_and_dragons.view.ItemView;
import dungeons_and_dragons.view.ManageCharacterView;
import sun.security.x509.CRLReasonCodeExtension;

/**
 * @author mihir
 *
 */
public class CreateGameController implements ActionListener {


	private CreateGameView createGameView;

	public CreateGameController() {
		
		createGameView = new CreateGameView();
		
		createGameView.setActionListener(this);
		
		createGameView.setVisible(true);
	}
	
	/** 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		
		// check which button clicked by user
		if(actionEvent.getSource().equals(createGameView.manage_character_button)) {
		
			// open manage character window
			this.showManageCharacterWindow();
			
		} else if(actionEvent.getSource().equals(createGameView.manage_map_button)) {

			// open manage map window			
			this.showManageMapWindow();
			
		} else if(actionEvent.getSource().equals(createGameView.manage_campaign_button)) {
			
			// open manage campaign window			
			this.showManageCampaignWindow();
			
		} else if(actionEvent.getSource().equals(createGameView.manage_item_button)) {

			// open manage item window			
			this.showManageItemWindow();
			
		} else if(actionEvent.getSource().equals(createGameView.back_button)) { 

			// back to game and open main game window			
			this.backToGame();
		}
	}
	
	
	/**
	 * method for show manage character window
	 */
	private void showManageCharacterWindow() {
		
		// show manage character view
		
		new CharacterController();
		
		// hide window
		createGameView.dispose();
	}
	
	/**
	 * method for show manage map window
	 */
	private void showManageMapWindow() {
		
		// hide game window
		// createGameView.dispose();
		new MapGridController();
		// show load game window....
		
		createGameView.dispose();
	}
	
	/**
	 * method for show manage item window
	 */
	private void showManageItemWindow() {
		
		// show Item game window....
		new ManageItemController();
		
		createGameView.dispose();
	}
	
	/**
	 * method for show create game window
	 */
	private void showManageCampaignWindow() {
		
		// hide game window
		// createGameView.dispose();
		
		// show create game window....
		
	}
		
	/**
	 * method for show exit game
	 */
	private void backToGame() {
		
		//open game window
		new GameController();
		
		createGameView.dispose();
	}
}
