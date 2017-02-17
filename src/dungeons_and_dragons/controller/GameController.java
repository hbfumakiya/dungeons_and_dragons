/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CreateGameView;
import dungeons_and_dragons.view.GameView;

/**
 * @author mihir
 *
 */
public class GameController implements ActionListener {

	/**
	 * 
	 */
	private GameView gameView;
	
	/**
	 *  constructore of game controller 
	 */
	public GameController() {
		
		gameView = new GameView();
		
		// set listener
		gameView.setActionListener(this);	
		
		// show game view 
		gameView.setVisible(true);
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		
		// check which button clicked by user 
		if(actionEvent.getSource().equals(gameView.new_game_button)) {
			
			// open new game window			
			this.showNewGameWindow();
			
		} else if(actionEvent.getSource().equals(gameView.load_game_button)) {
			
			// open load game window			
			this.showLoadGameWindow();
			
		} else if(actionEvent.getSource().equals(gameView.create_game_button)) {
			
			// open create game window			
			this.showCreateGameWindow();
			
		} else if(actionEvent.getSource().equals(gameView.exit_game_button)) {
			
			// exit game			
			this.exitGame();
		}
		
	}
	

	/**
	 * method for show new game window
	 */
	private void showNewGameWindow() {
		
		// hide game window
		// this.setVisible(false);
		
		// show new game window....
		
	}
	
	/**
	 * method for show load game window
	 */
	private void showLoadGameWindow() {
		
		// hide game window
		// this.setVisible(false);
		
		// show load game window....
		
	}
	
	/**
	 * method for show create game window
	 */
	private void showCreateGameWindow() {
		
		// show create game window
		new CreateGameController();
		
		// hide game window
		gameView.dispose();
	}
	
	/**
	 * method for show exit game
	 */
	private void exitGame() {
		
		gameView.dispose();
	}
	
	
	public static void main(String[] args) 
	{
		GameController game = new GameController();	
		/*
		try {
			FileHelper.saveItem(new ItemModel(1, "Hee", 5, "ggg","ggg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
