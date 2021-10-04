package UF1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Ejercicio1 {
	
	static Path path = Paths.get("D:\\usuarios\\mario\\Documents\\GitHub\\m06-acces-a-dades\\UF1\\El sistema de fitxers\\Actividad2\\foto.jpg" + "");
	static File file = new File("D:\\usuarios\\mario\\Documents\\GitHub\\m06-acces-a-dades\\UF1\\El sistema de fitxers\\Actividad2\\foto.jpg" + "");
	
		public static void splitFile(File f) throws IOException {
		
		int namePartNum = 1;
		int numSplits = 4;
		int fileSize = (int)Files.size(path); // in bytes
		String fileName = file.getName();
		// System.out.println(fileSize + " / " + numSplits + " = " + fileSize/numSplits);
		
		byte[] buffer = new byte[fileSize / numSplits];
		
		try (FileInputStream fis = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(fis)) {
			
			int amountBytes = 0;
			while ((amountBytes = bis.read(buffer)) > 0) {
				String nameOfPart = String.format("%s.%03d", fileName, namePartNum++);
				File newFile = new File(file.getParent(), nameOfPart);
				try (FileOutputStream out = new FileOutputStream(newFile)) {
					out.write(buffer, 0, amountBytes);
				}
			}
			
		}
		
	}
		
	public static void mergeFiles(List<File> files, File into)
	        throws IOException {
	    try (FileOutputStream fos = new FileOutputStream(into);
	         BufferedOutputStream mergingStream = new BufferedOutputStream(fos)) {
	        for (File f : files) {
	            Files.copy(f.toPath(), mergingStream);
	        }
	    }
	}
	
	public static void main(String[] args) throws IOException {
		splitFile(file);
	}
}