/**
 * 
 */
package dungeons_and_dragons.helper;

/**
 * @author Mihir Pujara
 *
 */
public class GameStatus {

	public static final int RUNNING = 0;
	
	public static final int KILL_ALL = 1;
	
	public static final int NEXT_LEVEL = 2;
	
	public static final int WON_GAME = 3;
	
	public static final int GAME_OVER = 4;
	
	public static final int CANT_MOVE = 5;
	
	private int gameStatus;

	/**
	 * @return the gameStatus
	 */
	public int getGameStatus() {
		return gameStatus;
	}

	/**
	 * @param gameStatus the gameStatus to set
	 */
	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}
	
}
