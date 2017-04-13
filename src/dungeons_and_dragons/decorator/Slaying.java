/**
 * 
 */
package dungeons_and_dragons.decorator;

/**
 * This is concrete decorator class
 * @author urmil
 *
 */
public class Slaying extends WeaponsDecorator{
	
	/**
	 * When creating a decorated freezing, pass a weapons to be decorated
	 * as a parameter. Note that this can be an already-decorated weapons.
	 */
	public Slaying(Weapons weapon) {
		super(weapon);
		
	}
	/**
	 * return the Slaying weapn and its point
	 * @return String slaying
	 */
	public String getEnchantment()
	{
		return "Slaying-"+super.getEnhancment();
	}
	
}
