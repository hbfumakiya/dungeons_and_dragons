package dungeons_and_dragons.builder;

import java.util.ArrayList;
import java.util.Collections;

import dungeons_and_dragons.model.AbilityScoresModel;

/**
 * Nimble fighter class to generate ability scores
 * @author 
 *
 */
public class NimbleFighterBuilder extends FighterBuilder {
	/**
	 * Generate ability scores and sort it to arrange it in ascending order
	 */
	public void buildCalculateAbilityScores() {
		ArrayList<Integer> scores = new ArrayList<Integer>();

		for (int i = 0; i < 6; i++) {
			scores.add(this.fighterType.calculate4D6());
		}

		Collections.sort(scores);

		AbilityScoresModel ability = new AbilityScoresModel();
		ability.setDexterity(scores.get(5));
		ability.setConstitution(scores.get(4));
		ability.setstrength(scores.get(3));
		ability.setIntelligence(scores.get(2));
		ability.setCharisma(scores.get(1));
		ability.setWisdom(scores.get(0));
		this.fighterType.setAbilityScores(ability);
		this.fighterType.setRawAbilityScores(ability);
	}

}
