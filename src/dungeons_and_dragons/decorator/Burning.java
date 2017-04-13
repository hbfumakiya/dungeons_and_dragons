/**
 * 
 */
package dungeons_and_dragons.decorator;

/**
 * This is concrete decorator class
 * @author urmil
 *
 */
public class Burning extends WeaponsDecorator{
	
	/**
	 * When creating a decorated freezing, pass a weapons to be decorated
	 * as a parameter. Note that this can be an already-decorated weapons.
	 */
	public Burning(Weapons weapon) {
		super(weapon);
		
	}
	/**
	 * return the burning weapn and its point
	 * @return String burning
	 */
	public String getEnchantment()
	{
		return "Burning-"+super.getEnhancment()*5;
	}
	
}
