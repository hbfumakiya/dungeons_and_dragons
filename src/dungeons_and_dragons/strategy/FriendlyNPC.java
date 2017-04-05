/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir Pujara
 *
 */
public class FriendlyNPC implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
		System.out.println("Friendly NPC Move");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
