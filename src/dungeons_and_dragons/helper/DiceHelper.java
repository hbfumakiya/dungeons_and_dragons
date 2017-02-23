package dungeons_and_dragons.helper;

import java.util.Random;

/**
 * 
 * @author Mihir Pujara
 *
 */
public class DiceHelper {

	public static int rollD6() {
		return roll(6);
	}
	
	public static int rollD10() {
		return roll(10);
	}

	private static int roll(int max) {
		Random rand = new Random();

		int randomNum = rand.nextInt((max - 1) + 1) + 1;

		return randomNum;
	}
}