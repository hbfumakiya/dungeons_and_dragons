/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir Pujara
 *
 */
public class AggressiveNPC implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
		System.out.println("Agresive NPC Move");
		
	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		System.out.println("Agresive NPC Attack");
		
	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		System.out.println("Agresive NPC Interact");
		
	}

}
