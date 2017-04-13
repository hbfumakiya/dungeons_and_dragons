/**
 * 
 */
package dungeons_and_dragons.decorator;

/**
 * This is concrete decorator class
 * @author urmil
 *
 */
public class Frightening extends WeaponsDecorator{
	
	/**
	 * When creating a decorated freezing, pass a weapons to be decorated
	 * as a parameter. Note that this can be an already-decorated weapons.
	 */
	public Frightening(Weapons weapon) {
		super(weapon);
		
	}
	/**
	 * return the frightening weapn and its point
	 * @return String frightening
	 */
	public String getEnchantment()
	{
		return "Frightening-"+super.getEnhancment();
	}
	
}
