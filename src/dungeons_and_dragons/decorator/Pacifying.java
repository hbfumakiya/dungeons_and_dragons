/**
 * 
 */
package dungeons_and_dragons.decorator;
/**
 * This is concrete decorator class
 * @author urmil
 *
 */
public class Pacifying extends WeaponsDecorator{
	
	/**
	 * When creating a decorated freezing, pass a weapons to be decorated
	 * as a parameter. Note that this can be an already-decorated weapons.
	 */
	public Pacifying(Weapons weapon) {
		super(weapon);
		
	}
	/**
	 * return the pacifying weapon and its point
	 * @return String pacifying
	 */
	public String getEnchantment()
	{
		return "Pacifying-"+super.getEnhancment();
	}
	
}
