
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;

	int x = (int) (Math.random() * 250 + 20);
	int y = 0;
	int maxspeed = 15;
	int xa = 2;
	int ya = 2;
	int xa_start = xa;
	int ya_start = ya;
	private Game game;
	int score = 0;
	int speedvalues[] = { 2, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 10 };
	int counter = 0;

	static int red = (int) (Math.random() * 255 + 1);
	static int green = (int) (Math.random() * 255 + 1);
	static int blue = (int) (Math.random() * 255 + 1);

	public static Color Color_ball = new Color(red, green, blue);

	public Ball(Game game) {
		this.game = game;

	}

	void reset() {
		colorchange();
		x = (int) (Math.random() * 250 + 20);
		y = 0;
		xa = xa_start;
		ya = ya_start;
		score = 0;
		game.speed = 2;
		counter = 0;
		game.highscore = Highscore.getHighscore();
	}

	void move() {
		boolean changeDirection = true;

		if (x + xa < 0)
			xa = game.speed;

		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.speed;

		else if (y + ya < 0)
			ya = game.speed;

		else if (y > game.getHeight() - 50 - DIAMETER)
			game.gameOver();

		else if (collision()) {
			ya = -game.speed;
			y = game.racquet.getTopY() - DIAMETER;

			if (counter < speedvalues.length) {
				game.speed = speedvalues[counter];
			}

			else if (game.speed >= 10 && game.speed < maxspeed) {

				game.speed++;

			}
			game.score++;
			counter++;

		} else
			changeDirection = true;

		if (changeDirection) {
			x = x + xa;
			y = y + ya;
		}
	}

	public int getScore() {

		return game.score;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());

	}

	public void paint(Graphics2D g) {

		if (collision()) {
			colorchange();

		}

		g.setColor(Color_ball);
		g.fillOval(x, y, DIAMETER, DIAMETER);

	}

	public void colorchange() {
		red = (int) (Math.random() * 255 + 1);
		green = (int) (Math.random() * 255 + 1);
		blue = (int) (Math.random() * 255 + 1);
		Color_ball = new Color(red, blue, green);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);

	}
}