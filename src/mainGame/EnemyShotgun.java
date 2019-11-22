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

public class EnemyShotgun extends GameObject {

	private Handler handler;
	private int sizeX;
	private int sizeY;
	private int timer;
	private GameObject player;
	private double bulletVelX;
	private double bulletVelY;
	private int bulletSpeed;

	/**
	 * This method creates the enemy and how fast the bullet speed is.
	 */
	public EnemyShotgun(double x, double y, int bulletSpeed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.timer = 60;
		this.bulletSpeed = bulletSpeed;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}
	}
	/**
	 * Constantly ticking (used for updating smoothly). Used for updating the
	 * instance variables (DATA) of each entity (location, health,
	 * appearance, etc).
	 */
	public void tick() {
		timer--;
		if (timer <= 0) {
			shoot();
			timer = 100;
		}

	}

	/**
	 * This method creates the path for the bullets to be shot.
	 * It also adds the bullets to the path.
	 */
	public void shoot() {
		double diffX = this.x - player.getX() - Game.scaleX(16);
		double diffY = this.y - player.getY() - Game.scaleX(16);
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		////////////////////////////// pythagorean theorem
		////////////////////////////// above//////////////////////////////////////////////////
		bulletVelX = ((this.bulletSpeed / distance) * diffX); // numerator
																// affects speed
																// of enemy
		bulletVelY = ((this.bulletSpeed / distance) * diffY);// numerator
																// affects speed
																// of enemy

		handler.addObject(
				new EnemyShotgunBullet(this.x, this.y, bulletVelX, bulletVelY, ID.EnemyShotgunBullet, this.handler));
		handler.addObject(new EnemyShotgunBullet(this.x + 20, this.y, bulletVelX, bulletVelY, ID.EnemyShotgunBullet,
				this.handler));
		handler.addObject(new EnemyShotgunBullet(this.x + 40, this.y, bulletVelX, bulletVelY, ID.EnemyShotgunBullet,
				this.handler));
		handler.addObject(new EnemyShotgunBullet(this.x + 60, this.y, bulletVelX, bulletVelY, ID.EnemyShotgunBullet,
				this.handler));
		handler.addObject(new EnemyShotgunBullet(this.x + 80, this.y, bulletVelX, bulletVelY, ID.EnemyShotgunBullet,
				this.handler));
		handler.addObject(new EnemyShotgunBullet(this.x + 100, this.y, bulletVelX, bulletVelY, ID.EnemyShotgunBullet,
				this.handler));
	}

	/**
	 *This method creates/draws the enemy shotgun.
	 */
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, (int) Game.scaleX(100), (int) Game.scaleY(100));

	}

	/**
	 * This method creates the rectangle trail of the enemies.
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) Game.scaleX(100), (int) Game.scaleY(100));
	}

	@Override
	public String toString() {
		return id.toString();
	}

}
