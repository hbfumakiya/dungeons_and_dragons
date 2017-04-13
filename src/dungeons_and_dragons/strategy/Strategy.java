/**
 * 
 */
package dungeons_and_dragons.strategy;

import dungeons_and_dragons.model.GamePlayModel;

/**
 * interface to be implemented in other classes.
 * @author Mihir Pujara
 *
 */
public interface Strategy {
	public void move(GamePlayModel gamePlayModel);

	public void attack(GamePlayModel gamePlayModel);

	public void interact(GamePlayModel gamePlayModel);
}
