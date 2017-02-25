package dungeons_and_dragons.model;
import java.util.ArrayList;
import java.util.Observable;

import com.google.gson.annotations.Expose;

/**
 * Campaign model class
 * @author : Tejas Sadrani
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
	 * array list of existing maps that are already defined in file
	 * 
	 * @type ArrayList
	 */
	@Expose
	private ArrayList<GameMapModel> input_map_list;
	
	/**
	 * array list of maps present in one campaign
	 * 
	 * @type ArrayList
	 */
	@Expose
	private ArrayList<GameMapModel> output_map_list;
	
	
	
	public CampaignModel(int campaign_id,String campaign_name) {
		this.campaign_id=campaign_id;
		this.campaign_name=campaign_name;
		this.input_map_list = new ArrayList<GameMapModel>();
		this.output_map_list = new ArrayList<GameMapModel>();
	}

	public CampaignModel() {
		this.input_map_list = new ArrayList<GameMapModel>();
		this.output_map_list = new ArrayList<GameMapModel>();
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

	public ArrayList<GameMapModel> getInput_map_list() {
		return input_map_list;
	}

	public void setInput_map_list(ArrayList<GameMapModel> input_map_list) {
		this.input_map_list = input_map_list;
	}

	public ArrayList<GameMapModel> getOutput_map_list() {
		return output_map_list;
	}

	public void setOutput_map_list(ArrayList<GameMapModel> output_map_list) {
		this.output_map_list = output_map_list;
		setChanged();
		notifyObservers(this);
	}
	
	public void callObservers() {
		setChanged();
		notifyObservers(this);
		
	}

	
	
}