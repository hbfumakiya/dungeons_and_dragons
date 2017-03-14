/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.view.NewGameView;

/**
 * @author Mihir Pujara
 *
 */
public class NewGameController implements ActionListener {

	private GamePlayModel gamePlayModel;

	private NewGameView newGameView;

	/**
	 * 
	 */
	public NewGameController() {

		this.gamePlayModel = new GamePlayModel();

		this.newGameView = new NewGameView();
		
		this.newGameView.setActionListener(this);
		
		this.newGameView.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(this.newGameView.backButton)) {
			
			this.backToGame();
			
		} else if(e.getSource().equals(this.newGameView.playButton)) {
			
			
		}
	}
	
	
	/**
	 * method for show exit game
	 */
	private void backToGame() {

		// open game window
		new GameController();

		this.newGameView.dispose();
	}

}
