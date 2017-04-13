package dungeons_and_dragons.helper;

import dungeons_and_dragons.controller.GamePlayController;
import dungeons_and_dragons.model.GamePlayModel;
import dungeons_and_dragons.view.GamePlayView;

public class PostProcessingClass implements Runnable{

	public GamePlayModel gamePlayModel;
	public GamePlayView gamePlayView;
	public GamePlayController gamePlayController;
	
	public PostProcessingClass(GamePlayModel gamePlayModel, GamePlayView gamePlayView, GamePlayController gamePlayController) {
		this.gamePlayModel = gamePlayModel;
		this.gamePlayView = gamePlayView;
		this.gamePlayController = gamePlayController;
	}

	@Override
	public void run() {
		while (this.gamePlayModel.isGameRunning()) {
			this.gamePlayController.postProcessing(this.gamePlayModel.gameStatus);
		}
	}

	
}
