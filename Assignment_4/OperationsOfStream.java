import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;

public class OperationsOfStream {

    public static void main(String[] args) {

        List<Integer> numbers =  Arrays.asList(1,2,3,4,5,6);

        
        System.out.println(" List elements using forEach:");
        numbers.forEach(System.out::println);

        
        System.out.println("\n Filtering odd numbers using filter():");
        numbers.stream().filter(i -> i % 2 != 0).forEach(System.out::println);
        
        System.out.println("\n Making collection of square numbers using collect():");
        numbers.stream().map(i->i*i).collect(Collectors.toSet()).forEach(System.out::println);
 
    }
}
