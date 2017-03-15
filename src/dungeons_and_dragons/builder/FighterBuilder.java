package dungeons_and_dragons.builder;

import dungeons_and_dragons.model.CharacterModel;

/**
 * Abstract Builder class of the Builder pattern
 * 
 */
public abstract class FighterBuilder {
	
	  /**
	   * Product to be constructed by the builder
	   */
	  protected CharacterModel fighterType;
	  /**
	   * Get the constructed Character from the Builder
	   */
	  public CharacterModel getCharacter(){
	    return fighterType;
	  }

	  /**
	   * Create a new unspecified Character that 
	   * will be eventually build by calling the 
	   * following abstract methods in a concrete 
	   * class derived from the Character Model class
	   */
	  public void createNewFighterType(){
	    fighterType = new CharacterModel();
	  }
	  abstract void buildCalculateAbilityScores();
	
}


