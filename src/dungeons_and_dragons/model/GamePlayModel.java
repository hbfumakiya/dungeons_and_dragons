package dungeons_and_dragons.model;

import java.awt.Point;
import java.io.IOException;
import java.util.Observable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapItem;

/**
 * Once GamePlayModel gets a change state query request from any view they
 * respond to instructions to change the state from GamePlayController
 * 
 * @author Tejas Sadrani & Urmil Kansara
 * 
 */
public class GamePlayModel extends Observable{

	/**
	 * Sets id to the game play instance
	 */
	@Expose
	private int gamePlayId;
	
	/**
	 * Sets the location of the player that is playing
	 */
	@Expose
	private Point gameCharacterPosition;
	
	/**
	 * Creates an object of campaign
	 */
	@Expose
	private CampaignModel campaignModel;

	/**
	 * Creates an object of character
	 */
	@Expose
	private CharacterModel characterModel;
	
	@Expose
	private int currentMapIndex;
	
	/**
	 * constructor to initialize map object
	 */
	public GamePlayModel(){
		
		this.currentMapIndex = 0;
		
		/*
		 * Hard-coded values need to be embedded with Hirangi's code
		 */
		try {
			//this.campaignModel = new CampaignModel().getData().get(0);
			//this.characterModel = new CharacterModel().getData().get(0);
		} catch (JsonSyntaxException e) {
			//need to be changed
			e.printStackTrace();
		}
		this.gamePlayId = 0;
	}

	/**
	 * @return the gamePlayId
	 */
	public int getGamePlayId() {
		return gamePlayId;
	}

	/**
	 * @param gamePlayId the gamePlayId to set
	 */
	public void setGamePlayId(int gamePlayId) {
		this.gamePlayId = gamePlayId;
	}

	/**
	 * @return the campaignModel
	 */
	public CampaignModel getCampaignModel() {
		return campaignModel;
	}

	/**
	 * @param campaignModel the campaignModel to set
	 */
	public void setCampaignModel(CampaignModel campaignModel) {
		this.campaignModel = campaignModel;
	}

	/**
	 * @return the characterModel
	 */
	public CharacterModel getCharacterModel() {
		return characterModel;
	}

	/**
	 * @param characterModel the characterModel to set
	 */
	public void setCharacterModel(CharacterModel characterModel) {
		this.characterModel = characterModel;
	}

	/**
	 * to save it to file
	 * @param path location
	 */
	public void save(String path) {
		
		try {
			FileHelper.saveGame(path, this);
		} catch (IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
		
	}
	/**
	 * returns character position
	 * 
	 * @return point of current position of character
	 */
	public Point getGameCharacterPosition() {
		return gameCharacterPosition;
	}
	
	/**
	 * set the current position of player
	 * @param gameCharacterPosition current point of player
	 */
	public void setGameCharacterPosition(Point gameCharacterPosition) {
		this.gameCharacterPosition = gameCharacterPosition;
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * @return the currentMapIndex
	 */
	public int getCurrentMapIndex() {
		return currentMapIndex;
	}

	/**
	 * @param currentMapIndex the currentMapIndex to set
	 */
	public void setCurrentMapIndex(int currentMapIndex) {
		this.currentMapIndex = currentMapIndex;
	}

		/**
		 * Method to remove Point in the map
		 * @param tempPoint
		 */
	public void removeChest(Point tempPoint) {
		int i = this.getCurrentMapIndex();
		this.getCampaignModel().getOutput_map_list().get(i).setMap_chest(new MapItem());
		setChanged();
		notifyObservers();
		
		
	}

	
}
