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
		int i = 3;
		while (i > 0) {
			try {
				synchronized (gamePlayModel.gameThread) {
					LogHelper.Log(LogHelper.TYPE_INFO, "Waiting for user input to move human player");
					gamePlayModel.gameThread.wait();
					// perform move checks
					gamePlayModel.gameStatus = gamePlayModel.validateMove(gamePlayModel.charachterTempPoint,
							gamePlayModel.charachterOldPoint);
					if(!(gamePlayModel.gameStatus.getGameStatus() == GameStatus.CANT_MOVE)){
						LogHelper.Log(LogHelper.TYPE_INFO, "Human Player Moved");
						i--;
					}
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
