# FactorialCLI

A robust, dependency-free Java Command Line Interface (CLI) tool designed to compute the factorial of arbitrary-size integers ($n!$). Built entirely using standard Java native libraries.

<p align="center">
  <img src="https://user-images.githubusercontent.com/74038190/225813708-98b745f2-7d22-48cf-9150-083f1b00d6c9.gif" width="800">
  <br><br>
</p>

## Features

- **Infinite Precision Engine**: Powered by `java.math.BigInteger` to seamlessly calculate massive factorials (e.g., $1000!$, $5000!$) with no risk of data overflow or memory clipping.
- **POSIX Standard Flags**: Supports intuitive short flags (`-n`) and self-documenting long options (`--number`).
- **Interactive Mode**: Prompt and capture user input securely at runtime using the `-i` flag without pre-configuring command arguments.
- **Performance Benchmarking**: Toggle on-the-fly execution speed tracking down to fractions of a millisecond.
- **Pipeline & Stream Friendly**: A dedicated quiet mode strips metadata formatting to output raw results ideal for terminal pipelines, shell scripting, and text redirections (`> file.txt`).
- **Zero Dependencies**: Pure Java implementation requiring no heavy build frameworks like Maven or Gradle.

---

## Prerequisites

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

## Execution & Usage

Always invoke the program from the directory containing your compiled `FactorialCli.class` file. Use the `-cp .` flag to explicitly point Java to your local classpath directory.

### Syntax
```bash
java -cp . FactorialCli [FLAGS]
```

### Options
- `-h, --help` : Display the built-in manual and usage documentation.
- `-n, --number <int>` : Specify the target integer (must be $\ge 0$).
- `-i, --interactive` : Prompt interactively for the number input.
- `-b, --benchmark` : Append high-precision execution speed telemetry metrics.
- `-q, --quiet` : Output **only** the raw numerical solution (ideal for shell piping).

### Examples

**View Help Documentation:**
```bash
java -cp . FactorialCli --help
```

**Calculate a Massive Factorial with Benchmarking:**
```bash
java -cp . FactorialCli -n 50 -b
```

**Interactive CLI Session:**
```bash
java -cp . FactorialCli --interactive
# Terminal will prompt: Enter an integer to calculate its factorial:
```

**Pipe Raw Output directly to a Text File:**
```bash
java -cp . FactorialCli -n 5 -q > result.txt
```

---

## ⚠️ Error Handling & Troubleshooting

### 1. `ClassNotFoundException` Error
If you receive `Error: Could not find or load main class FactorialCli`:
- Ensure you do **not** add `.class` or `.java` to the end of your run command.
- Explicitly add the local classpath argument: `java -cp . FactorialCli -n 5`.

### 2. Constraints and Range Errors
Negative integers are invalid. Passing negative bounds or unparseable input structures will trigger clean validation failures:

```bash
java -cp . FactorialCli -n -5
# Standard Error Output: Error: Value out of range. Number must be greater than or equal to 0.
```

```bash
java -cp . FactorialCli -n text
# Standard Error Output: Error: 'text' is not a valid integer format.
```

```bash
echo \$?
# Output: 1 (Indicates shell pipeline failure exit code)
```

<p align="center">
  <img src="https://user-images.githubusercontent.com/74038190/212750155-3ceddfbd-19d3-40a3-87af-8d329c8323c4.gif" width="700">
  <br></br>
  <sub>Made with ☕ and Open Source Love</sub>
</p>
