/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir Pujara
 *
 */
public class HumanPlayer implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
		System.out.println(gamePlayModel.getCharacterModel().getCharacter_name()+" name");
		System.out.println("Human Player Move");
		
	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		System.out.println("Human Player Attack");
		
	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		System.out.println("Human Player Interact");
		
	}

}
