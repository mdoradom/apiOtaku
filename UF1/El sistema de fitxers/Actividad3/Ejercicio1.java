package Actividad3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio1 {
	
	public static void main(String[] args) throws IOException {
		Path file = Paths.get("/home/users/inf/wiam2/iam47577708/m06-acces-a-dades/UF1/El sistema de fitxers/Actividad3/top5.txt");
		BufferedWriter bw = Files.newBufferedWriter(file);
		
		/*String name = $1;
		int score = $2;*/
		
		String name = "James Gosling";
		int score = 300;
		
		
		bw.write(name + ":" + score);
		bw.close();
	}
	
}
