# FactorialCli

A robust, dependency-free Java Command Line Interface (CLI) tool designed to compute the factorial of an integer ($n!$). Built entirely using standard `java.util.*` native libraries.

##  Features

- **POSIX Standard Flags**: Supports short flags (`-n`) and self-documenting long options (`--number`).
- **Zero Dependencies**: Uses only core Java libraries (`java.util.*`), requiring no heavy build frameworks like Maven or Gradle.
- **Robust Argument Parsing**: Powered by `java.util.Scanner` to securely parse and validate user token blocks.
- **Pipeline Ready**: Routes diagnostic errors to standard error (`System.err`) and provides operational termination exit codes (`0` for success, `1` for failures) for seamless script integration.
- **Overflow Protection**: Enforces calculation boundaries ($0 \le n \le 20$) to prevent silent memory signs and bit flipping in `long` primitives.

---

##  Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher.
- Verify your installation by running:
  ```bash
  java -version
  ```

---

## 💻 Installation & Compilation

1. Clone or navigate to the directory containing your source file.
2. Ensure the filename matches the class definition exactly: `FactorialCli.java`.
3. Compile the source code down to Java bytecode:
   ```bash
   javac FactorialCli.java
   ```

---

##  Execution & Usage

Always invoke the program from the directory containing your compiled `FactorialCli.class` file. Use the `-cp .` flag to explicitly point Java to your local classpath directory.

### Syntax
```bash
java -cp . FactorialCli [FLAGS]
```

### Options
- `-h, --help`        : Display the built-in manual and usage documentation.
- `-n, --number <int>`: Specify the target integer (ranging from `0` to `20` inclusive).

### Examples

**View Help Documentation:**
```bash
java -cp . FactorialCli --help
```

**Calculate a Factorial:**
```bash
java -cp . FactorialCli -n 6
# Output: 6! = 720
```

**Calculate using Long Flags:**
```bash
java -cp . FactorialCli --number 15
# Output: 15! = 1307674368000
```

---

## ⚠️ Error Handling & Troubleshooting

If you encounter execution errors, verify the parameters below:

### 1. `ClassNotFoundException` Error
If you receive `Error: Could not find or load main class FactorialCli`:
- Ensure you do **not** add `.class` or `.java` to the end of your run command.
- Explicitly add the local classpath argument: `java -cp . FactorialCli -n 5`.

### 2. Constraints and Range Errors
The tool restricts input boundaries up to `20` because standard signed `long` structures overflow starting at $21!$. Passing invalid bounds or formats will output structured shell failures:
```bash
java -cp . FactorialCli -n 25
# Standard Error Output: Error: Value out of range. Number must be between 0 and 20 to prevent data overflow.
```
```bash
java -cp . FactorialCli -n text
# Standard Error Output: Error: 'text' is not a valid integer format.
```
```bash
echo \$?
# Output: 1 (Indicates shell pipeline failure)
```
