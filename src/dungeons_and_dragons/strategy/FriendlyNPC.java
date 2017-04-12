package dungeons_and_dragons.strategy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dungeons_and_dragons.helper.GameStatus;
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
	GameStatus gameStatus;

	@Override
	public void move(GamePlayModel gamePlayModel) {

		System.out.println("Friendly NPC Move");
		MapCharacter friend = gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn());
		Point friendPoint = (Point) new Point(friend.getX(), friend.getY()).clone();
		Point oldPoint = (Point) friendPoint.clone();
		int i = 0;
		while (i < 3) {
			try {
				gameStatus = gamePlayModel.moveFriend(friend,friendPoint,oldPoint,i);
				Thread.sleep(2000);
				if(!(gamePlayModel.gameStatus.getGameStatus() == GameStatus.CANT_MOVE)){
					friendPoint = (Point) new Point(friend.getX(), friend.getY()).clone();
					oldPoint = (Point) friendPoint.clone();
					i++;
				}
			} catch (InterruptedException e) {
				LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
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
			else {
				for (int i = 0; i < map.getMap_enemy_loc().size(); i++) {
					//check enemy dead or alive
					if (!map.getMap_enemy_loc().get(i).getCharacter().isAlive()) {
						// check enemy location with dead enemy
						if (friendly.getX() == map.getMap_enemy_loc().get(i).getX()
								&& friendly.getY() == map.getMap_enemy_loc().get(i).getY()) {
							//check if it is not checking same enemy
							if (friendly.getCharacter().getCharacter_id() != map.getMap_enemy_loc().get(i).getCharacter()
									.getCharacter_id()) {
								ArrayList<ItemModel> allEnemyItems = new ArrayList<ItemModel>();
								
								if (friendly.getCharacter().getBackPackItems().size() < 10) {
									if(!map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().isEmpty())
									{
									Collections.shuffle(map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems());

									ItemModel item = map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().get(0);

									map.getMap_enemy_loc().get(i).getCharacter().getBackPackItems().remove(item);

									friendly.getCharacter().getBackPackItems().add(item);

									map.getMap_enemy_loc().get(i).getCharacter().getItems().remove(item);

									//this.items.add(item);
									gamePlayModel.notifyChange();
									JOptionPane.showMessageDialog(new JFrame(), "You received this " + item.getItem_name() + "("
											+ item.getItem_type() + ") item from friendly player which is added into your backpack");
									}

								} else {
									JOptionPane.showMessageDialog(new JFrame(), "Sorry " + friendly.getCharacter().getCharacter_name()
											+ "'s (Friendly Player) backpack is full.So cannot exchange any Item");
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
