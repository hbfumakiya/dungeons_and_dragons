/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.model.GamePlayModel;

/**
 * @author Mihir Pujara
 *
 */
public class CharacterStrategy {

	private Strategy strategy;

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * @return the strategy
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	public void executeStrategy(GamePlayModel gamePlayModel) {
		
		this.strategy.move(gamePlayModel);

		this.strategy.attack(gamePlayModel);

		this.strategy.interact(gamePlayModel);
	}
}
