package Actividad2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio1 {
	
	// static Path file = Paths.get("D:\\usuarios\\mario\\Documents\\GitHub\\m06-acces-a-dades\\UF1\\El sistema de fitxers\\Actividad2\\foto.jpg" + "");

	static Path file = Paths.get("/home/users/inf/wiam2/iam47577708/m06-cces-a-dades/UF1/El sistema de fitxers/Actividad2"); // /foto.jpg
	
	public static void splitFile(String f, int numSplits) throws IOException {

		Path infile = Paths.get(f);
		
		try (InputStream is = Files.newInputStream(infile)) {
			
			int i = 1;
			Path outfile = Paths.get(f + ".part" + i);
			
			OutputStream os = Files.newOutputStream(outfile);
			
			int partSize = (int) Math.ceil(is.available() / numSplits);
			
			int amountBytes = 0;
			int byteLeido;
			while ((byteLeido = is.read()) != -1) {
				
				if (amountBytes == partSize && ((int) Math.ceil(is.available() / numSplits)) != 0) {
					i++;
					outfile = Paths.get(f + ".part" + i);
					os = Files.newOutputStream(outfile);
					amountBytes = 0;
				}
				amountBytes++;
				os.write(byteLeido);
			}
			
		}
		
	}
		
		
	public static void joinFile(String file) throws IOException {
		
		Path path1 = Paths.get(file);
		OutputStream os = Files.newOutputStream(path1);
		
		for (int i=1; ;i++) {
			String part = file + ".part" + i;
			File fileTmp = new File(part);
			
			if (fileTmp.exists()) {
				Path path2 = Paths.get(part);
				os.write(Files.readAllBytes(path2));
			} else {
				break;
			}

		}
		
	}
		
	public static void main(String[] args) throws IOException {
		splitFile("/home/users/inf/wiam2/iam47577708/m06-acces-a-dades/UF1/El sistema de fitxers/Actividad2/sample.txt", 4);
		joinFile("/home/users/inf/wiam2/iam47577708/m06-acces-a-dades/UF1/El sistema de fitxers/Actividad2/sample.txt");
	}
	
}


