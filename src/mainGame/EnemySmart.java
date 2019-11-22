package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 * Documentation 11/20/19
 *
 */

public class EnemySmart extends GameObject {

	private Handler handler;
	private GameObject player;
	private int speed;
	private Image img = null;

	/**
	 * This method creates the enemy and how fast it is.
	 */
	public EnemySmart(double x, double y, int speed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speed = speed;
		this.checkForImage();

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}

	}

	/**
	 * This method looks to see if there is an image for the enemy.
	 */
	private void checkForImage() {
		Image image = this.handler.getEnemyImage();
		if (image != null) {
			this.img = image;
		}
	}

	/**
	 * Constantly ticking (used for updating smoothly). Used for updating the
	 * instance variables (DATA) of each entity (location, health,
	 * appearance, etc).
	 */
	public void tick() {
		this.x += velX;
		this.y += velY;
		////////////////////////////// pythagorean theorem
		////////////////////////////// below//////////////////////////////////////////////////
		double diffX = this.x - player.getX() - 16;
		double diffY = this.y - player.getY() - 16;
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		////////////////////////////// pythagorean theorem
		////////////////////////////// above//////////////////////////////////////////////////
		velX = ((this.speed / distance) * diffX); // numerator affects speed of enemy
		velY = ((this.speed / distance) * diffY);// numerator affects speed of enemy

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 40) velY *= -1;
		// if (this.x <= 0 || this.x >= Game.WIDTH - 16) velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.025, this.handler));

	}

	/**
	 * This method creates/draws the enemy smart.
	 */
	public void render(Graphics g) {
		if (this.img != null) {
			g.drawImage(img, (int) this.x, (int) this.y, 20,20, null);
		} else {
			g.setColor(Color.green);
			g.fillRect((int) x, (int) y, (int) Game.scaleX(16), (int) Game.scaleY(16));
		}

	}

	/**
	 * This method creates the rectangle trail of the enemies.
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) Game.scaleX(16), (int) Game.scaleY(16));
	}

	@Override
	public String toString() {
		return id.toString();
	}

}
