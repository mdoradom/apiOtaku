package Actividad3;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Ejercicio1 {

	public static ArrayList<Score> puntuaciones = new ArrayList<>();
	public static Path file = Paths.get("D:\\usuarios\\mario\\Documents\\GitHub\\m06-acces-a-dades\\UF1\\El sistema de fitxers\\Actividad3\\top5.txt");
	//public static Path file = Paths.get("/home/users/inf/wiam2/iam47577708/m06-acces-a-dades/UF1/El sistema de fitxers/Actividad3/top5.txt");

	public static void main(String[] args) throws IOException, IndexOutOfBoundsException {

		score(args[0], args[1], file);

														// IMPORTANTE: si se quiere probar a crear nuevos Scores dentro
														// del archivo, hay que hacerlo de 1 en 1, si le pasas varios en
														// la misma ejecución no funionará como debe.
														// Descomentar de 1 en 1 para probar:
		//score("James Gosling", "300", file); 			// 1
		//score("Anders Hejlsberg", "500", file); 		// 2
		//score("Chris Lattner", "400", file); 			// 3
		//score("Brendan Eich", "200", file); 			// 4
		//score("Bjarne Stroustrup", "600", file); 		// 5
		//score("Guido van Rossum", "100", file); 		// 6
	}

	public static void score(String name, String points, Path path) throws IOException {

		if (!Files.exists(path)) {
			Files.createFile(path);
		}

		List<String> lines = Files.lines(path).collect(Collectors.toList());

		for (String line : lines) {
			String[] lineaTmp = line.split(":");
			puntuaciones.add(new Score(lineaTmp[0], lineaTmp[1]));
		}

		puntuaciones.add(new Score(name, points));

		for (int i = 0; i <= puntuaciones.size(); i++) {
			for (int j = 0; j < puntuaciones.size()-i-1; j++) {

				if (puntuaciones.get(j).compareTo(puntuaciones.get(j+1)) < 0) {
					Score temp = puntuaciones.get(j);
					puntuaciones.set(j, puntuaciones.get(j+1));
					puntuaciones.set(j+1, temp);
				}
			}
		}

		BufferedWriter bw = Files.newBufferedWriter(path);

		if (puntuaciones.size() >= 5) {
			for (int i = 0; i < 5; i++) {
				bw.write(puntuaciones.get(i).nombre + ":" + puntuaciones.get(i).puntuacion);
				bw.newLine();
			}
		} else {
			for (Score puntuaciones : puntuaciones) {
				bw.write(puntuaciones.nombre + ":" + puntuaciones.puntuacion);
				bw.newLine();
			}
		}

		bw.close();
	}

}

class Score {
	public String nombre;
	public int puntuacion;

	public Score(String nombre, String puntuacion) {
		this.nombre = nombre;
		this.puntuacion = Integer.parseInt(puntuacion);
	}

	public int compareTo(Score s) {
		int res = 0;
		if (puntuacion < s.puntuacion) {res = -1;}
		if (puntuacion > s.puntuacion) {res = 1;}
		return res;
	}
}
