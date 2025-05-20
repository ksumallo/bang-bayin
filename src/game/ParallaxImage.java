package game;

import application.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class ParallaxImage implements Comparable<ParallaxImage> {
	private Image image;
	
	private int zOrder = 0;
	private int direction = ParallaxBackground.HORIZONTAL; // Either -1 (towards left/down) or 1 (towards right/up)
	private double movementSpeed = 1;
	private double x = 0, 
				   y = 0;
	private boolean reverse = false;
	
	public ParallaxImage (Image i, double s, int z) {
		this.image = i;
		this.movementSpeed = s;
		this.zOrder = z;
	}
	
	protected void draw(GraphicsContext gc) {
		gc.drawImage(this.image, this.x, this.y);
		
		if (this.x != 0) // Draws the same image with an offset, for infinite scrolling
			gc.drawImage(this.image, this.x + gc.getCanvas().getWidth() * ((this.x < 0) ? 1 : -1), this.y);
		else if (this.y != 0)
			gc.drawImage(this.image, this.x, this.y + gc.getCanvas().getHeight() * ((this.y < 0) ? 1 : -1));
	}
	
	protected void draw(GraphicsContext gc, double X, double Y) {
		gc.drawImage(this.image, X, Y);
	}
	
	protected void translate() {
		if (direction == ParallaxBackground.HORIZONTAL)
			this.x = (this.x + (movementSpeed * ((reverse) ? -1 : 1))) % App.WINDOW_WIDTH;
		else if (direction == ParallaxBackground.VERTICAL)
			this.y = (this.y + (movementSpeed * ((reverse) ? -1 : 1))) % App.WINDOW_HEIGHT;
	}
	
	protected double getWidth() {
		return this.image.getWidth();
	}
	
	// Required for using ParallaxImage with SortedSet (and TreeSet)
	@Override
	public int compareTo(ParallaxImage p) {
		if (p.zOrder < this.zOrder) return 1;
		else if (p.zOrder > this.zOrder) return -1;
		else return 0;
	}
}
