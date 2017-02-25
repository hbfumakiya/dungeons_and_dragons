package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.FileHelper;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CampaignView;

/**
 * @author Hirangi Naik and Tejas Sadrani
 *
 */

public class CampaignController implements ActionListener {

	private CampaignModel campaignModel;
	private CampaignView campaignView;
	private GameMapModel map_model = new GameMapModel();
	private ArrayList<GameMapModel> input_map_list;
	private ArrayList<GameMapModel> output_map_list;

	/**
	 * Constructor of Campaign Controller used to construct campaign models and campaign views
	 */
	public CampaignController() {
		
		//creating campaign model
		this.campaignModel = new CampaignModel();
		
		this.input_map_list = new ArrayList<GameMapModel>();
		this.output_map_list = new ArrayList<GameMapModel>();
		
		try {
			this.input_map_list = FileHelper.getMaps();
		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
		
		this.campaignModel.setInput_map_list(input_map_list);

		//creating campaign view
		this.campaignView = new CampaignView(input_map_list);
		
		this.campaignModel.addObserver(campaignView);

		// set listener
		this.campaignView.setActionListener(this);

		// show game view
		this.campaignView.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		System.out.println(this.campaignView.campaign_add);
		System.out.println(e.getSource().equals(campaignView.campaign_add));
		
		if(e.getSource().equals(this.campaignView.campaign_add)){
			
			this.map_model = (GameMapModel)this.campaignView.campaign_combobox.getSelectedItem();
			this.output_map_list.add(map_model);
			this.input_map_list.remove(map_model);
			
			if(map_model != null)
			{
				this.campaignModel.setInput_map_list(this.input_map_list);
				this.campaignModel.setOutput_map_list(this.output_map_list);
			}
			else 
			{
				JOptionPane.showOptionDialog(null,
						"There are no more maps",
						"Invalid", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
						null);
				return;
			}
		} else if (e.getSource().equals(this.campaignView.moveMapUP)) {

			/*List<GameMapModel> GameMapModel = this.campaignView.output_map_list.getSelectedValuesList();
			if ((GameMapModel == null) || (GameMapModel.size() < 1))
				return;*/

			/*this.character.getItems().removeAll(GameMapModel);
			while (this.character.getItems().remove(null)) {
			}
			this.character.getBackPackItems().addAll(GameMapModel);
			this.characterInventoryView.updateList(this.character);*/
			
			/*List<ItemModel> items = this.characterInventoryView.itemList.getSelectedValuesList();
			if ((items == null) || (items.size() < 1))
				return;

			this.character.getItems().removeAll(items);
			while (this.character.getItems().remove(null)) {
			}
			this.character.getBackPackItems().addAll(items);
			this.characterInventoryView.updateList(this.character);*/

		}
	}

}
