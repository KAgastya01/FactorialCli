import java.util.*;

public class FactorialCli {

    public static void main(String[] args) {
        // 1. Convert terminal arguments array to a List for streamlined querying
        List<String> argList = Arrays.asList(args);

        // 2. Intercept help flags or complete lack of arguments
        if (argList.contains("-h") || argList.contains("--help") || args.length == 0) {
            printUsage();
            System.exit(0);
        }

        // 3. Find the location index of the target value flag
        int flagIndex = argList.indexOf("-n");
        if (flagIndex == -1) {
            flagIndex = argList.indexOf("--number");
        }

        // 4. Validate flag existence and check that a parameter follows it
        if (flagIndex == -1 || flagIndex + 1 >= args.length) {
            System.err.println("Error: Missing required option '-n' or '--number' followed by an integer.");
            printUsage();
            System.exit(1);
        }

        // 5. Pass the raw string token directly into a java.util.Scanner instance
        String token = args[flagIndex + 1];
        Scanner scanner = new Scanner(token);

        // 6. Ensure the input string is a cleanly structured integer
        if (!scanner.hasNextInt()) {
            System.err.println("Error: '" + token + "' is not a valid integer format.");
            scanner.close();
            System.exit(1);
        }

        int n = scanner.nextInt();
        scanner.close();

        // 7. Enforce range limit (long primitives overflow beyond 20!)
        if (n < 0 || n > 20) {
            System.err.println("Error: Value out of range. Number must be between 0 and 20 to prevent data overflow.");
            System.exit(1);
        }

        // 8. Execute core loop logic
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        // 9. Output final evaluation to standard output
        System.out.println(n + "! = " + result);
        System.exit(0);
    }

    /**
     * Prints a standardized POSIX manual page design layout.
     */
    private static void printUsage() {
        System.out.println("NAME");
        System.out.println("     FactorialCli - Compute the factorial of a given integer");
        System.out.println("\nSYNOPSIS");
        System.out.println("     java FactorialCli [FLAGS]");
        System.out.println("\nFLAGS / OPTIONS");
        System.out.println("     -n, --number <int>  Specify the target integer (0 to 20 inclusive).");
        System.out.println("     -h, --help          Display this interactive help system documentation.");
        System.out.println("\nEXAMPLES");
        System.out.println("     java FactorialCli -n 5");
        System.out.println("     java FactorialCli --number 18");
    }
}
