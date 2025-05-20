package game;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

abstract public class GameScene {
	public static final int WINDOW_WIDTH = 1080;
	public static final int WINDOW_HEIGHT = 720;
	
	private static Scene scene;
	protected StackPane root;
	private ArrayList<Renderable> components;
	
	public GameScene() {
		this.components = new ArrayList<Renderable>();
	}
	
	public GameScene(Scene s, Canvas c) {
		this.components = new ArrayList<Renderable>();
	}
	
	abstract public void draw();

	abstract public void update();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addEventHandler(EventType event, EventHandler<?> f) {
		scene.addEventHandler(event, f);
	}
	
	
	public Renderable addComponent(Renderable r) {
		this.components.add(r);
		return r;
	}
	
	public void removeComponent(Renderable r) {
		this.components.remove(r);
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public StackPane getRoot() {
		return root;
	}
	
	public ArrayList<Renderable> getComponents() {
		return this.components;
	}
}
