package dungeons_and_dragons.builder;

import dungeons_and_dragons.model.CharacterModel;

public class BullyFighterBuilder extends FighterBuilder {

	CharacterModel model;
	public String s;

	public void buildCalculateAbilityScores() {
		model=new CharacterModel();
		System.out.println("buildabi");
		s = "bully";
		this.model.calculateAbilityScores(s);
	}

}
