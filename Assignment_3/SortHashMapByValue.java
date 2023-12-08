import java.util.*;

public class SortHashMapByValue {
    public static void main(String[] args) {
        
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apurva", 5);
        hashMap.put("Atharv", 3);
        hashMap.put("Asmita", 7);
        hashMap.put("Arpita", 1);
        hashMap.put("Akruti", 2);
 
        System.out.println("Original HashMap: " + hashMap);

       
        HashMap<String, Integer> sortedHashMap = sortByValue(hashMap);

       
        System.out.println("Sorted HashMap by Value: " + sortedHashMap);
    }
 
    private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hashMap) {
       
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(hashMap.entrySet());
 
        entryList.sort((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));
 
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
 
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
