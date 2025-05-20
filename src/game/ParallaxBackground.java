package game;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import application.App;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ParallaxBackground extends Renderable {
	public final static int VERTICAL = 1;
	public final static int HORIZONTAL = 2;
	
	private SortedSet<ParallaxImage> layers;
	
	public ParallaxBackground() {
		layers = new TreeSet<ParallaxImage>();
	}
	
	public void addLayer(Image image, double speed, int zOrder) {
		layers.add(new ParallaxImage(image, speed, zOrder));
		System.out.printf("Added %s\n", image.getUrl());
	}
	
	// Draws the image then translates horizontally depending on movementSpeed
	@Override
	void render(GraphicsContext gc) {
		for (ParallaxImage layer : layers) {
			layer.draw(gc);
			// System.out.printf("Drawing z = %d\n", layer.zOrder);
			layer.translate();
		}
	}

}
