import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Frequency_Analysis {
	public static void main(String[] args) throws IOException {
		HashMap<Character, Integer> map = new HashMap<>();
		File inFile = new File("ciphertext.txt");
		String line = "";
		try {
			Scanner sc = new Scanner(inFile);
			line = sc.nextLine();
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				map = freq(map, line);
			}
			System.out.println(map);
			sc.close();
		} catch (IOException ex) {
			System.out.println("Input file not found");
		} 
		
		File outFile = new File("output.txt");
		try {
			FileWriter fw = new FileWriter(outFile); 
			fw.write(line);
			fw.close();
		} catch (IOException ex) {
			System.out.println("Output file not found");
		}

	}

	public static HashMap<Character, Integer> freq(HashMap<Character,Integer> map, String line) {
		//System.out.println(map);
		for(int i=0; i<line.length(); i++) {
			char c = line.charAt(i);
			int val = 0;
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
