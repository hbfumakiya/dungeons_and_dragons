package dungeons_and_dragons.strategy;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.PathFinder;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir Pujara & Tejas Sadrani
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
