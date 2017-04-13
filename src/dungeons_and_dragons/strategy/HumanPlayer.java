/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.helper.GameStatus;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.helper.LogHelper;
import dungeons_and_dragons.helper.MapCharacter;
import dungeons_and_dragons.model.GamePlayModel;

/**
 * This is concrete class for human player
 * @author Mihir Pujara & Tejas Sadrani
 *
 */
public class HumanPlayer implements Strategy {
	/**
	 * move of human character
	 */
	@Override
	public void move(GamePlayModel gamePlayModel) {
		int i = 0; 
		int j = 0;
		
		MapCharacter human = gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn());
		if(human.Freezing){
				j = human.freezingBonus;
		}
		
		while (i < 3 && j == 0) {
			human.Freezing = false;
			LogHelper.Log(LogHelper.TYPE_INFO, "Waiting for user input to move human player");
			try {
				
				synchronized (gamePlayModel.gameThread) {
					gamePlayModel.gameThread.wait();
					// perform move checks
					gamePlayModel.gameStatus = gamePlayModel.validateMove(gamePlayModel.charachterTempPoint,
							gamePlayModel.charachterOldPoint,human);
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
		
		if(j>0){
			LogHelper.Log(LogHelper.TYPE_INFO, "Human Player freezed for "+j+ " moves");
			human.freezingBonus--;
		}
	}
	/**
	 * attack of human character
	 */
	@Override
	public void attack(GamePlayModel gamePlayModel) {
		
		try {			
			gamePlayModel.initiateAttack(gamePlayModel.getTurnList().get(gamePlayModel.getCurrentTurn()));
			Thread.sleep(Game_constants.TIME_CONSTANT);
		} catch (InterruptedException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

	}
	
	/**
	 * interact of human character
	 */
	@Override
	public void interact(GamePlayModel gamePlayModel) {
		System.out.println("Human Player Interact");
		try {
			gamePlayModel.gameStatus = gamePlayModel.initateInteract(gamePlayModel.charachterTempPoint);
			Thread.sleep(Game_constants.TIME_CONSTANT);
		} catch (InterruptedException e) {
			LogHelper.Log(LogHelper.TYPE_ERROR, e.getMessage());
		}

	}

}
