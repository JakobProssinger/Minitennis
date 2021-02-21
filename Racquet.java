

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private static final int Y = 330;
	private static final int WITH = 60;
	private static final int HEIGHT = 10;
	static int x = 150 - (WITH / 2);
	static int xa = 0;
	static boolean pause = false;

	private Game game;
	int racquetspeed = 5;

	public Racquet(Game game) {
		this.game = game;
	}

	static void reset() {
		x = 150 - (WITH / 2);
		xa = 0;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WITH)
			x = x + xa;
	}

	public static final Color Color_Racquet = new Color(0,153,0);
	
	public void paint(Graphics2D g) {
		g.setColor(Color_Racquet);
		g.fillRect(x, Y, WITH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
			if(pause == false) {
			xa = -racquetspeed;
			}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
			if(pause == false) {
			xa = racquetspeed;
			}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_SPACE) {

		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.gamePause(pause);

		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WITH  , HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT;
	}
	
	public static boolean getPause() {
		return pause;
	}
}