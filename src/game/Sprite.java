package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends Renderable {
	
	protected Image _image;

	public Sprite() {
		super();
	}
	
	public Sprite(Image image) {
		this._image = image;
		this.setSize((int)image.getWidth(), (int)image.getHeight());
	}
	
	// With position
	public Sprite(Image image, double x, double y) {
		super(x, y);
		this._image = image;
	}
	
	// With position and size
	public Sprite(Image image, double x, double y, int w, int h) {
		super(x, y);
		this.setSize(w, h);
		
		this._image = image;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(this._image, super.x, this.y, this.width, this.height);
	}
	
	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPosDelta(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
	public void setImageAsset(Image image) { 
		this._image = image; 
	}
	
	public Image getImageAsset() { 
		return this._image; 
	}
	
	public double getImageWidth() { return this._image.getWidth(); }
	
	public double getImageHeight() { return this._image.getHeight(); }
}
