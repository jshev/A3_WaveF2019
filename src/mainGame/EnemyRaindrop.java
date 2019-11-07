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

public class EnemyRaindrop extends GameObject {

	private Handler handler;
	private GameObject player;
	private Image img = null;

	public EnemyRaindrop(double x, double y, int velX, int velY, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
		this.checkForImage();

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}
		if (((Player) player).getGame().gameState == Game.STATE.Game) {
			this.x = player.getX(); // overrides the position set in the spawn class so that it spawns on top of the player
		}
	}
	
	private void checkForImage() {
		Image image = this.handler.getEnemyImage();
		if (image != null) {
			this.img = image;
		}
	}

	public void tick() {
		this.y += velY;

		if (this.y > Game.HEIGHT) {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		if (this.img != null) {
			g.drawImage(img, (int) this.x-25, (int) this.y-25, 50,50, null);
		} else {
			g.setColor(Color.blue);
			g.fillOval((int) x, (int) y, (int) Game.scaleX(40), (int) Game.scaleY(70));
		}

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) Game.scaleX(40), (int) Game.scaleY(70));
	}

	@Override
	public String toString() {
		return id.toString();
	}

}
