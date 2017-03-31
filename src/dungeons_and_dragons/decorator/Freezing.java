/**
 * 
 */
package dungeons_and_dragons.decorator;

/**
 * @author urmil
 *
 */
public class Freezing extends WeaponsDecorator{
	
	/**
	 * When creating a decorated freezing, pass a weapons to be decorated
	 * as a parameter. Note that this can be an already-decorated weapons.
	 */
	public Freezing(Weapons weapon) {
		super(weapon);
		
	}
	
	public String getEnchantment()
	{
		return "Freezing-"+super.getEnhancment();
	}
	
}
