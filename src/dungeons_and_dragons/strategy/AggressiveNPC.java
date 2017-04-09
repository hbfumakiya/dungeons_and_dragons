/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.PathFinder;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir Pujara & Tejas Sadrani
 *
 */
public class AggressiveNPC implements Strategy {

	GameMapModel mapModel;

	@Override
	public void move(GamePlayModel gamePlayModel) {
		
		System.out.println("Agresive NPC Move");

		mapModel = gamePlayModel.getCampaignModel().getOutput_map_list().get(gamePlayModel.getCurrentMapIndex());
		PathFinder p = new PathFinder(gamePlayModel);
		MapButton path[][] = p.findPath("enemy");
		int index = gamePlayModel.currentEnemyIndex;

		int i = 3;
		while (i > 0) {
			try {
				findPath(path, gamePlayModel, index);
				LogHelper.Log(LogHelper.TYPE_INFO, "Aggressive Character Move" + (i + 1));
				Thread.sleep(2000);
				i--;
			} catch (InterruptedException e) {
				LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
			}
		}

	}

	/**
	 * This function is used to set character position depending on the
	 * selective path that is available
	 * 
	 * @param path
	 * @param gamePlayModel
	 * @param index
	 */
	private void findPath(MapButton[][] path, GamePlayModel gamePlayModel, int index) {

		boolean flag = false;
		int x = gamePlayModel.enemyList.get(index).getX();
		int y = gamePlayModel.enemyList.get(index).getY();
		int mainIndex = 0;

		for (int j = 0; j < mapModel.getMap_enemy_loc().size(); j++) {
			if (mapModel.getMap_enemy_loc().get(j).getCharacter().getCharacter_id() == gamePlayModel.enemyList
					.get(index).getCharacter().getCharacter_id()) {
				mainIndex = j;
			}
		}

		while (flag!=true) {
			if (func(x + 1, y, path)) {
				flag = true;
				gamePlayModel.enemyList.get(index).setX(x + 1);
				gamePlayModel.enemyList.get(index).setY(y);

				mapModel.getMap_enemy_loc().get(mainIndex).setX(x + 1);
				mapModel.getMap_enemy_loc().get(mainIndex).setY(y);

				gamePlayModel.notifyChange();

			} else if (func(x - 1, y, path)) {
				flag = true;
				gamePlayModel.enemyList.get(index).setX(x - 1);
				gamePlayModel.enemyList.get(index).setY(y);

				mapModel.getMap_enemy_loc().get(mainIndex).setX(x - 1);
				mapModel.getMap_enemy_loc().get(mainIndex).setY(y);

				gamePlayModel.notifyChange();

			} else if (func(x, y + 1, path)) {
				flag = true;
				gamePlayModel.enemyList.get(index).setX(x);
				gamePlayModel.enemyList.get(index).setY(y + 1);

				mapModel.getMap_enemy_loc().get(mainIndex).setX(x);
				mapModel.getMap_enemy_loc().get(mainIndex).setY(y + 1);

				gamePlayModel.notifyChange();

			} else if (func(x, y - 1, path)) {
				flag = true;
				gamePlayModel.enemyList.get(index).setX(x);
				gamePlayModel.enemyList.get(index).setY(y - 1);

				mapModel.getMap_enemy_loc().get(mainIndex).setX(x);
				mapModel.getMap_enemy_loc().get(mainIndex).setY(y - 1);

				gamePlayModel.notifyChange();

			}

		}
	}

	/**
	 * This function is used to see if a path exists for x and y point
	 * 
	 * @param x
	 * @param y
	 * @param path
	 * @return true if there is a path
	 */
	private boolean func(double x, double y, MapButton[][] path) {
		if (path[(int) x][(int) y].path == 1) {
			return true;
		}
		return false;
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
