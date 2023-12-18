import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TotalCountOfWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        String sentence = scanner.nextLine();
 
        scanner.close();
        
 	String str = sentence.toLowerCase();
        Map<String, Integer> occurrences = countAllWordOccurrences(str);
  
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
 
    private static Map<String, Integer> countAllWordOccurrences(String sentence) {
    	
        String[] words = sentence.split("\\s+");
 
        Map<String, Integer> wordOccurrences = new HashMap<>();
 
        for (String word : words) {  
            wordOccurrences.put(word, wordOccurrences.getOrDefault(word, 0) + 1);
        }

        return wordOccurrences;
    }
}
