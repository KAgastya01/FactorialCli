import java.math.BigInteger;
import java.util.*;

public class FactorialCli {
    public static void main(String[] args) {
        List<String> argList = Arrays.asList(args);

        // 1. Intercept help flags or complete lack of arguments
        if (argList.contains("-h") || argList.contains("--help") || args.length == 0) {
            printUsage();
            System.exit(0);
        }

        // 2. Extract configuration flags
        boolean benchmark = argList.contains("-b") || argList.contains("--benchmark");
        boolean quiet = argList.contains("-q") || argList.contains("--quiet");
        boolean interactive = argList.contains("-i") || argList.contains("--interactive");

        String token = null;

        // 3. Handle Interactive Mode
        if (interactive) {
            Scanner consoleScanner = new Scanner(System.in);
            System.out.print("Enter an integer to calculate its factorial: ");
            if (consoleScanner.hasNextLine()) {
                token = consoleScanner.nextLine().trim();
            }
        } else {
            // 4. Extract standard number flag location
            int flagIndex = argList.indexOf("-n");
            if (flagIndex == -1) {
                flagIndex = argList.indexOf("--number");
            }

            if (flagIndex == -1 || flagIndex + 1 >= args.length) {
                System.err.println("Error: Missing required option '-n' or '--number' followed by an integer (or use -i).");
                printUsage();
                System.exit(1);
            }
            token = args[flagIndex + 1];
        }

        // 5. Validate input format
        if (token == null || token.isEmpty() || !token.matches("-?\\d+")) {
            System.err.println("Error: '" + token + "' is not a valid integer format.");
            System.exit(1);
        }

        int n = Integer.parseInt(token);

        // 6. Enforce lower range limit (Negative numbers are invalid)
        if (n < 0) {
            System.err.println("Error: Value out of range. Number must be greater than or equal to 0.");
            System.exit(1);
        }

        // 7. Execute logic with high-precision and benchmarking
        long startTime = System.nanoTime();
        BigInteger result = calculateFactorial(n);
        long endTime = System.nanoTime();

        // 8. Output results based on formatting flags
        if (quiet) {
            System.out.println(result);
        } else {
            System.out.println(n + "! = " + result);
        }

        if (benchmark && !quiet) {
            double durationMs = (endTime - startTime) / 1_000_000.0;
            System.out.printf("[Benchmark] Execution Time: %.4f ms\n", durationMs);
        }

        System.exit(0);
    }

    /**
     * Core iterative algorithm scaling to massive inputs via BigInteger.
     */
    private static BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Prints an enhanced POSIX manual page design layout.
     */
    private static void printUsage() {
        System.out.println("NAME");
        System.out.println("    FactorialCli - Compute the factorial of arbitrary-size integers");
        System.out.println("\nSYNOPSIS");
        System.out.println("    java FactorialCli [FLAGS]");
        System.out.println("\nFLAGS / OPTIONS");
        System.out.println("    -n, --number <int>      Specify the target integer (>= 0).");
        System.out.println("    -i, --interactive       Prompt interactively for the number input.");
        System.out.println("    -b, --benchmark         Append processing telemetry execution speed performance metrics.");
        System.out.println("    -q, --quiet             Output ONLY the raw numerical solution (ideal for shell piping).");
        System.out.println("    -h, --help              Display this interactive help system documentation.");
        System.out.println("\nEXAMPLES");
        System.out.println("    java FactorialCli -n 50 -b");
        System.out.println("    java FactorialCli --interactive");
        System.out.println("    java FactorialCli -n 5 -q > result.txt");
    }
}
