package game;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.App;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainGameScene extends GameScene {
	// Assets
	public Image background;
	public Image friar;
	public Image character;
	public Image bullet;

	private Canvas canvas;
	private GraphicsContext gc;
	
	private CharacterController ctrl;
	private Sprite laya;

	private Pattern pattern = Pattern.compile("^([^cfjqvxzaeiou\s]|ng)?[aeiou]?$");
	private Matcher matcher;
	private StringBuilder buffer;
	private StageManager manager;
	
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> deadEnemies;
	private ArrayList<Projectile> deadOrbs;
	
	private double mouse_x, mouse_y;
	
	private SimpleBooleanProperty loop,
			upPressed = new SimpleBooleanProperty(false),
			downPressed = new SimpleBooleanProperty(false),
			leftPressed = new SimpleBooleanProperty(false),
			rightPressed = new SimpleBooleanProperty(false),
			isInputMode = new SimpleBooleanProperty(false),
			isValid = new SimpleBooleanProperty(false);

	private Font font;
	
	// Widgets
	
	HBox statBar;
	Label tv_karunungan, tv_diwa, tv_buhay;

	public MainGameScene(StageManager manager) {
		root = new StackPane();
		statBar = new HBox();
		
		canvas = new Canvas(App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
		tv_buhay = new Label("Buhay: 3");
		tv_diwa = new Label("OOOOOOOOOOO");
		tv_karunungan = new Label("XP: 0");
		
		tv_buhay.getStyleClass().add("stat-display");
		tv_diwa.getStyleClass().add("stat-display");
		tv_karunungan.getStyleClass().add("stat-display");
		
		statBar.setSpacing(40);
		statBar.getChildren().addAll(tv_buhay, tv_diwa, tv_karunungan);
		root.getChildren().addAll(canvas, statBar);
		
		// Load images assets and fonts
		this.background = new Image(getClass().getResource("bg.png").toString());
		this.character = new Image(getClass().getResource("running.gif").toString());
		this.friar = new Image(getClass().getResource("friar.png").toString());
		this.bullet = new Image(getClass().getResource("orb_empty.png").toString());
		
		this.font = Font.loadFont(getClass().getResource("bagwis.ttf").toExternalForm(), 50);
		
		// Initialize helpers
		this.ctrl = new CharacterController(character);
		this.ctrl.setCharImage(character);
		this.gc = canvas.getGraphicsContext2D();
		this.buffer = new StringBuilder();
		this.matcher = this.pattern.matcher(this.buffer);
		this.manager = manager;
		gc.setFont(font);
		
		// Initiailize placeholders
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = new ArrayList<Enemy>();
		this.deadEnemies = new ArrayList<Enemy>();
		this.deadOrbs = new ArrayList<Projectile>();
		Enemy.setGameScene(this);

		init();
	}
	
	private void init() {
		addComponent(ctrl.getChar());
		
		// Initialize keyboard and mouse input listeners
		manager.getScene().setOnKeyPressed((e) -> {
			KeyCode code = e.getCode();
			
			if (code.equals(KeyCode.UP) && !upPressed.get()) {
				upPressed.set(true);
			}
			else if (code.equals(KeyCode.DOWN) && !downPressed.get()) {
				downPressed.set(true);
			}
			else if (code.equals(KeyCode.LEFT) && !leftPressed.get()) {
				leftPressed.set(true);
			}
			else if (code.equals(KeyCode.RIGHT) && rightPressed.get()) {
				rightPressed.set(true);
			} else if (code.equals(KeyCode.TAB)) {
				enemies.add(Enemy.spawnRandom());
			} else if (code.equals(KeyCode.BACK_SPACE)) {
				isValid.set(false);
				buffer.delete(0, buffer.length());
			} else if (code.equals(KeyCode.ESCAPE)) {
			} 
		});
		
		manager.getScene().setOnKeyReleased((e) -> {
			KeyCode code = e.getCode();
			
			switch (code) {
				case UP:
					upPressed.set(false);
					break;
				case DOWN:
					downPressed.set(false);
					break;
				case LEFT:
					leftPressed.set(false);
					break;
				case RIGHT:
					rightPressed.set(false);
					break;
				default:
					break;
			}
		});
		
		manager.getScene().addEventHandler(MouseEvent.ANY, (MouseEvent e) -> {
			mouse_x = e.getSceneX();
			mouse_y = e.getSceneY();
	    });
		
		manager.getScene().setOnMouseClicked((e) -> {
			isInputMode.set(false);
			this.matcher = this.pattern.matcher(this.buffer);
			isValid.set(matcher.find() && !buffer.isEmpty());
			
			//System.out.printf("\"%s\" : %s\n", this.buffer.toString(), (isValid.get()) ? "VALID" : "INVALID");
			
			if (isValid.get()) {
				spawnOrb();
			} else {
				buffer.delete(0, buffer.length());
			}
		});
		
		manager.getScene().setOnKeyTyped((k)->{
			try {
				System.out.println("Key!");
				isInputMode.set(true);
				
				if (Character.isAlphabetic(k.getCharacter().charAt(0))) {
					this.buffer.append(k.getCharacter());
					if (this.buffer.toString().equals("ng")) 
						this.buffer.replace(0, 2, "N");
				} 
				
//				System.out.printf("%s\n", this.buffer.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public void spawnOrb() {
		Projectile orb = new Projectile(ctrl.getChar().getX() + ctrl.getWidth()/2, ctrl.getChar().getY() + ctrl.getHeight()/2, 
				bullet, this, this.buffer.toString());
		projectiles.add(orb);
	}
	
	@Override
	public void update() {
		ctrl.setPos(mouse_x - ctrl.getWidth() / 2, mouse_y - ctrl.getHeight() / 2);
		
		for (Projectile p : this.projectiles) {
			for (Enemy f : this.enemies) {
				if(Projectile.checkCollision(p, f)) {
					//f.kill();
					if (p.isAlive) { 
						boolean killEnemy = f.damage(p.getSymbol());
						if (killEnemy) {
							deadEnemies.add(f);
							ctrl.gainKarunungan(500);
						}
						p.isAlive = false;
					}
					deadOrbs.add(p);
				}
			}
		}
		
		enemies.removeAll(deadEnemies);
		projectiles.removeAll(deadOrbs);
		
		tv_karunungan.setText(String.format("XP: %d", ctrl.getKarunungan()));
	}

	@Override
	public void draw() {
		// Clear canvas for redraw
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		// Anti-alias or anisotropic filtering?
		gc.setImageSmoothing(true);
		
		// Draw background
		gc.drawImage(this.background, 0, 0, App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
		
		// Draw Laya
		ctrl.render(gc);
		
		// Draw typed string
		gc.fillText(this.buffer.toString(), 16, App.WINDOW_HEIGHT - 100);
		
		// Draw enemies
		for (Enemy e : getEnemies()) {
			if (e.isAlive()) e.render(gc);
			else this.enemies.remove(e);
		}
		
		// Draw projectiles
		for (Projectile p : this.projectiles) {
			p.render(gc);
		}
		
		// Draw the rest
		for (Renderable r : getComponents()) {
			r.render(gc);
		}
	}
	
	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public void kill(Enemy e) {
		this.enemies.remove(e);
	}
}
