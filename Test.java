import java.util.*;

public class Test {
    public static void main(String[] args) {
        class Example {
            int id = 1;
            String name = "Test";
            double score = 99.5;
        }

        List<Integer> list = Arrays.asList(1, 2, 3);
        Map.Entry<String, Integer> pair = Map.entry("hello", 42);
        Example obj = new Example();

        Debug.debug("Test", 123, 45.67, true, list, pair, obj);
    }
}

