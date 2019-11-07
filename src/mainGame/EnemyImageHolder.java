package mainGame;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class EnemyImageHolder {
	
	private int imgNum;
	private Image img;	
	private Handler handler;

	public EnemyImageHolder(Handler handler) {
		this.handler = handler;
	}
	
	public void updateImg(int num) {
		if(num!=0) {
			String newURL;
			if(num==1) {
				newURL = "images/boo.png";
			} else if(num==2) {
				newURL = "images/spiny.png";	
			} else if(num==3) {
				newURL = "images/goomba.png";
			} else {
				return; //Add more customization options here
			}
			img = getImage(newURL);
			this.handler.setEnemyImage(img);
		}
	}
	
	
	public Image getImage(String path) {
		Image image = null;
		try {
			URL imageURL = Game.class.getResource(path);
			image = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return image;
	}
	
	

}
