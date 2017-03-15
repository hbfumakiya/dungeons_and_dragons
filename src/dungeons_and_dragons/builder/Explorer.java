package dungeons_and_dragons.builder;

import dungeons_and_dragons.model.CharacterModel;

/**
	 * Director of the Builder pattern
	 * 
	 */
public class Explorer {
	

	  /**
	   * The Explorer is to use a specific "build plan": the FighterBuilder
	   */
	  private FighterBuilder builder;

	  public void setBuilder(FighterBuilder newFighterBuilder) {
	    builder = newFighterBuilder;
	  }
	  
	  /**
	   * The Director assumes that all Fighters have the same characteristics
	   * and each part is built by calling the same method
	   * though what these specific methods do may be different.
	   */
	   public void constructFighterType() {
	    builder.createNewFighterType();
	    builder.buildCalculateAbilityScores();
		
	
	  }
	  
	  /**
	   * @return gets the Shelter after it has been built
	   */
	  public CharacterModel getCharacter() {
	    return builder.getCharacter();
	  }
	}



