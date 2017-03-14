package dungeons_and_dragons.model;

import java.io.IOException;
import java.util.Observable;

import com.google.gson.JsonSyntaxException;

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
	private int gamePlayId;
	
	/**
	 * Creates an object of campaign
	 */
	private CampaignModel campaignModel;
	
	/**
	 * Creates an object of character
	 */
	private CharacterModel characterModel;
	
	/**
	 * constructor to initialize map object
	 */
	public GamePlayModel(){
		
		/*
		 * Hard-coded values need to be embedded with Hirangi's code
		 */
		try {
			this.campaignModel = new CampaignModel().getData().get(0);
			this.characterModel = new CharacterModel().getData().get(0);
		} catch (JsonSyntaxException | IOException e) {
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
	
	
}
