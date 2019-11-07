package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class EnemySweep extends GameObject {

	private Handler handler;
	private Image img = null;

	public EnemySweep(double x, double y, double velX, double velY, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
		this.checkForImage();
	}
	
	private void checkForImage() {
		Image image = this.handler.getEnemyImage();
		if (image != null) {
			this.img = image;
		}
	}

	public void tick() {
		this.x += velX;
		this.y += velY;

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 43) velY *= -1;
		//Remove the object when it goes off the top/bottom of the screen.
		if (this.y <= Game.scaleY(90) || this.y >= (Game.scaleY(1080) - 60)){	
			handler.removeObject(this);
		}
		if (this.x <= 0 || this.x >= (Game.scaleX(1920) - 40)){
			velX *= -1;
		}/*
		if (this.y >= Game.HEIGHT) { 
										// removes the object from the array list
										// when it goes off the top of the
										// screen
			handler.removeObject(this);
		}
		if (this.y <= 0) { // removes the object from the array list when it
							// goes off the bottom of the screen
			handler.removeObject(this);
		}*/

		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.025, this.handler));
	
	}

	public void render(Graphics g) {
		if (this.img != null) {
			g.clearRect((int) x, (int) y, (int) Game.scaleX(16), (int) Game.scaleY(16));
			g.drawImage(img, (int) this.x, (int) this.y, 30,30, null);
		} else {
			g.setColor(Color.cyan);
			g.fillRect((int) x, (int) y, (int) Game.scaleX(16), (int) Game.scaleY(16));
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) Game.scaleX(16), (int) Game.scaleY(16));
	}
	
	@Override
	public String toString() {
		return id.toString();
	}

}
