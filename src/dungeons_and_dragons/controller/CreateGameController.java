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
 * The CreateGameController translates the user's interactions with the
 * CreateGameView into actions that the CreateGameModel will perform that may use
 * some additional/changed data gathered in a user-interactive view.
 * 
 * @author Mihir pujara
 */
public class CreateGameController implements ActionListener {

	private CreateGameView createGameView;

	/**
	 * Default constructor for game controller
	 * initialize game view
	 */
	public CreateGameController() {

		createGameView = new CreateGameView();

		createGameView.setActionListener(this);

		createGameView.setVisible(true);
	}

	/**
	 * All action events
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		// check which button clicked by user
		if (actionEvent.getSource().equals(createGameView.manage_character_button)) {

			// open manage character window
			this.showManageCharacterWindow();

		} else if (actionEvent.getSource().equals(createGameView.manage_map_button)) {

			// open manage map window
			this.showManageMapWindow();

		} else if (actionEvent.getSource().equals(createGameView.manage_campaign_button)) {

			// open manage campaign window
			this.showManageCampaignWindow();

		} else if (actionEvent.getSource().equals(createGameView.manage_item_button)) {

			// open manage item window
			this.showManageItemWindow();

		} else if (actionEvent.getSource().equals(createGameView.back_button)) {

			// back to game and open main game window
			this.backToGame();
		}
	}

	/**
	 * method for show manage character window
	 */
	private void showManageCharacterWindow() {

		// show manage character view

		new ManageCharacterController();

		// hide window
		createGameView.dispose();
	}

	/**
	 * method for show manage map window
	 */
	private void showManageMapWindow() {

		// hide game window
		// createGameView.dispose();
		new ManageMapController();
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

		new ManageCampaignController();

		createGameView.dispose();
	}

	/**
	 * method for show exit game
	 */
	private void backToGame() {

		// open game window
		new GameController();

		createGameView.dispose();
	}
}
