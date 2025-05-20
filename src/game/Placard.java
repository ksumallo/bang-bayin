package game;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Placard extends Renderable {
	
	private static Font font;
	
	private double padding = 0,
			textWidth = 0, 
			textHeight = 0;
	
	private Text text;
	private Enemy parent;
	private Queue<String> queue;

	public Placard(Enemy p, String s) {
		// Initialize symbol queue
		queue = new LinkedList<String>();
		for (String syllable : s.split("/")) 
			queue.add(syllable);

		// Initialize placard display
		font = Font.loadFont(getClass().getResource("bagwis.ttf").toExternalForm(), 50);	
		
		this.parent = p;
		
		this.text = new Text(s.replace("/", ""));
		this.text.setFont(font);
		this.text.setWrappingWidth(0);
		this.text.setLineSpacing(0);
		
		this.textWidth = this.text.getLayoutBounds().getWidth();
		this.textHeight = this.text.getLayoutBounds().getHeight();
		
		this.width = textWidth + padding * 2;
		this.height = textHeight + padding * 2;
		
		this.x = (this.parent.x + this.parent.getWidth()/2) - this.textWidth/2;
		this.y = (this.parent.y + this.parent.getHeight()/3); // this.parent.getY();// + this.parent.getHeight()/2) - this.height/2; //(this.parent.y + this.parent.getHeight()/2) - this.height/2;
	}

	@Override
	void render(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(this.x, this.y, this.textWidth, this.textHeight);
		
		gc.setFill(Color.BLACK);
		gc.fillText(this.text.getText(), this.x, this.y + this.textHeight - 10);
	}
	
	public boolean attack(String s) {
		if (queue.peek().equals(s)) {
			for (String q : queue) {
				System.out.printf("Print: %s\n", q);
			}
			queue.remove();
			this.recalculate();
		}
		
		// If the placard is now empty, kill the entity
		if (this.text.getText().isEmpty()) return true;
		
		return false;
	}
	
	public void recalculate() {
		StringBuilder builder = new StringBuilder();
		for (String q : queue) {
			builder.append(q);
			System.out.printf("Append: %s\n", q);
		}
		
		this.text.setText(builder.toString());
		this.textWidth = this.text.getLayoutBounds().getWidth();
		this.textHeight = this.text.getLayoutBounds().getHeight();
		
		this.width = textWidth + padding * 2;
		this.height = textHeight + padding * 2;
		
		this.x = (this.parent.x + this.parent.getWidth()/2) - this.textWidth/2;
		this.y = (this.parent.y + this.parent.getHeight()/3);
	}
}
