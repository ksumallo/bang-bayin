package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextView extends Renderable{
	
 	private double x = 0;
	private double y = 0;
	
	private String content;

	public TextView() {
	}
	
	public TextView(String c) {
		this.x = 0;
		this.y = 0;
		this.content = c;
	}
	
	public TextView(int x, int y, String c) {
		this.x = x;
		this.y = y;
		this.content = c;
	}
	
	public void render(GraphicsContext gc) {
		gc.setFont(Font.font("Times New Roman", 28));
		gc.setFill(Color.BLACK);
		gc.fillText(content, this.x, this.y);
	}
	
	public void setText(String content) {
		this.content = content;
	}
	
	public String getText() {
		return this.content;
	}
}