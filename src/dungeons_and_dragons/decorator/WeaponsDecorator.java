/**
 * 
 */
package dungeons_and_dragons.decorator;

/**
 * @author urmil
 *
 */
public class WeaponsDecorator extends Weapons{
	
	protected final Weapons weapon;
	
	public WeaponsDecorator(Weapons weapon) {
		this.weapon = weapon;
	}
	@Override
	public String getEnchantment() {
		return weapon.getEnchantment();
	}
	
	@Override
	public int getEnhancment() {
		return weapon.getEnhancment();
	}

}
