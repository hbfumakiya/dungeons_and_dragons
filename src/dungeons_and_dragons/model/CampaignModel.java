package dungeons_and_dragons.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.LogHelper;

/**
 * Once CampaignModel gets a change state query request from any view they
 * respond to instructions to change the state from CampaignController
 * 
 * @author Tejas Sadrani
 * 
 */
public class CampaignModel extends Observable implements Model<CampaignModel> {

	/**
	 * Variable for identity of campaign. Value of all these campaigns must be
	 * unique.
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
	 * Array list of existing maps that are already defined in file
	 * 
	 * @type ArrayList
	 */
	@Expose
	private ArrayList<GameMapModel> input_map_list;

	/**
	 * Array list of maps present in one particular campaign
	 * 
	 * @type ArrayList
	 */
	@Expose
	private ArrayList<GameMapModel> output_map_list;

	/**
	 * Parameterized constructor to set id, name and campaign map lists
	 * 
	 * @param campaign_id
	 * @param campaign_name
	 */
	public CampaignModel(int campaign_id, String campaign_name) {
		this.campaign_id = campaign_id;
		this.campaign_name = campaign_name;
		this.input_map_list = new ArrayList<GameMapModel>();
		this.output_map_list = new ArrayList<GameMapModel>();
	}

	/**
	 * Default constructor used to set the campaign map lists
	 */
	public CampaignModel() {
		this.input_map_list = new ArrayList<GameMapModel>();
		this.output_map_list = new ArrayList<GameMapModel>();
	}

	/**
	 * Method used to notify state change whenever any change is reflected by
	 * CampaignController via CampaignView
	 */
	public void callObservers() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Method used to save campaign
	 */
	@Override
	public void save() {
		try {
			this.setCurrentId();
			FileHelper.saveCampaign(this);
		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

	}

	/**
	 * Method used to get the stored ArrayList consisting of campaigns
	 * 
	 * @return ArrayList
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	@Override
	public ArrayList<CampaignModel> getData() throws JsonSyntaxException, IOException {
		return FileHelper.getCampaigns();
	}

	/**
	 * method used to update a campaign
	 */
	@Override
	public void update() {
		try {
			FileHelper.updateCampaign(this);
		} catch (Exception e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

	}

	/**
	 * Method used to set the campaign_id for every insert operation
	 * 
	 * @throws JsonSyntaxException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void setCurrentId() throws JsonSyntaxException, IOException {

		ArrayList<CampaignModel> alldata = this.getData();
		if (alldata != null) {
			this.campaign_id = alldata.get(alldata.size() - 1).getCampaign_id() + 1;
		} else {
			this.campaign_id = 1;
		}
	}

	/**
	 * @return the campaign_id
	 */
	public int getCampaign_id() {
		return campaign_id;
	}

	/**
	 * @param campaign_id the campaign_id to set
	 */
	public void setCampaign_id(int campaign_id) {
		this.campaign_id = campaign_id;
	}

	/**
	 * @return the campaign_name
	 */
	public String getCampaign_name() {
		return campaign_name;
	}

	/**
	 * @param campaign_name the campaign_name to set
	 */
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	/**
	 * @return the input_map_list
	 */
	public ArrayList<GameMapModel> getInput_map_list() {
		return input_map_list;
	}

	/**
	 * @param input_map_list the input_map_list to set
	 */
	public void setInput_map_list(ArrayList<GameMapModel> input_map_list) {
		this.input_map_list = input_map_list;
	}

	/**
	 * @return the output_map_list
	 */
	public ArrayList<GameMapModel> getOutput_map_list() {
		return output_map_list;
	}

	/**
	 * @param output_map_list the output_map_list to set
	 */
	public void setOutput_map_list(ArrayList<GameMapModel> output_map_list) {
		this.output_map_list = output_map_list;
		setChanged();
		notifyObservers(this);
	}

	
	
}