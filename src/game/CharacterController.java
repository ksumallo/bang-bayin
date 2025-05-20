package game;

import application.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CharacterController implements StatsInterface {
	
	private Sprite character;
	private GameScene gameScene;
	
	// Player physics constants
	private double PLAYER_SPEED = 5;
	private double MAX_PLAYER_SPEED = 3;
	private double xPLAYER_SPEED = 0;
	private double yPLAYER_SPEED = 0;
	private double acceleration = 1;
	
	// Player stats
	private int diwa;
	private int buhay;
	private int karunungan;
	
	public CharacterController(Image c) {
		this.character = new Sprite(c);
	}
	
	public void moveXY(int dx, int dy) {
		boolean withinXBounds = ((character.getX() + character.getWidth()) < App.WINDOW_WIDTH),
				withinYBounds = (character.getY() + character.getHeight()) < App.WINDOW_HEIGHT;
		
		character.setPosDelta(xPLAYER_SPEED * dx - ((withinXBounds) ? 0 : xPLAYER_SPEED), 
							yPLAYER_SPEED * dy - ((withinYBounds) ? 0 : yPLAYER_SPEED));
	}
	
	public void move(int dx, int dy) {
		boolean withinXBounds = ((character.getX() + character.getWidth()) < App.WINDOW_WIDTH),
				withinYBounds = (character.getY() + character.getHeight()) < App.WINDOW_HEIGHT;
		
		character.setPosDelta(PLAYER_SPEED * dx * ((withinXBounds) ? 1 : 0), 
							PLAYER_SPEED * dy * ((withinYBounds) ? 1 : 0));
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(this.character._image, this.character.getX(), this.character.getY());
	}
	
	public Sprite getChar() {
		return this.character;
	}
	
	public double getWalkSpeed() {
		return this.PLAYER_SPEED;
	}
	
	public double getXWalkSpeed() {
		return this.xPLAYER_SPEED;
	}
	
	public double getYWalkSpeed() {
		return this.yPLAYER_SPEED;
	}
	
	public void setPos(double x, double y) {
		this.character.setPos(x, y);
	}
	
	public void setWalkSpeed(double speed) {
		this.PLAYER_SPEED = (speed < 0) ? 0 : (speed > MAX_PLAYER_SPEED) ? MAX_PLAYER_SPEED : speed;
	}
	
	public void setXWalkSpeed(double speed) {
		this.xPLAYER_SPEED = (speed < 0) ? 0 : (speed > MAX_PLAYER_SPEED) ? MAX_PLAYER_SPEED : speed;
	}
	
	public void setYWalkSpeed(double speed) {
		this.yPLAYER_SPEED = (speed < 0) ? 0 : (speed > MAX_PLAYER_SPEED) ? MAX_PLAYER_SPEED : speed;
	}
	
	public void setCharImage(Image image) {
		character.setImageAsset(image);
	}
	
	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
	}
	
	public double getMaxSpeed() {
		return this.MAX_PLAYER_SPEED;
	}
	
	public void setMaxSpeed(double max_v) {
		this.MAX_PLAYER_SPEED = max_v;
	}
	
	public double getAcceleration() {
		return this.acceleration;
	}
	
	public void setAcceleration(double accel) {
		this.acceleration = accel;
	}
	
	public double getWidth() {
		return this.character.getWidth();
	}
	
	public double getHeight() {
		return this.character.getHeight();
	}

	@Override
	public void consumeDiwa() {
		
	}

	@Override
	public void gainKarunungan(int xp) {
		this.karunungan += xp;
	}

	@Override
	public void reduceBuhay() {
		
	}

	public int getKarunungan() {
		return this.karunungan;
	}
}
