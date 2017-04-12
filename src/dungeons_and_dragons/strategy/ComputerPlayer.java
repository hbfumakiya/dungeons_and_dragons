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
 * @author Mihir & Tejas & kansara
 *
 */
public class ComputerPlayer implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
		/*LogHelper.Log(LogHelper.TYPE_INFO, "Computer Player Move");
		System.out.println("Computer Player Move");

		PathFinder p = new PathFinder(gamePlayModel);
		MapButton path[][] = p.findPath("computer");*/

//		int i = 3;
//		while (i > 0) {
			try {
				//findPath(path, gamePlayModel);
				LogHelper.Log(LogHelper.TYPE_INFO, "Computer Player Move");
				
				MapCharacter computer = gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn());
				if(computer.getCharacter().isAlive())
				gamePlayModel.moveNPCOrComputer(computer);
				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		//	}
		}
	}

	/**
	 * This function is used to set character position depending on the
	 * selective path that is available
	 * 
	 * @param path
	 * @param gamePlayModel
	 */
	/*private void findPath(MapButton[][] path, GamePlayModel gamePlayModel) {

		boolean flag = false;

		while (flag == true) {
			if (func(gamePlayModel.getGameCharacterPosition().getX() + 1,
					gamePlayModel.getGameCharacterPosition().getY(), path)) {
				flag = true;
				gamePlayModel
						.setGameCharacterPosition(new Point((int) gamePlayModel.getGameCharacterPosition().getX() + 1,
								(int) gamePlayModel.getGameCharacterPosition().getY()));
			} else if (func(gamePlayModel.getGameCharacterPosition().getX() - 1,
					gamePlayModel.getGameCharacterPosition().getY(), path)) {
				flag = true;
				gamePlayModel
						.setGameCharacterPosition(new Point((int) gamePlayModel.getGameCharacterPosition().getX() - 1,
								(int) gamePlayModel.getGameCharacterPosition().getY()));
			} else if (func(gamePlayModel.getGameCharacterPosition().getX(),
					gamePlayModel.getGameCharacterPosition().getY() + 1, path)) {
				flag = true;
				gamePlayModel.setGameCharacterPosition(new Point((int) gamePlayModel.getGameCharacterPosition().getX(),
						(int) gamePlayModel.getGameCharacterPosition().getY() + 1));
			} else if (func(gamePlayModel.getGameCharacterPosition().getX(),
					gamePlayModel.getGameCharacterPosition().getY() - 1, path)) {
				flag = true;
				gamePlayModel.setGameCharacterPosition(new Point((int) gamePlayModel.getGameCharacterPosition().getX(),
						(int) gamePlayModel.getGameCharacterPosition().getY() - 1));
			}
		}
	}*/

	/**
	 * This function is used to see if a path exists for x and y point
	 * 
	 * @param x
	 * @param y
	 * @param path
	 * @return true if there is a path
	 */
	/*private boolean func(double x, double y, MapButton[][] path) {
		if (path[(int) x][(int) y].path == 1) {
			return true;
		}
		return false;
	}*/

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
			else {
				for (int i = 0; i < map.getMap_enemy_loc().size(); i++) {
					//check enemy dead or alive
					if (!map.getMap_enemy_loc().get(i).getCharacter().isAlive()) {
						// check enemy location with dead enemy
						if (playerOrNPC.getX() == map.getMap_enemy_loc().get(i).getX()
								&& playerOrNPC.getY() == map.getMap_enemy_loc().get(i).getY()) {
							//check if it is not checking same enemy
							if (playerOrNPC.getCharacter().getCharacter_id() != map.getMap_enemy_loc().get(i).getCharacter()
									.getCharacter_id()) {
								ArrayList<ItemModel> allEnemyItems = new ArrayList<ItemModel>();
								
								if (playerOrNPC.getCharacter().getBackPackItems().size() < 10) {
									if(!map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().isEmpty())
									{
									Collections.shuffle(map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems());

									ItemModel item = map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().get(0);

									map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().remove(item);

									playerOrNPC.getCharacter().getBackPackItems().add(item);

									gamePlayModel.notifyChange();
									LogHelper.Log(LogHelper.TYPE_INFO,  playerOrNPC.getCharacter().getCharacter_name()+" received this " + item.getItem_name() + "("
											+ item.getItem_type() + ") item from other player which is added into "+playerOrNPC.getCharacter().getCharacter_name()+"'s backpack");
									}
									else
									{
										LogHelper.Log(LogHelper.TYPE_INFO,  "Sorry " + map.getMap_enemy_loc().get(i).getCharacter().getCharacter_name()
												+ "'s backpack does not have any item.So cannot add any Item" );
									}

								} else {
									LogHelper.Log(LogHelper.TYPE_INFO,  "Sorry " + playerOrNPC.getCharacter().getCharacter_name()
											+ "'s backpack is full.So cannot add any Item" );
								}
							}
						}

					}
					else if( map.getMap_enemy_loc().get(i).getCharacterType().equals(MapCharacter.FRIENDLY))
					{
						if (playerOrNPC.getCharacter().getBackPackItems().size() < 10) {
							if(!map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().isEmpty())
							{
							Collections.shuffle(map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems());

							ItemModel item = playerOrNPC.getCharacter().getBackPackItems().get(0);

							playerOrNPC.getCharacter().getBackPackItems().remove(item);

							playerOrNPC.getCharacter().getBackPackItems().add(map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().get(0));

							map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().remove(map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().get(0));

							map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().add(item);

							LogHelper.Log(LogHelper.TYPE_INFO, "You received this " + item.getItem_name() + "("
									+ item.getItem_type() + ") item from friendly player which is added into your backpack");
							}

						} else {
							LogHelper.Log(LogHelper.TYPE_INFO, "Sorry " + playerOrNPC.getCharacter().getCharacter_name()
									+ "'s (Friendly Player) backpack is full.So cannot exchange any Item");
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
