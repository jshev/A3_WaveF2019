package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 * Documentation 11/20/19
 *
 */

public class EnemyShooterBullet extends GameObject {

	private Handler handler;

	/**
	 * This method is creating the bullets for the enemy.
	 */
	public EnemyShooterBullet(double x, double y, double velX, double velY, ID id, Handler handler) {
		super(x, y, id);
		this.x = x; // Same as with the trails: ignore scaling of GameObjects.
		this.y = y;
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
	}

	/**
	 * Constantly ticking (used for updating smoothly). Used
	 * for updating the instance variables (DATA) of each entity (location, health,
	 * appearance, etc).
	 */
	public void tick() {
		this.x += velX;
		this.y += velY;

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 40) velY *= -1;
		// if (this.x <= 0 || this.x >= Game.WIDTH - 16) velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 4, 4, 0.025, this.handler));

		removeBullets();
	}

	/**
	 * This method is clearing the bullets from the screen once the enemy shoots
	 * them and they travel to the player.
	 */
	public void removeBullets() {

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.EnemyShooterBullet) {
				if (tempObject.getX() >= Game.WIDTH || tempObject.getX() <= 0) {
					handler.removeObject(tempObject);
				}
				if (tempObject.getY() >= Game.HEIGHT || tempObject.getY() <= 0) {
					handler.removeObject(tempObject);
				}
			}

		}
	}

	/**
	 * This method creates/draws out the bullets for the enemy to use.
	 */
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 4, 4);
	}

	/**
	 * This method creates the rectangle trail of the enemies.
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}
