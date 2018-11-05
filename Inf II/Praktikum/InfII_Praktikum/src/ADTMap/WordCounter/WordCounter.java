package ADTMap.WordCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//import ADTMap.ListMap;
//import ADTMap.Map;

import ADTMap.TreeMap.Map;
import ADTMap.TreeMap.TreeMap;

public class WordCounter {

	public static void main(String[] args) {
		Scanner input = null;
		//Map<String, Integer> retList = ListMap.empty();
		Map<String, Integer> retList = TreeMap.empty();
		try {
			input = new Scanner(new File("src/ADTMap/WordCounter/Essay.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(input.hasNext())
		{
			String word  = input.next();
			word = word.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
			if(retList.member(word))
			{
				int count = retList.lookup(word);
				retList = retList.delete(word);
				retList = retList.insert(word, ++count);
			} else {
				retList = retList.insert(word, 1);
			}
		}
		System.out.println(retList.toString());
	}
}