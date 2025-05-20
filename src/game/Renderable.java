package game;

import javafx.scene.canvas.GraphicsContext;

abstract public class Renderable {
 	protected double x = 0;
 	protected double y = 0;
	protected double width = 0;
	protected double height = 0;

	public Renderable() {
	}
	
	public Renderable(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	abstract void render(GraphicsContext gc);
	
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
	
	public double getX() { return this.x; }
	
	public double getY() { return this.y; }
	
	public double getWidth() { return this.width; }
	
	public double getHeight() { return this.height; }
}
