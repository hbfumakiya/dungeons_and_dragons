/**
 * 
 */
package dungeons_and_dragons.strategy;

import java.awt.Point;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapButton;
import dungeons_and_dragons.helper.PathFinder;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir & Tejas
 *
 */
public class ComputerPlayer implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Computer Player Move");
		System.out.println("Computer Player Move");

		PathFinder p = new PathFinder(gamePlayModel);
		MapButton path[][] = p.findPath("computer");

		int i = 3;
		while (i > 0) {
			try {
				findPath(path, gamePlayModel);
				LogHelper.Log(LogHelper.TYPE_INFO, "Computer Player Move" + (i + 1));
				Thread.sleep(2000);
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
	 */
	private void findPath(MapButton[][] path, GamePlayModel gamePlayModel) {

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
		LogHelper.Log(LogHelper.TYPE_INFO, "Computer Player attack");
		System.out.println("Computer Player attack");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Computer Player interact");
		System.out.println("Computer Player attack");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
