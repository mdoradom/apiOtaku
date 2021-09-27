package Ejercicio1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Exercici1_Flatten {
	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get("/home/users/inf/wiam2/iam47577708/m06-acces-a-dades/UF1/El sistema de fitxers/Ejercicio1/niats" + "");
		
		//System.out.println(path);
		
		Files.walk(path)
			.filter(Files::isRegularFile)
			.forEach(file -> {
				try {
					Files.move(file, path.resolve(file.getFileName()));
					if (!path.equals(file.getParent())) {
						Files.delete(file.getParent());
						//System.out.println(file.getParent());
					};
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		
	}	
}
