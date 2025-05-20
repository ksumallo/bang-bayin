package game;

import application.App;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainMenuScene extends GameScene {
	String[] files = {"0-bg.png", "1-mountains-1.png", "2-mountains-2.png", "3-mountains-3.png", "4-words-1.png", 
			"5-convent-1.png", "6-convent-2.png", "7-ground.png", "9-words-2.png", "10-texture.png" };
	
	double[] translateSpeed = { 0, 0.2, 0.25, 0.3, -0.4, 0.5, 0.5, 0.6, -0.3, 0 };
	
	ParallaxBackground parallax;
	Canvas canvas;
	ImageView gameTitle;
	Button btnPlay, btnHowTo, btnOption, btnAbout, btnQuit;
	VBox vertLayout;
	
	StageManager manager;
	StageInterface iStage;

	public MainMenuScene(StageManager manager) {
		root = new StackPane();
		this.manager = manager;
		iStage = manager;
		
		// Initialize parallax background
		parallax = new ParallaxBackground();
		
		try {
			for (int f = 0; f < files.length; ++f) {
					parallax.addLayer(
							new Image(getClass().getResource("assets/" + files[f]).toString()),
							translateSpeed[f],
							f);
			}
		} catch (Exception e) { e.printStackTrace(); }
		
		vertLayout = new VBox();
		
		btnPlay = new Button("Maglaro");
		btnHowTo = new Button("Paano laruin");
		btnOption = new Button("Mga setting");
		btnAbout = new Button("Mga tagalikha");
		btnQuit = new Button("Isara ang laro");
		
		btnPlay.setOnMouseClicked((e)->{
			iStage.changeScene(new MainGameScene(manager));
		});
		
		gameTitle = new ImageView(getClass().getResource("title.png").toString());
		
		this.canvas = new Canvas(App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
		
		vertLayout.getChildren().addAll(new Node[]{gameTitle, btnPlay, btnHowTo, btnOption, btnAbout, btnQuit});
		vertLayout.setAlignment(Pos.CENTER);
		root.getChildren().addAll(canvas, vertLayout);
		
		//getScene().getStylesheets().add(getClass().getResource("styles_main_menu.css").toExternalForm());
	}

	@Override
	public void draw() {
		// Clear canvas for redraw // .clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		parallax.render(canvas.getGraphicsContext2D());
	}

	@Override
	public void update() {

	}
}
