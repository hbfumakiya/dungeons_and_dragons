/**
 * 
 */
package dungeons_and_dragons.controller;

import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.view.NewGameView;

/**
 * @author Mihir Pujara
 *
 */
public class NewGameController {

	private GamePlayModel gamePlayModel;

	private NewGameView newGameView;

	/**
	 * 
	 */
	public NewGameController() {

		this.gamePlayModel = new GamePlayModel();

		this.newGameView = new NewGameView();
		
		this.newGameView.setVisible(true);
	}

}
