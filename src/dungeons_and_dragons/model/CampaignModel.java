package dungeons_and_dragons.model;
import java.util.ArrayList;
import java.util.Observable;

import com.google.gson.annotations.Expose;

/**
 * class for campaign
 * @author : Hirangi Naik and Tejas Sadrani
 * 
 */

public class CampaignModel extends Observable {
	
	/**
	 * Variable for identity of campaign. Value of all these campaigns must be unique.
	 * 
	 * @type integer
	 */
	@Expose
	private int campaign_id;
	
	/**
	 * Variable for campaign name.
	 * 
	 * @type string
	 */
	@Expose
	private String campaign_name;
	
	/**
	 * linked list for traversing through maps
	 * 
	 * @type ArrayList
	 */
	@Expose
	private ArrayList<GameMapModel> map_list;
	
	public CampaignModel(int campaign_id,String campaign_name) {
		this.campaign_id=campaign_id;
		this.campaign_name=campaign_name;
		this.map_list = new ArrayList<GameMapModel>();
	}

	public CampaignModel() {
		this.map_list = new ArrayList<GameMapModel>();
	}

	public int getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(int campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public ArrayList<GameMapModel> getMap_list() {
		return map_list;
	}

	public void setMap_list(ArrayList<GameMapModel> map_list) {
		this.map_list = map_list;
		setChanged();
		notifyObservers(this);
	}
	
	public void callObservers() {
		setChanged();
		notifyObservers(this);
		
	}

	
	
}