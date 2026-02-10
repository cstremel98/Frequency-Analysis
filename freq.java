import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Frequency_Analysis {
	public static void main(String[] args) throws IOException {
		HashMap<Character, Double> map = new HashMap<>();
		File inFile = new File("ciphertext.txt");
		String line = "";
		try {
			Scanner sc = new Scanner(inFile);
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				map = freq(map, line);
			}
			System.out.println(map);
			sc.close();
		} catch (IOException ex) {
			System.out.println("Input file not found");
		} 
		
		HashMap<Character, Double> freqMap = new HashMap<>();
		List<String> rows = Files.readAllLines(Paths.get("table.csv"));
		int total = 0;
		for (String row : rows) {
			String [] split = row.split(":");
			char letter = split[0].charAt(0);
			total += map.get(letter);
			double frequency = Double.parseDouble(split[1]);
			freqMap.put(letter, frequency);
		}

		System.out.println(freqMap);
		System.out.println(total);

		for (Character key : map.keySet()) {
			System.out.println("Key: " + key);
		}
		for (Double value : map.values()) {
			value = value / total;
			Double preciseValue = BigDecimal.valueOf(value)
				.setScale(4, RoundingMode.HALF_UP)
				.doubleValue();
			System.out.println("Value: " + preciseValue);
		}



		

		
		
		/*File outFile = new File("output.txt");
		try {
			FileWriter fw = new FileWriter(outFile); 
			fw.write(line);
			fw.close();
		} catch (IOException ex) {
			System.out.println("Output file not found");
		}*/

	}

	public static HashMap<Character, Double> freq(HashMap<Character,Double> map, String line) {
		//System.out.println(map);
		for(int i=0; i<line.length(); i++) {
			char c = line.charAt(i);
			double val = 0;
			if(map.get(c) != null) {
				val = map.get(c);
			} else {
				val = 0;
			}

			if(line.charAt(i) == ' ') {
			
			} else {
				map.put(c, val+1);
			}
		}
		return map;
	}
}
