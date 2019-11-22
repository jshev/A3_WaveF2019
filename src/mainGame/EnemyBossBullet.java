package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * The bullets that the first boss shoots
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class EnemyBossBullet extends GameObject {

	private Handler handler;
	Random r = new Random();
	private int max = 15;
	private int min = -15;
//sets the location and where the bullets spawn
	public EnemyBossBullet(double x, double y, ID id, Handler handler) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.handler = handler;
		velX = (r.nextInt((max - min) + 1) + min);// OFFICIAL WAY TO GET A RANGE FOR randInt()
		velY = 30;
	}

	public void tick() {
		this.x += velX;
		this.y += velY;

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 40) velY *= -1;
		// if (this.x <= 0 || this.x >= Game.WIDTH - 16) velX *= -1;

		if (this.y >= Game.HEIGHT)
			handler.removeObject(this);

		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.025, this.handler));

	}
// Makes the bullets on the screen appear
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 16, 16);
	}
//gets the location of the bullets
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}
