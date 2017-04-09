package dungeons_and_dragons.strategy;

import java.awt.Point;
import java.util.ArrayList;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.PathFinder;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * @author Mihir Pujara & Tejas Sadrani & Urmil Kansara
 *
 */
public class FriendlyNPC implements Strategy {

	GameMapModel mapModel;

	@Override
	public void move(GamePlayModel gamePlayModel) {

		System.out.println("Friendly NPC Move");
		mapModel = gamePlayModel.getCampaignModel().getOutput_map_list().get(gamePlayModel.getCurrentMapIndex());

		if (mapModel.getMap_enemy_loc().contains(MapCharacter.FRIENDLY)) {

			PathFinder p = new PathFinder(gamePlayModel);
			MapButton path[][] = p.findPath("friend");
			int index = gamePlayModel.currentFriendIndex;

			int i = 3;
			while (i > 0) {
				try {
					findPath(path, gamePlayModel, index);
					LogHelper.Log(LogHelper.TYPE_INFO, "Friend Character Move" + (i + 1));
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
				}
			}

		}

	}

	/**
	 * This function defines a path for the friendly player and correctly displays on the map
	 * @param path
	 * @param gamePlayModel
	 * @param index
	 */
	private void findPath(MapButton[][] path, GamePlayModel gamePlayModel, int index) {

		int mainIndex = 0;

		for (int j = 0; j < mapModel.getMap_enemy_loc().size(); j++) {
			if (mapModel.getMap_enemy_loc().get(j).getCharacter().getCharacter_id() == gamePlayModel.enemyList
					.get(index).getCharacter().getCharacter_id()) {
				mainIndex = j;
			}
		}

		if (path != null) {
			for (int i = 0; i < path.length; i++) {
				for (int j = 0; j < path[0].length; j++) {
					if (path[i][j].path == 1 && path[i][j].getDirty_flag() != 2) {
						path[i][j].setDirty_flag(2);
						
						gamePlayModel.friendList.get(index).setX(path[i][j].getX());
						gamePlayModel.friendList.get(index).setY(path[i][j].getY());
						
						mapModel.getMap_enemy_loc().get(mainIndex).setX(path[i][j].getX());
						mapModel.getMap_enemy_loc().get(mainIndex).setY(path[i][j].getY());
						
						gamePlayModel.notifyChange();
					}
				}
			}
		}
	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		System.out.println("Friendly NPC Move");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		System.out.println("Friendly NPC Move");
		try {
			MapCharacter friendly = gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn());
			GameMapModel map = gamePlayModel.getCampaignModel().getOutput_map_list()
					.get(gamePlayModel.getCurrentMapIndex());

			String msg = "";
			if (map.getMap_chest() != null && map.getMap_chest().getX() != -1 && map.getMap_chest().getY() != -1
					&& map.getMap_chest().getX() == friendly.getX() && map.getMap_chest().getY() == friendly.getY()) {
				ArrayList<ItemModel> backPackItems = friendly.getCharacter().getBackPackItems();
				if (backPackItems.size() < 10) {
					backPackItems.add(map.getMap_chest().getItem());
					friendly.getCharacter().setBackPackItems(backPackItems);

					ItemModel i = gamePlayModel.getCampaignModel().getOutput_map_list()
							.get(gamePlayModel.getCurrentMapIndex()).getMap_chest().getItem();

					msg = "Item " + i.getItem_name() + " has been added in friend's backpack";

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
