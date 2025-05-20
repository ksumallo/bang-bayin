package game;

import application.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Enemy extends Sprite {
	private final static int ENEMY_WALK_SPEED = 1;
	private static MainGameScene gameScene;
	private static StatsInterface statsInterface;
	private static Font font;
	
	private boolean isAlive = true;
	private double padding = 0;
	
	private Text insult = new Text("");
	private Placard placard;
		
	public Enemy(Image image) {
		this.x = GameScene.WINDOW_WIDTH/2 * ((Math.random() * 0.5) + 1);
		this.y = Math.random() * (GameScene.WINDOW_HEIGHT - image.getHeight());
		
		this._image = image;
		this.setSize((int)image.getWidth(), (int)image.getHeight());
		
		this.placard = new Placard(this, "ba/ke/di/go/su");
	}
	
	public void move(int dx, int dy) {
		boolean withinXBounds = (getX() + getWidth()) < App.WINDOW_WIDTH,
				withinYBounds = (getY() + getHeight()) < App.WINDOW_HEIGHT;
		
		setPosDelta(ENEMY_WALK_SPEED * dx * ((withinXBounds) ? 1 : 0), 
				ENEMY_WALK_SPEED * dy * ((withinYBounds) ? 1 : 0));
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(_image, x, y);
		
		placard.render(gc);
	}
	
	
	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public static Enemy spawnRandom() {
		Enemy f = new Enemy(gameScene.friar);
		gameScene.getEnemies().add(f);
		System.out.printf("Spawned enemy at (%f, %f)\n", f.getX(), f.getY());
		
		return f;
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	public void setPos(double x, double y) {
		this.setPos(x, y);
	}
	
	public void setImage(Image image) {
		setImageAsset(image);
	}
	
	public static void setGameScene(GameScene gameScene) {
		Enemy.gameScene = (MainGameScene) gameScene;
	}
	
	public static void setStatsInterface(StatsInterface i) {
		Enemy.statsInterface = i;
	}
	
	public boolean damage(String s) {
		return this.placard.attack(s);
	}
	
	public void kill() {
		this.isAlive = false;
	}
	
	public void setPlacardText(String t) {
		this.insult.setText(t);
		this.width = this.insult.getBoundsInLocal().getWidth();
		this.height = this.insult.getBoundsInLocal().getHeight();
	}
}
