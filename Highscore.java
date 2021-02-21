import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Highscore {

	
	public static int getHighscore() {
		String line;

		BufferedReader fin_Score;
		Game.highscore = 0;
		
		try {

			fin_Score = new BufferedReader(new FileReader("Highscore.txt"));

			line = fin_Score.readLine();
			Game.highscore = Integer.parseInt(line);
			fin_Score.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return Game.highscore;

	}

	public static void outHighscore(int score) {

		PrintWriter f_newhighscore;

		try {
			f_newhighscore = new PrintWriter(new BufferedWriter(new FileWriter("Highscore.txt")));
			f_newhighscore.print(score);

			f_newhighscore.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
