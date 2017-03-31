package dungeons_and_dragons.helper;

import java.util.Random;

/**
 * This class is created as helper to calculate the dice value using random
 * function
 * 
 * @author Mihir Pujara
 *
 */
public class DiceHelper {

	/**
	 * This method will generate random number from 1 to 6
	 * 
	 * @return value of dice 6
	 */
	public static int rollD6() {
		return roll(6);
	}

	/**
	 * This method will generate random number from 1 to 10
	 * 
	 * @return value of dice 10
	 */
	public static int rollD10() {
		return roll(10);
	}
	
	/**
	 * This method will generate random number from 1 to 20
	 * 
	 * @return value of dice 20
	 */
	public static int rollD20() {
		return roll(20);
	}
	

	/**
	 * This method will generate random number from 1 to max
	 * 
	 * @param max
	 *            varaible upto which you want to generate max number
	 * @return random number
	 */
	private static int roll(int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - 1) + 1) + 1;
		return randomNum;
	}
}