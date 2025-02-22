import java.lang.reflect.*;
import java.util.*;

public class Debug {
    public static void print(char x) { System.err.print("'" + x + "'"); }
    public static void print(String x) { System.err.print("\"" + x + "\""); }
    public static void print(boolean x) { System.err.print(x ? "true" : "false"); }
    public static void print(int x) { System.err.print(x); }
    public static void print(long x) { System.err.print(x); }
    public static void print(double x) { System.err.print(x); }
    public static void print(float x) { System.err.print(x); }

    public static <A, B> void print(Map.Entry<A, B> p) {
        System.err.print("{");
        print(p.getKey());
        System.err.print(", ");
        print(p.getValue());
        System.err.print("}");
    }

    public static <T> void print(Collection<T> x) {
        System.err.print("{");
        boolean first = true;
        for (T i : x) {
            if (!first) System.err.print(", ");
            print(i);
            first = false;
        }
        System.err.print("}");
    }

    @SafeVarargs
    public static <T> void print(T... args) {
        System.err.print("[");
        boolean first = true;
        for (T arg : args) {
            if (!first) System.err.print(", ");
            print(arg);
            first = false;
        }
        System.err.print("]");
    }

    public static void print(Object obj) {
        if (obj == null) {
            System.err.print("null");
            return;
        }
        if (obj instanceof String || obj instanceof Number || obj instanceof Boolean || obj instanceof Character) {
            System.err.print(obj);
            return;
        }
        if (obj instanceof Collection<?>) {
            print((Collection<?>) obj);
            return;
        }
        if (obj instanceof Map.Entry<?, ?>) {
            print((Map.Entry<?, ?>) obj);
            return;
        }
        if (obj.getClass().isArray()) {
            System.err.print(Arrays.deepToString((Object[]) obj));
            return;
        }
        printClass(obj);
    }

    private static void printClass(Object obj) {
        System.err.print("{");
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean first = true;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!first) System.err.print(", ");
                System.err.print(field.getName() + "=");
                print(field.get(obj));
                first = false;
            } catch (IllegalAccessException ignored) {}
        }
        System.err.print("}");
    }

    public static void debug(Object... args) {
        System.err.print("[");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) System.err.print(", ");
            print(args[i]);
        }
        System.err.println("]");
    }

    public static void main(String[] args) {
        class Example {
            int id = 1;
            String name = "Test";
            double score = 99.5;
        }

        List<Integer> list = Arrays.asList(1, 2, 3);
        Map.Entry<String, Integer> pair = Map.entry("hello", 42);
        Example obj = new Example();

        debug("Test", 123, 45.67, true, list, pair, obj);
    }
}

