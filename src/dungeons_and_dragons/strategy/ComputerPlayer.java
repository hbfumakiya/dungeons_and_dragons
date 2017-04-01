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
		
	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		System.out.println("Computer Player Attack");
		
	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		System.out.println("Computer Player Interact");
		
	}

}
