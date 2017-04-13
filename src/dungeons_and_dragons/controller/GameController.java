/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.view.GameView;

/**
 * The GameController translates the user's interactions with the GameView into
 * actions that the GameModel will perform that may use some additional/changed
 * data gathered in a user-interactive view.
 * 
 * @author Mihir Pujara
 */
public class GameController implements ActionListener {

	/**
	 * 
	 */
	private GameView gameView;

	/**
	 * Default constructor of game controller
	 * <p>
	 * Gameview is initialize
	 */
	public GameController() {

		gameView = new GameView();

		// set listener
		gameView.setActionListener(this);

		// show game view
		gameView.setVisible(true);

		try {
			FileHelper.getItems();
		} catch (JsonSyntaxException | IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Invalid Item File.");
		}

		try {
			FileHelper.getCharcters();
		} catch (JsonSyntaxException | IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Invalid Character File.");
		}

		try {
			FileHelper.getMaps();
		} catch (JsonSyntaxException | IOException e1) {
			JOptionPane.showMessageDialog(new JFrame(), "Invalid Map File.");
		}

		try {
			FileHelper.getCampaigns();
		} catch (JsonSyntaxException | IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Invalid Campaign File.");
		}

	}

	/**
	 * Action event of all events
	 * 
	 * @param actionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// check which button clicked by user
		if (actionEvent.getSource().equals(gameView.new_game_button)) {

			// open new game window
			this.showNewGameWindow();

		} else if (actionEvent.getSource().equals(gameView.load_game_button)) {

			// open load game window
			this.showLoadGameWindow();

		} else if (actionEvent.getSource().equals(gameView.create_game_button)) {

			// open create game window
			this.showCreateGameWindow();

		} else if (actionEvent.getSource().equals(gameView.exit_game_button)) {

			// exit game
			this.exitGame();
		}

	}

	/**
	 * method for show new game window
	 */
	private void showNewGameWindow() {

		// show new game window
		new NewGameController();

		// hide game window
		this.gameView.dispose();

	}

	/**
	 * method for show load game window
	 */
	private void showLoadGameWindow() {

		JFileChooser c = new JFileChooser();
		c.setFileFilter(new FileNameExtensionFilter("JSON File","json"));
		// Demonstrate "Open" dialog:
		int rVal = c.showOpenDialog(this.gameView);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			String file = c.getSelectedFile().toString();
			
			if(!file.equals("")) {
				try {
					GamePlayModel game = FileHelper.loadGame(file);
					this.gameView.dispose();
					new GamePlayController(game, true);
				} catch (JsonSyntaxException | IOException e) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid file..!");
				}
			}
			
		}
	}

	/**
	 * method for show create game window
	 */
	private void showCreateGameWindow() {

		// show create game window
		new CreateGameController();

		// hide game window
		this.gameView.dispose();
	}

	/**
	 * method for show exit game
	 */
	private void exitGame() {

		this.gameView.dispose();
	}

	public static void main(String[] args) {
		GameController game = new GameController();
	}
}
