package Actividad1;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class Ejercicio3 {
	public static void main(String[] args) throws IOException {
	Path path = Paths.get("/home/users/inf/wiam2/iam47577708/Documentos" + "");
	//System.out.println(path);
	Files.walk(path)
	.filter(Files::isRegularFile)
	.forEach(file -> {
		try {
			Files.move(file, path.resolve(file.getFileName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	});

	Files.walk(path)
	.filter(Files::isDirectory)
	.sorted(Collections.reverseOrder())
	.forEach(directory -> {
		try {
			if (!path.equals(directory)){
				Files.delete(directory);
			}
			
		} catch (DirectoryNotEmptyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});
	Files.walk(path)
		.filter(Files::isRegularFile)
		.forEach(file -> {
			try {
				LocalDateTime time = LocalDateTime.parse(Files.getLastModifiedTime(file).toString(), DateTimeFormatter.ISO_DATE_TIME);
				
				//if(!Files.exists(path.resolve(Integer.toString(time.getYear())).resolve(Integer.toString(time.getMonthValue())).resolve(Integer.toString(time.getDayOfMonth()+1)))) {
					Files.createDirectories(path.resolve(Integer.toString(time.getYear())).resolve(Integer.toString(time.getMonthValue())).resolve(Integer.toString(time.getDayOfMonth()+1)));
				//}
				
				Files.move(file, path.resolve(Integer.toString(time.getYear())).resolve(Integer.toString(time.getMonthValue())).resolve(Integer.toString(time.getDayOfMonth()+1)).resolve(file.getFileName()));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
