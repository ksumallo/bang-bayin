package game;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game {

	private Stage stage;
	private GameScene currScene;
	private CharacterController ctrl;	
	
	private Sprite laya; 
	Image image, bullet;
	
	public Game(Stage _stage) {
		this.stage = _stage;
		//this.currScene = new MainGameScene(new StageManager());
		
		this.stage.show();
	}
	
	private void init() {

		// Load image assets
		bullet = new Image(getClass().getResource("orb.png").toString());
		image = new Image(getClass().getResource("running.gif").toString());
		laya = new Sprite(image, 16, 16);
	}
}
