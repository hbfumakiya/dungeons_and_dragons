/**
 * 
 */
package dungeons_and_dragons.strategy;

import java.awt.Point;
import java.util.ArrayList;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * @author mihir,Urmil kansara
 *
 */
public class ComputerPlayer implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Computer Player Move");
		System.out.println("Computer Player Move");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Computer Player attack");
		try {

			gamePlayModel.initiateAttack(gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn()));
			
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}
	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Computer Player interact");
		System.out.println("Computer Player attack");
		try {
			MapCharacter playerOrNPC = gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn());
			GameMapModel map = gamePlayModel.getCampaignModel().getOutput_map_list()
					.get(gamePlayModel.getCurrentMapIndex());

			String msg = "";
			if (map.getMap_chest() != null && map.getMap_chest().getX() != -1 && map.getMap_chest().getY() != -1
					&& map.getMap_chest().getX() == playerOrNPC.getX() && map.getMap_chest().getY() == playerOrNPC.getY()) {
				ArrayList<ItemModel> backPackItems = playerOrNPC.getCharacter().getBackPackItems();
				if (backPackItems.size() < 10) {
					backPackItems.add(map.getMap_chest().getItem());
					playerOrNPC.getCharacter().setBackPackItems(backPackItems);

					ItemModel i = gamePlayModel.getCampaignModel().getOutput_map_list()
							.get(gamePlayModel.getCurrentMapIndex()).getMap_chest().getItem();

					msg = "Item " + i.getItem_name() + " has been added in "+playerOrNPC.getCharacter().getCharacter_name()+"'s backpack";

					gamePlayModel.removeChest(new Point(-1, -1));
					LogHelper.Log(LogHelper.TYPE_INFO, msg);
					System.out.println("" + msg);
				} else {
					msg = "Sorry your backpack is full.So cannot add any new Item";
					LogHelper.Log(LogHelper.TYPE_INFO, msg);
					System.out.println("" + msg);
				}
			}
			
			
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
