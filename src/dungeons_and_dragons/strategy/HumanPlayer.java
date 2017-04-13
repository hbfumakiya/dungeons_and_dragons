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
		int i = 0;
		while (i < 3) {
			LogHelper.Log(LogHelper.TYPE_INFO, "Waiting for user input to move human player");
			try {
				
				synchronized (gamePlayModel.gameThread) {
					gamePlayModel.gameThread.wait();
					// perform move checks
					gamePlayModel.gameStatus = gamePlayModel.validateMove(gamePlayModel.charachterTempPoint,
							gamePlayModel.charachterOldPoint);
					if(!(gamePlayModel.gameStatus.getGameStatus() == GameStatus.CANT_MOVE)){
						LogHelper.Log(LogHelper.TYPE_INFO, "Human Player Moves "+(i+1));
						i++;
					}else if(!(gamePlayModel.gameStatus.getGameStatus() == GameStatus.RUNNING)){
						
					}
				}
			} catch (InterruptedException e) {
				LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
			}
		}
	}

	@Override
	public void attack(GamePlayModel gamePlayModel) {
		
		try {
			
			gamePlayModel.initiateAttack(gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn()));
			LogHelper.Log(LogHelper.TYPE_INFO, "Human Player has attacked");
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
