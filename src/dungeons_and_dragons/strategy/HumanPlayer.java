/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir Pujara
 *
 */
public class HumanPlayer implements Strategy {
	
	@Override
	public void move(GamePlayModel gamePlayModel) {
		
		int i = 3;
		System.out.println(gamePlayModel.getCharacterModel().getCharacter_name() + " name");
		System.out.println("Human Player Move");
		
		while(i>0)
		{
			try {
				synchronized (gamePlayModel.gameThread) {
					gamePlayModel.gameThread.wait();
					//perform move checks 
					gamePlayModel.gameStatus = gamePlayModel.validateMove(gamePlayModel.charachterTempPoint,gamePlayModel.charachterOldPoint);
					i--;
				}
			} catch (InterruptedException e) {
				LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
			}
		}
		LogHelper.Log(LogHelper.TYPE_INFO, "Human Player move");
	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Human Player attack");
		System.out.println("Human Player Attack");
		try {
			if(gamePlayModel.validateAttack(gamePlayModel.charachterTempPoint)){
				gamePlayModel.gameStatus = gamePlayModel.initiateAttack();
			}
			else{
				LogHelper.Log(LogHelper.TYPE_INFO,"Player not in range to attack...");
			}
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

	}

	@Override
	public void interact(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Human Player interact");
		System.out.println("Human Player Interact");
		try {
			gamePlayModel.gameStatus = gamePlayModel.initateInteract(gamePlayModel.charachterTempPoint);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

	}

}
