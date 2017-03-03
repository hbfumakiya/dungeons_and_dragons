/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.GameButton;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.view.ManageCampaignView;

/**
 * This class is created to handle all the task of viewing,saving and editing of
 * campaign.
 * 
 * @author Urmil Kansara
 * 
 */

public class ManageCampaignController implements ActionListener {

	private CampaignModel campaignModel;

	private ManageCampaignView manageCampaignView;

	/**
	 * Default constructor for campaign model campaign model and view is
	 * initialized
	 */
	public ManageCampaignController() {

		try {

			campaignModel = new CampaignModel();

			ArrayList<CampaignModel> items = campaignModel.getData();

			manageCampaignView = new ManageCampaignView(this, items);

			manageCampaignView.setVisible(true);

			manageCampaignView.setActionListener(this);

		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
	}

	/**
	 * Action event to handle viewing,going back to previous screen,adding of
	 * campaign and update of campaign.
	 * 
	 * @param actionevent
	 *            Actionevent argument to control event
	 */

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if (actionEvent.getSource().equals(manageCampaignView.addCampaignButton)) {
			new CampaignController();

			manageCampaignView.dispose();

		} else if (actionEvent.getSource().equals(manageCampaignView.backButton)) {

			new CreateGameController();

			manageCampaignView.dispose();

		} else if (((GameButton) actionEvent.getSource()).getButtonType() == GameButton.BUTTON_TYPE_VIEW) {

			GameButton button = (GameButton) actionEvent.getSource();
			int itemId = button.getId();
			CampaignModel map = (CampaignModel) button.getSource();

			// new MapView(map);
			new CampaignController(map, "view");
			manageCampaignView.dispose();
			// StringBuilder sb = new StringBuilder("");
			// sb.append(item.getMap_name());
			// sb.append("\n");

			// JOptionPane.showMessageDialog(this.manageMapView, sb.toString());

		} else if (((GameButton) actionEvent.getSource()).getButtonType() == GameButton.BUTTON_TYPE_EDIT) {
			GameButton button = (GameButton) actionEvent.getSource();
			int itemId = button.getId();
			CampaignModel map = (CampaignModel) button.getSource();

			new CampaignController(map, "edit");
			// x.setListener(this);
			manageCampaignView.dispose();
		}
	}

}
