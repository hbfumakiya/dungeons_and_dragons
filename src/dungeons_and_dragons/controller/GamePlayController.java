package dungeons_and_dragons.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.view.GamePlayView;

/**
 * The GamePlayController translates the user's interactions with the
 * GamePlayView into actions that the GamePlayModel will perform that may use
 * some additional/changed data gathered in a user-interactive view.
 * 
 * @author Tejas Sadrani & Urmil Kansara
 */
public class GamePlayController implements KeyListener {

	
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
		
		//GameMapModel currentMap = this.gamePlayModel.getCampaignModel().getOutput_map_list().get(this.gamePlayModel.getCurrentMapIndex());
		
		this.gamePlayView = new GamePlayView(this.gamePlayModel);
	
		//this.gamePlayModel.addObserver(gamePlayView);
		
		this.gamePlayView.setVisible(true);
		this.gamePlayView.setListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	        System.out.println("left");
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	    	System.out.println("right");
	    }

	    if (key == KeyEvent.VK_UP) {
	    	System.out.println("up");
	    }

	    if (key == KeyEvent.VK_DOWN) {
	    	System.out.println("down");
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
