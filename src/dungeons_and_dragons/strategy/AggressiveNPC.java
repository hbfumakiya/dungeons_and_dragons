/**
 * 
 */
package dungeons_and_dragons.strategy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.helper.PathFinder;
import dungeons_and_dragons.model.GameMapModel;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * @author Mihir Pujara & Tejas Sadrani & Urmil Kansara
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

//		int i = 3;
//		while (i > 0) {
			try {
				//findPath(path, gamePlayModel, index);
				LogHelper.Log(LogHelper.TYPE_INFO, "Aggressive Character Move" + 1);
				MapCharacter enemy = gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn());
				if(enemy.getCharacter().isAlive())
				gamePlayModel.moveNPCOrComputer(enemy);
				Thread.sleep(2000);
				//i--;
			} catch (InterruptedException e) {
				LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
			//}
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

		while (flag != true) {
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
		try {
			gamePlayModel.initiateAttack(gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn()));
			
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Agressive NPC interact");
		System.out.println("Agresive NPC Interact");
		GameMapModel map = gamePlayModel.getCampaignModel().getOutput_map_list()
				.get(gamePlayModel.getCurrentMapIndex());
		MapCharacter enemy = gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn());
		try {

			String msg = "";
			if (map.getMap_chest() != null && map.getMap_chest().getX() != -1 && map.getMap_chest().getY() != -1
					&& map.getMap_chest().getX() == enemy.getX() && map.getMap_chest().getY() == enemy.getY()) {
				ArrayList<ItemModel> backPackItems = enemy.getCharacter().getBackPackItems();
				if (backPackItems.size() < 10) {
					backPackItems.add(map.getMap_chest().getItem());
					enemy.getCharacter().setBackPackItems(backPackItems);

					ItemModel i = gamePlayModel.getCampaignModel().getOutput_map_list()
							.get(gamePlayModel.getCurrentMapIndex()).getMap_chest().getItem();

					msg = "Item " + i.getItem_name() + " has been added in enemy's backpack";

					gamePlayModel.removeChest(new Point(-1, -1));
					LogHelper.Log(LogHelper.TYPE_INFO, msg);
					System.out.println("" + msg);
				} else {
					msg = "Sorry your backpack is full.So cannot add any new Item";
					LogHelper.Log(LogHelper.TYPE_INFO, msg);
					System.out.println("" + msg);
				}
			}
			//check if enemy bumped dead enemy then take its random item
			else {
				for (int i = 0; i < map.getMap_enemy_loc().size(); i++) {
					//check enemy dead or alive
					if (!map.getMap_enemy_loc().get(i).getCharacter().isAlive() ) {
						// check enemy location with dead enemy
						if (enemy.getX() == map.getMap_enemy_loc().get(i).getX()
								&& enemy.getY() == map.getMap_enemy_loc().get(i).getY()) {
							//check if it is not checking same enemy
							if (enemy.getCharacter().getCharacter_id() != map.getMap_enemy_loc().get(i).getCharacter()
									.getCharacter_id()) {
								ArrayList<ItemModel> allEnemyItems = new ArrayList<ItemModel>();
								
								if (enemy.getCharacter().getBackPackItems().size() < 10) {
									if(!map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().isEmpty())
									{
									Collections.shuffle(map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems());

									ItemModel item = map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().get(0);

									map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().remove(item);

									enemy.getCharacter().getBackPackItems().add(item);

									gamePlayModel.notifyChange();
									LogHelper.Log(LogHelper.TYPE_INFO,  enemy.getCharacter().getCharacter_name()+" received this " + item.getItem_name() + "("
											+ item.getItem_type() + ") item from other player which is added into "+enemy.getCharacter().getCharacter_name()+"'s backpack");
									
									}
									else
									{
										LogHelper.Log(LogHelper.TYPE_INFO,  "Sorry " + map.getMap_enemy_loc().get(i).getCharacter().getCharacter_name()
												+ "'s backpack does not have any item.So cannot add any Item" );
									}

								} else {
									LogHelper.Log(LogHelper.TYPE_INFO,  "Sorry " + enemy.getCharacter().getCharacter_name()
											+ "'s backpack is full.So cannot add any Item" );
									
								}
							}
						}

					}
				}
			}
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
