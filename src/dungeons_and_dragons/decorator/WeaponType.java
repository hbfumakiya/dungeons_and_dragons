/**
 * 
 */
package dungeons_and_dragons.decorator;

/**
 * This is concrete component
 * @author urmil
 *
 */
public class WeaponType extends Weapons{

	private int score;
	/**
	 * set the score for weapon type
	 * @param score
	 */
	public WeaponType(int score){
		this.score = score;
	}
	/**
	 * to get enhancement score
	 */
	@Override
	public int getEnhancment() {
		return score;
	}
	/**
	 * to get enchantment
	 */
	@Override
	public String getEnchantment() {
		return "";
	}

}
