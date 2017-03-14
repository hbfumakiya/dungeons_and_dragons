package dungeons_and_dragons.controller;

import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.view.GamePlayView;

/**
 * The GamePlayController translates the user's interactions with the
 * GamePlayView into actions that the GamePlayModel will perform that may use
 * some additional/changed data gathered in a user-interactive view.
 * 
 * @author Tejas Sadrani & Urmil Kansara
 */
public class GamePlayController {

	
	/**
	 * This creates new game play model which is being observed
	 * 
	 * @type GameMapModel
	 */
	GamePlayModel gamePlayModel;
	
	/**
	 * This create game play view object which is an observer
	 * 
	 * @type MapGridView
	 */
	GamePlayView gamePlayView;

	/**
	 * Default constructor of Map Grid controller
	 * <p>
	 * MapGrid model and view are initialized Also view is binded to observer.
	 * <p>
	 * All the events of view are registered in constructor
	 */
	public GamePlayController(GamePlayModel gamePlayModel) {
		
		this.gamePlayModel = gamePlayModel;
		
		this.gamePlayView = new GamePlayView(this.gamePlayModel);

		this.gamePlayModel.addObserver(gamePlayView);
		this.gamePlayView.setListener(this);
	}
	
	
}
