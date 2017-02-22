/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import dungeons_and_dragons.helper.GameButton;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.ManageItemView;

/**
 * @author mihir
 *
 */
public class ManageItemController implements ActionListener {

	private ItemModel itemModel;

	private ManageItemView manageItemView;

	public ManageItemController() {

		try {

			itemModel = new ItemModel();

			ArrayList<ItemModel> items = itemModel.getData();

			manageItemView = new ManageItemView(this, items);

			manageItemView.setVisible(true);

			manageItemView.setActionListener(this);

		} catch (JsonSyntaxException | IOException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if (actionEvent.getSource().equals(manageItemView.addItemButton)) {
			new ItemController();
			
			manageItemView.dispose();
			
		} else if (actionEvent.getSource().equals(manageItemView.backButton)) {
			
			new CreateGameController();
			
			manageItemView.dispose();

		} else if (((GameButton) actionEvent.getSource()).getButtonType() == GameButton.BUTTON_TYPE_VIEW) {

			GameButton button = (GameButton) actionEvent.getSource();
			int itemId = button.getId();
			ItemModel item = (ItemModel) button.getSource();

			StringBuilder sb = new StringBuilder("");
			sb.append(item.getItem_name());
			sb.append("\n");
			sb.append(item.getItem_type());
			sb.append("\n");
			sb.append(item.getItem_ability());
			sb.append("\n");
			sb.append(item.getItem_point());

			JOptionPane.showMessageDialog(this.manageItemView, sb.toString());

		}
		else if (((GameButton) actionEvent.getSource()).getButtonType() == GameButton.BUTTON_TYPE_EDIT) {
			GameButton button = (GameButton) actionEvent.getSource();
			int itemId = button.getId();	
			ItemModel item = (ItemModel) button.getSource();
		    
			
			new ItemController(item);
			manageItemView.dispose();
		}
	}

}
