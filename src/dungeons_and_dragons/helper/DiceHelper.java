package dungeons_and_dragons.helper;

import java.util.Random;
/**
 * 
 * @author Urmil Kansara
 *
 */
public class DiceHelper {

	public DiceHelper() {
		// TODO Auto-generated constructor stub
		
		
		
	}
	
	private int rollD6(){

		return roll(6);
	}
		private int roll(int max) {
			// TODO Auto-generated method stub
			return (int) Math.floor(Math.random() * max  + 1);
			
		}
}