/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.helper.GameStatus;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir Pujara & Tejas Sadrani
 *
 */
public class HumanPlayer implements Strategy {

	@Override
	public void move(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Waiting for user input to move human player");
		int i = 3;
		while (i > 0) {
			try {
				synchronized (gamePlayModel.gameThread) {
					gamePlayModel.gameThread.wait();
					// perform move checks
					//gamePlayModel.gameStatus = gamePlayModel.validateMove(gamePlayModel.charachterTempPoint,
					//		gamePlayModel.charachterOldPoint);
					gamePlayModel.gameStatus = gamePlayModel.moveCharacter(gamePlayModel.charachterTempPoint, gamePlayModel.charachterOldPoint);
					LogHelper.Log(LogHelper.TYPE_INFO, "Human Player Moved");
					if(!gamePlayModel.iswallBumped())
					i--;
				}
			} catch (InterruptedException e) {
				LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
			}
		}
	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		LogHelper.Log(LogHelper.TYPE_INFO, "Human Player attack");
		try {

			gamePlayModel.initiateAttack(gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn()));
			
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
