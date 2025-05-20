package game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameTick extends AnimationTimer {
	
	StageManager manager;
	private GameScene currentScene;
	
	public GameTick() {
		//this.stage = stage;
		
		//this.stage.setScene(this.currentScene.getScene());
		init();
	}

//	public GameTick(Stage stage, GameScene initScene) {
//		this.stage = stage;
//		this.stage.setScene(this.currentScene.getScene());
//
//		this.currentScene = initScene;
//		
//		init();
//	}
	
	public void init() {
//		prev = System.currentTimeMillis();
//		stopwatch = System.currentTimeMillis();
	}
	
	@Override
	public void handle(long arg0) {
		currentScene.draw();
		currentScene.update();
	}
	
	// FLOOR FRICTION PHYSICS
	
//	private double xAccel = 0, yAccel = 0;
//	private double mem_dir, mem_x, mem_y;
//	private long prev, curr, xstart, ystart, stopwatch;	
//	@Override
//	public void handle(long arg0) {	
//		boolean yPressed = upPressed.get() || downPressed.get(),
//				xPressed = rightPressed.get() || leftPressed.get();
//		
//		if (yPressed) {
//			mem_y = ctrl.getChar().getY();
//			yAccel = ctrl.getAcceleration(); // m/s2		
//			curr = System.currentTimeMillis();
//			ctrl.setYWalkSpeed(ctrl.getYWalkSpeed() + yAccel * (curr - ystart)/1000);
//		} else {
//			if (yAccel > 0) {
//				mem_y = ctrl.getChar().getY() - mem_y;
//				mem_dir = Math.atan2(mem_y, mem_x);
//			}
//			if (ctrl.getYWalkSpeed() > 0) {
//				ctrl.move(0, Math.sin(mem_dir) > 0 ? 1 : Math.sin(mem_dir) < 0 ? -1 : 0);
//				yAccel = -100;
//				prev = curr;
//				curr = System.currentTimeMillis();
//				ctrl.setYWalkSpeed(ctrl.getYWalkSpeed() + yAccel * (curr - prev)/1000);
//				prev = curr;
//			}
//			ystart = System.currentTimeMillis();
//		}
//		
//		if (xPressed) {
//			ctrl.setCharImage((rightPressed.get()) ? image : flipped);
//			mem_x = ctrl.getChar().getX();
//			xAccel = ctrl.getAcceleration(); 
//			curr = System.currentTimeMillis();
//			ctrl.setXWalkSpeed(ctrl.getXWalkSpeed() + xAccel * (curr - xstart)/1000);
//		} else {
//			if (xAccel > 0) {
//				mem_x = ctrl.getChar().getX() - mem_x;
//				mem_dir = Math.atan2(mem_y, mem_x);
//			} if (ctrl.getXWalkSpeed() > 0) {
//				ctrl.move(Math.cos(mem_dir) > 0 ? 1 : Math.cos(mem_dir) < 0 ? -1 : 0, 0);
//				xAccel = -100;
//				prev = curr;
//				curr = System.currentTimeMillis();
//				ctrl.setXWalkSpeed(ctrl.getXWalkSpeed() + xAccel * (curr - prev)/1000);
//				prev = curr;
//			}
//			xstart = System.currentTimeMillis();
//		}
//		
//		if ((int)(ctrl.getXWalkSpeed() + ctrl.getYWalkSpeed()) == 0) ctrl.setCharImage(idle);
//		
//		if ((System.currentTimeMillis() - stopwatch) > 100) {
//			System.out.printf("v_x = %.5f, v_y = %.5f\n", ctrl.getXWalkSpeed(), ctrl.getYWalkSpeed());
//			stopwatch = System.currentTimeMillis();
//		}
//		
//		if (rightPressed.get() ^ leftPressed.get())
//			ctrl.move((rightPressed.get()) ? 1 : -1, 0);
//		if (upPressed.get() ^ downPressed.get())
//			ctrl.move(0, (downPressed.get()) ? 1 : -1);
//		
//		currentScene.redraw();
//	}
	
	public void setScene(GameScene gameScene) {
		currentScene = gameScene;
		//stage.setScene(currentScene.getScene());
	}
}
