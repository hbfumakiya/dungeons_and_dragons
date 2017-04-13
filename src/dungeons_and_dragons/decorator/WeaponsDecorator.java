/**
 * 
 */
package dungeons_and_dragons.decorator;

/**
 * This is decorator class
 * @author urmil
 *
 */
public class WeaponsDecorator extends Weapons{
	
	protected final Weapons weapon;
	/**
	 * to set weapon
	 * @param weapon
	 */
	public WeaponsDecorator(Weapons weapon) {
		this.weapon = weapon;
	}
	/**
	 * to get enhancement score
	 */
	@Override
	public String getEnchantment() {
		return weapon.getEnchantment();
	}
	/**
	 * to get enachantment
	 */
	@Override
	public int getEnhancment() {
		return weapon.getEnhancment();
	}

}
