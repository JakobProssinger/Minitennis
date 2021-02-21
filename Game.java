
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	int speed = 2;
	int basespeed = speed;
	static int score = 0;
	int ya_pause, xa_pause;
	static int posX;
	static int posY;
	static int pressed = 0;

	static int highscore = Highscore.getHighscore();
	static boolean menu = true; // Menu is aktiv or not

	static int game_width = 300;
	static int game_height = 450;
	static int time = 0;

	public Game() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(final MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON1) {
					pressed = 1;
					posX = e.getX();
					posY = e.getY();

				}
			}
		});
	}

	private void move() {
		ball.move();
		racquet.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Color Color_Pause = new Color(102, 51, 0);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (menu == false) {
			ball.paint(g2d);
			racquet.paint(g2d);

			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Verdana", Font.BOLD, (game_height * 7) / 100));

			// Score Posision
			g2d.drawString(String.valueOf(ball.getScore()), (game_width * 3) / 100, (game_height * 7) / 100);
			// Highscore Position
			g2d.drawString(String.valueOf("Highscore: " + Highscore.getHighscore()), game_width / 10, 400);

			if (Racquet.getPause() == true) {
				g2d.setColor(Color_Pause);
				g2d.drawString("Pause", game_width - ((game_width * 42) / 100), (game_height * 7) / 100);

			}
		} else if (menu == true) {
			// game-manu title
			g2d.setFont(new Font("Verdana", Font.BOLD, (game_height * 7) / 100));
			g2d.drawString("Menü", game_width / 2 - 50, game_height / 2);

		}

		System.out.println(posX + "    " + posY + "      " + pressed);
		pressed = 0;
	}

	public void gameOver() {

		ball.xa = 0;
		ball.ya = 0;
		String[] options = { "retry", "exit" };

		int x = JOptionPane.showOptionDialog(null, "Menu", "Click a button", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		if (x == 0) {
			ball.reset();
			Racquet.reset();
			score = 0;
		} else if (x == 1) {
			System.exit(ABORT);
		}

	}

	public void gamePause(boolean pause) {
		if (pause == false) {
			xa_pause = ball.xa;
			ya_pause = ball.ya;
			ball.xa = 0;
			ball.ya = 0;

			Racquet.pause = true;

		} else if (pause == true) {

			ball.xa = xa_pause;
			ball.ya = ya_pause;
			Racquet.pause = false;

		}

	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();

		final Color background = new Color(255, 204, 51);

		frame.add(game);
		frame.setSize(game_width, game_height);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			game.setBackground(background);

			while (menu == false) {
				game.move();
				game.repaint();

				if (score > highscore) {
					Highscore.outHighscore(score);
					highscore = Highscore.getHighscore();
				}
				Thread.sleep(10);

			}
			while (menu == true) {

				game.setBackground(Color.white);
				game.repaint();

				if (posY >= 300 && pressed == 1) {

					menu = false;
				}

				Thread.sleep(10);

			}
		}
	}

}