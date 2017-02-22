/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.Point;
/**
 * @author Urmil Kansara
 *
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.GameButton;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.view.ManageItemView;
import dungeons_and_dragons.view.ManageMapView;
import dungeons_and_dragons.view.MapGridView;
import dungeons_and_dragons.view.MapView;

/**
 * @author Urmil Kansara
 *
 */
public class ManageMapController implements ActionListener {

	private GameMapModel mapModel;

	private ManageMapView manageMapView;

	public ManageMapController() {

		try {

			mapModel = new GameMapModel();

			ArrayList<GameMapModel> items = mapModel.getData();

			manageMapView = new ManageMapView(this, items);

			manageMapView.setVisible(true);

			manageMapView.setActionListener(this);

		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
	}

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
			int itemId = button.getId();
			GameMapModel map = (GameMapModel) button.getSource();

			new MapView(map);
			manageMapView.dispose();
//			StringBuilder sb = new StringBuilder("");
//			sb.append(item.getMap_name());
//			sb.append("\n");
			

//			JOptionPane.showMessageDialog(this.manageMapView, sb.toString());

		}
		else if (((GameButton) actionEvent.getSource()).getButtonType() == GameButton.BUTTON_TYPE_EDIT) {
			GameButton button = (GameButton) actionEvent.getSource();
			int itemId = button.getId();	
			GameMapModel map = (GameMapModel) button.getSource();
		    
			
			new MapGridController(map);
			//x.setListener(this);
			manageMapView.dispose();
		}
	}

}
