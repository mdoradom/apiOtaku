package Ejercicio1;

// METER PARENT + RESOLV PARA Q PILLE MAS DIRECTORIOS

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Exercici2_Treediff {

	static boolean findFile;
	
	public static void main(String[] args) throws IOException {
		  
		
		Path path1 = Paths.get("/home/users/inf/wiam2/iam47577708/m06-acces-a-dades/UF1/El sistema de fitxers/Ejercicio 2/dirA" + "");
		Path path2 = Paths.get("/home/users/inf/wiam2/iam47577708/m06-acces-a-dades/UF1/El sistema de fitxers/Ejercicio 2/dirB" + "");
		
		Scanner sc = new Scanner(System.in);
		
		/*System.out.println("Dir A:");
		String tmpPath1 = sc.next();
		Path path1 = Paths.get(tmpPath1);
		System.out.println("Dir B:");
		String tmpPath2 = sc.next();
		Path path2 = Paths.get(tmpPath2);*/
		
		//System.out.println(path1 + "\n" + path2);
		
		
		Files.walk(path1)
			.filter(Files::isRegularFile)
			.forEach(file1 -> {
				try {
					findFile = false;
					byte[] hash1 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(file1));
					Path name1 = path1.relativize(file1);
					
					//System.out.println("COSAS DE 1\n" + file1 + "\n" + hash1 + "\n" + name1);
					
					Files.walk(path2)
					.filter(Files::isRegularFile)
					.forEach(file2 -> {
						try {
							
							byte[] hash2 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(file2));
							Path name2 = path2.relativize(file2);
							
							if (Arrays.equals(hash1, hash2) && name1.equals(name2)) {
								System.out.println(file1.getFileName() + " está en la misma ruta que " + file2.getFileName() + " del directorio " + file2.getParent());
								findFile = true;
							}
							
							if (Arrays.equals(hash1, hash2) && !name1.equals(name2)) {
								System.out.println(file1.getFileName() + " está renombrado como " + file2.getFileName() + " en el directorio " + file2.getParent());
								findFile = true;
							}
							
							/*if (!Arrays.equals(hash1, hash2) && !name1.equals(name2)) {
								System.out.println(file1.getFileName() + " no está en el directorio " + file2.getParent());
							}*/

							//System.out.println("COSAS DE 2\n" + file2 + "\n" + hash2 + "\n" + name2);
							
						} catch (NoSuchAlgorithmException | IOException e) {
							e.printStackTrace();
						}

					});
				} catch (NoSuchAlgorithmException | IOException e) {
					e.printStackTrace();
				}
				
				if (!findFile) {
					System.out.println(file1.getFileName() + " no está");
				}
				
				
			});
		
		
		//System.out.println("Ficheros del directorio:\n" + path1 + "\nque no están en:\n" + path2);
		
		//System.out.println("Ficheros del directorio:\n" + path1 + "\nque están en: " + path2 + " pero en otra ruta.");
		
		//System.out.println("Ficheros del directorio:\n" + path2 + "\nque no están en:\n" + path1);
		
		//System.out.println("Ficheros del directorio:\n" + path2 + "\nque están en: " + path1 + " pero en otra ruta.");
		
		//System.out.println("Ficheros que están en a misma ruta en:\n" + path1 + "\nY|n" + path2);
		sc.close();
	}

}


