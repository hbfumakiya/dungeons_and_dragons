/**
 * 
 */
package dungeons_and_dragons.strategy;

import java.awt.Point;
import java.util.ArrayList;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.MapItem;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * @author Mihir Pujara,Urmil Kansara
 *
 */
public class AggressiveNPC implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO_ERROR, "Agressive NPC Move");
		System.out.println("Agresive NPC Move");
		try {

			MapCharacter enemy = gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn());
			// if(enemy.frighteningTurn<=)
			gamePlayModel.moveEnemy(enemy);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, "");
		}

	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Agressive NPC Attack");
		System.out.println("Agresive NPC Attack");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Agressive NPC interact");
		System.out.println("Agresive NPC Interact");
		try {
			MapCharacter enemy = gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn());
			GameMapModel map = gamePlayModel.getCampaignModel().getOutput_map_list()
					.get(gamePlayModel.getCurrentMapIndex());

			String msg = "";
			if (map.getMap_chest() != null && map.getMap_chest().getX() != -1 && map.getMap_chest().getY() != -1
					&& map.getMap_chest().getX() == enemy.getX() && map.getMap_chest().getY() == enemy.getY()) {
				ArrayList<ItemModel> backPackItems = enemy.getCharacter().getBackPackItems();
				if (backPackItems.size() < 10) {
					backPackItems.add(map.getMap_chest().getItem());
					enemy.getCharacter().setBackPackItems(backPackItems);

					ItemModel i = gamePlayModel.getCampaignModel().getOutput_map_list()
							.get(gamePlayModel.getCurrentMapIndex()).getMap_chest().getItem();

					msg = "Item " + i.getItem_name() + " has been added in your backpack";

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
