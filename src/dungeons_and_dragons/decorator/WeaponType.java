/**
 * 
 */
package dungeons_and_dragons.decorator;

/**
 * @author urmil
 *
 */
public class WeaponType extends Weapons{

	private int score;

	public WeaponType(int score){
		this.score = score;
	}

	@Override
	public int getEnhancment() {
		return score;
	}
	
	@Override
	public String getEnchantment() {
		return "";
	}

}
