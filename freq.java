import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.StringBuilder;
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
			sc.close();
		} catch (IOException ex) {
			System.out.println("Input file not found");
		} 
		
		HashMap<Character, Double> freqMap = new HashMap<>();
		List<String> rows = Files.readAllLines(Paths.get("table.csv"));
		double total = 0;
		for (String row : rows) {
			String [] split = row.split(":");
			char letter = split[0].charAt(0);
			total += map.get(letter);
			double frequency = Double.parseDouble(split[1]);
			freqMap.put(letter, frequency);
		}

		System.out.println(freqMap);

		for (Map.Entry<Character, Double> entry : map.entrySet()) {
			double valueAsFrequency = entry.getValue() / total;
			Double preciseValue = BigDecimal.valueOf(valueAsFrequency)
				.setScale(4, RoundingMode.HALF_UP)
				.doubleValue();
			entry.setValue(preciseValue);
		}
		System.out.println(map);
		System.out.println();

		List<Map.Entry<Character, Double>> list = new ArrayList<>(map.entrySet());
		List<Map.Entry<Character, Double>> freqList = new ArrayList<>(freqMap.entrySet());
		list.sort(Map.Entry.comparingByValue());
		freqList.sort(Map.Entry.comparingByValue());
		System.out.println(list);
		System.out.println(freqList);

		StringBuilder sb = new StringBuilder();
		try {
			Scanner sc = new Scanner(inFile);
			while(sc.hasNext()) {
				sb.append(sc.next());
			}
			sc.close();
		} catch (IOException ex) {
			System.out.println("Input file not found");
		}
		System.out.println(sb);
		System.out.println();
		
		StringBuilder sb2 = new StringBuilder();
		for(int i=0; i<sb.length(); i++) {
			char convert = sb.charAt(i);
			switch(convert) {
				case 'n': 
					convert = 'e';
					break;
				case 'y':
					convert = 't';
					break;
				case  'v':
					convert = 'a';
					break;
				case 'x':
					convert = 'o';
					break;
				case 'u':
				       	convert = 'i';
					break;
				case 'q':
					convert = 'n';
					break;
				case 'm':
					convert = 's';
					break;
				case 'h':
					convert = 'r';
					break;
				case 't':
					convert = 'h';
					break;
				case 'i':
					convert = 'd';
					break;
				case 'p':	
					convert = 'l';
					break;
				case 'a':
					convert = 'u';
					break;
				case 'c':
					convert = 'c';
					break;
				case 'z':
					convert = 'm';
					break;
				case 'l':
					convert = 'f';
					break;
				case 'g':
					convert = 'y';
					break;
				case 'b':
					convert = 'w';
					break;
				case 'r':
					convert = 'g';
					break;
				case 'e':
					convert = 'p';
					break;
				case 'd':
					convert = 'b';
					break;
				case 'f':
					convert = 'v';
					break;
				case 's':
					convert = 'k';
					break;
				case 'k':
					convert = 'x';
					break;
				case 'j':
					convert = 'q';
					break;
				case 'o':
					convert = 'j';
					break;
				case 'w':
					convert = 'z';
					break;
			}
			sb2.append(convert);
		}
		
		System.out.println(sb2);
			
		File outFile = new File("output.txt");
		try {
			FileWriter fw = new FileWriter(outFile); 
			fw.write(sb2.toString());
			fw.close();
		} catch (IOException ex) {
			System.out.println("Output file not found");
		}

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
