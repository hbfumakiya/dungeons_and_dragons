/**
 * 
 */
package dungeons_and_dragons.decorator;

/**
 * This is concrete decorator class
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
	/**
	 * return the freezing weapn and its point
	 * @return String freezing
	 */
	public String getEnchantment()
	{
		return "Freezing-"+super.getEnhancment();
	}
	
}
