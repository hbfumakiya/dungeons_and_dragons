/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author mihir
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
