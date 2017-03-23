/**
 * This file is created to mange View,Edit and creating of new Maps
 *   
 */
package dungeons_and_dragons.controller;

/**
 * @author Urmil Kansara
 *
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.GameButton;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.ManageMapView;
import dungeons_and_dragons.view.MapView;

/**
 * 
 * This class is created to mange View,Edit and creating of new Maps by
 * implementing ActionListener
 * 
 * @author Urmil Kansara
 *
 */
public class ManageMapController implements ActionListener {

	private GameMapModel mapModel;

	private ManageMapView manageMapView;

	/**
	 * Default constructor to initialize model and view of managing maps
	 * initialize model and view
	 */
	public ManageMapController() {

		try {

			mapModel = new GameMapModel();

			ArrayList<GameMapModel> maps = mapModel.getData();

			manageMapView = new ManageMapView(this, maps);

			manageMapView.setVisible(true);

			manageMapView.setActionListener(this);

		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
	}

	/**
	 * This method will listen all the events of buttons(Edit,View,Back and Add
	 * Map)
	 * 
	 * @param actionEvent
	 *            event of actions performed
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if (actionEvent.getSource().equals(manageMapView.addItemButton)) {
			new MapGridController();

			manageMapView.dispose();

		} else if (actionEvent.getSource().equals(manageMapView.backButton)) {

			new CreateGameController();

			manageMapView.dispose();

		} else if (((GameButton) actionEvent.getSource()).getButtonType() == GameButton.BUTTON_TYPE_VIEW) {

			GameButton button = (GameButton) actionEvent.getSource();
			int mapId = button.getId();
			GameMapModel map = (GameMapModel) button.getSource();

			new MapView(map);
			manageMapView.dispose();

		} else if (((GameButton) actionEvent.getSource()).getButtonType() == GameButton.BUTTON_TYPE_EDIT) {
			GameButton button = (GameButton) actionEvent.getSource();
			int mapId = button.getId();
			GameMapModel map = (GameMapModel) button.getSource();

			new MapGridController(map);
			manageMapView.dispose();
		}
	}

}
