package mainGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * TO BE IMPLEMENTED - adds health to the player when they move over it
 * @author Brandon Loehle
 * 5/30/16
 * Documentation 11/19/19
 *
 */

public class PickupHealth extends Pickup{
	
	private Handler handler;

	public PickupHealth(double x, double y, ID id, String path, Handler handler) {
		super(x, y, id, path);
		this.handler = handler;
	}


	public void tick() {

		
	}


	//Image for health on the screen
	public void render(Graphics g) {
		g.drawImage(this.img, (int)this.x, (int)this.y, 16, 16, null);
		
	}

	//Area where health can be picked up
	public Rectangle getBounds() {

		return null;
	}

}
