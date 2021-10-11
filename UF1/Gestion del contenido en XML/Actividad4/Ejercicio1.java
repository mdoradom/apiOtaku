package Actividad4;

import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;

class Score {
	String name;
	int points;
	
	public Score(String name, int points) {
		super();
		this.name = name;
		this.points = points;
	}
	
	
}

public class Ejercicio1 {
	public static void main (String[] args) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Score score = new Score("asd", 123);
		objectMapper.writeValue(Paths.get("scores.json").toString());
	} 
}
	
