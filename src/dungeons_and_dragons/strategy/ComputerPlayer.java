/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author mihir
 *
 */
public class ComputerPlayer implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
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
		System.out.println("Computer Player Attack");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		System.out.println("Computer Player Interact");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
