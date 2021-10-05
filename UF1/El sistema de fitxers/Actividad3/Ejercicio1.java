package Actividad3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {
	
	public static void main(String[] args, String $1, int $2) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("top5.txt"));
		
		/*String name = $1;
		int score = $2;*/
		
		String name = "James Gosling";
		int score = 300;
		
		writer.write(name + ":" + score);
		writer.close();
	}
	
}
