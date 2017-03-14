/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

				// show game play window
				new GamePlayController();

				// hide game window
				gameView.dispose();

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

	public static void main(String[] args) {
		GameController game = new GameController();
	}
}
