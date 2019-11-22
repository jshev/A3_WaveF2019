package mainGame;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.URL;

import mainGame.Game.STATE;

// Handles pausing during the game.

public class PauseMenu {
	private Game game;
	private Handler handler;
	private HUD hud;
	private int timer;
	private int tempCounter;
	private LevelText pausePrompt;
	private LevelText ReturntoMenuPrompt;
	public PauseMenu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		tempCounter = 0;
		//Description when paused
		pausePrompt = new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 200, "Press P to resume",
				ID.Levels1to10Text);
		ReturntoMenuPrompt=new LevelText(Game.WIDTH / 2 - 200, Game.HEIGHT / 2 + 100, "Press H To Return To Menu",
				ID.Levels1to10Text);

	}

	public void tick() {
		timer--;
		if (timer <= 0) {
			handler.object.clear();
			timer = 300;
		}
		handler.tick();
	}

	//When screen of pause menu shows up
	public void render(Graphics g) {
		if (game.gameState==STATE.PauseMenu) {
			if (tempCounter < 1) {
				handler.addObject(pausePrompt);
				handler.addObject(ReturntoMenuPrompt);
				tempCounter++;
			}
			handler.render(g);
		}
	}
	
	//To resume or go back to the menu
	public void removePrompt() {
		handler.removeObject(pausePrompt);
		handler.removeObject(ReturntoMenuPrompt);
		tempCounter=0;
		
	}
	

}
