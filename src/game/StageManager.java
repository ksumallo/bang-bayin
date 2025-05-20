package game;

import application.App;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StageManager implements StageInterface {
	
	private Stage stage;
	private Scene scene;
	private GameTick gameTickHandler;
	
	public StageManager(Stage s, GameTick tickHandler) {
		stage = s;
		scene = new Scene(new StackPane(), App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
		gameTickHandler = tickHandler;
		
		scene.getStylesheets().add(getClass().getResource("styles_main_menu.css").toExternalForm());
		
		stage.setScene(scene);
		stage.show();
		
		gameTickHandler.start();
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	public void setGameScene(GameScene gameScene) {
		scene.setRoot(gameScene.getRoot());
		gameTickHandler.setScene(gameScene);
	}
	
	public void setTickHandler(GameTick gameTick) {
		gameTickHandler = gameTick;
	}

	@Override
	public void changeScene(GameScene gameScene) {
		setGameScene(gameScene);
	}
}
