package dungeons_and_dragons.model;
import java.util.LinkedList;
import java.util.Observable;

/*
 * class for campaign
 * @author : Hirangi Naik
 * 
 */

public class CampaignModel extends Observable {
	
	/**
	 * Variable for identity of campaign. Value of all these campaigns must be unique.
	 * 
	 * @type integer
	 */
	private int campaign_id;
	
	/**
	 * Variable for campaign name.
	 * 
	 * @type string
	 */
	private String campaign_name;
	
	/**
	 * linked list for traversing through maps
	 * 
	 * @type LinkedList
	 */
	private LinkedList<GameMapModel> map_list;
	
	public CampaignModel(int campaign_id,String campaign_name) {
		this.campaign_id=campaign_id;
		this.campaign_name=campaign_name;
		
	}

	public CampaignModel() {
		// TODO Auto-generated constructor stub
	}

}