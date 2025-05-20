package game;

import application.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Projectile extends Sprite {
	private double PROJECTILE_SPEED = 10;
	
	public boolean isAlive = true;
	private MainGameScene context;
	private String symbol = "?";

	public Projectile(double x, double y, Image texture, MainGameScene context, String sym) {
		this._image = texture;
		this.x = x;
		this.y = y;
		this.width = 16;
		this.height = 16;
		
		this.context = context;
		
		this.setImageAsset(texture);
		this.symbol = sym;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		if (this.isAlive) {
			gc.drawImage(_image, x, y);
			gc.setFill(Color.WHITE);
			gc.setStroke(Color.rgb(159, 147, 43));
			gc.setLineWidth(2);
			gc.fillText(this.symbol, x + _image.getWidth()/3, y + 5 + _image.getHeight()*3/5);
			gc.strokeText(this.symbol, x + _image.getWidth()/3, y + 5 + _image.getHeight()*3/5);
		}
		
		if (x < App.WINDOW_WIDTH) {
			this.setPosDelta(PROJECTILE_SPEED, 0);
		} else this.isAlive = false;
	}
	
	public static boolean checkCollision(Sprite a, Sprite b) {
		boolean collides = (a.getX() < b.getX() + b.getWidth())
						&& (a.getX() + a.getWidth() > b.getX())
						&& (a.getY() < b.getY() + b.getImageHeight())
						&& (a.getY() + a.getImageHeight() > b.getY());
		
		return collides;
	}
	
	public void kill() {
		this.isAlive = false;
	}
	
	public String getSymbol() { return this.symbol; }
 }
