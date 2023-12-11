import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

interface StreamOperations {
    static void printListElements(List<Integer> numbers) {
        System.out.println("List elements using forEach:");
        numbers.forEach(System.out::println);
    }

    default void filterOddNumbers(List<Integer> numbers) {
        System.out.println("\nFiltering odd numbers using filter():");
        numbers.stream().filter(i -> i % 2 != 0).forEach(System.out::println);
    }

    default Set<Integer> collectSquareNumbers(List<Integer> numbers) {
        System.out.println("\nMaking collection of square numbers using collect():");
        return numbers.stream().map(i -> i * i).collect(Collectors.toSet());
    }

}

public class OperationsOfStreamModified implements StreamOperations {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        StreamOperations.printListElements(numbers);

        new OperationsOfStreamModified().filterOddNumbers(numbers);

        Set<Integer> squareNumbers = new OperationsOfStreamModified().collectSquareNumbers(numbers);
        squareNumbers.forEach(System.out::println);

    }
}
