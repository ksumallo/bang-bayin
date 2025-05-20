package application;
	
import game.GameTick;
import game.MainGameScene;
import game.MainMenuScene;
import game.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 720;
	
	@Override
	public void start(Stage primaryStage) {
		
		// TODO: Make window unresizeable
		try {
			GameTick tickHandler = new GameTick();
			StageManager manager = new StageManager(primaryStage, tickHandler);
			manager.setGameScene(new MainGameScene(manager));
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
