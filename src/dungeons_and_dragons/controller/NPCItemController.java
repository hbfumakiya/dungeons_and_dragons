/**
 * 
 */
package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.NPCItemView;

/**
 * @author Hirangi Naik
 *
 */
public class NPCItemController implements ActionListener{
	
	private NPCItemView view;
	public ArrayList<ItemModel> items;
	public boolean isEnemy;
	private GamePlayController controller;

	/**
	 * 
	 */
	public NPCItemController(GamePlayController gamePlayController, ArrayList<ItemModel> items, boolean isEnemy) {
		
		this.controller=gamePlayController;
		this.isEnemy=isEnemy;
		this.items=items;
		this.view = new NPCItemView(this.items);
		view.setActionListener(this);
		this.view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(view.okButton)){
			
			List<ItemModel> items = this.view.itemList.getSelectedValuesList();
			if ((items == null) || (items.size() < 1))
				return;
			this.controller.gamePlayModel.getCharacterModel().getBackPackItems().addAll(items);
			this.view.dispose();
		}
			
	}

}
