/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.model.GamePlayModel;

/**
 * this is context class for assigning character strategy
 * @author Mihir Pujara
 *
 */
public class CharacterStrategy {

	private Strategy strategy;
	/**
	 * set the strategy of character
	 * @param strategy
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * get strategy of character
	 * @return the strategy
	 */
	public Strategy getStrategy() {
		return strategy;
	}
	/**
	 * It will be executed by all the concrete classes. 
	 * @param gamePlayModel
	 */
	public void executeStrategy(GamePlayModel gamePlayModel) {
		
		this.strategy.move(gamePlayModel);

		this.strategy.attack(gamePlayModel);

		this.strategy.interact(gamePlayModel);
	}
}
