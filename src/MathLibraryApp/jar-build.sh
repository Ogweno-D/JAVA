# Complete build process for both thin and fat JARs

echo "=== Building MathLibrary and Calculator ==="

# 1. Create MathLibrary JAR
echo "Creating MathLibrary JAR..."
jar cf lib/mathlib.jar -C bin com/mathlib

# 2. Create Thin JAR (Method 1 -Simplest)
echo "Creating Thin JAR with main class..."
echo " From the root directory, run the following command- to verify and prove the class just in case:"
javac -cp lib/MathLibrary.jar -d bin src/com/calculator/CalculatorApp.java

echo "To now create the thin JAR, run the following command:"
jar cfe CalculatorApp-Thin.jar com.calculator.CalculatorApp -C bin com
echo " 
    com.calculator.CalculatorApp is your main class 
    (ensure the class is declared like this: package com.calculator;)
    -C bin com tells the jar tool to start from bin and include the full com folder hierarchy
    "
# Note: The above command assumes that the CalculatorApp class is in the package com.calculator
# If you want to create a thin JAR without specifying the main class, you can use:
# Uncomment the following line to create a thin JAR without specifying the main class
# jar cf CalculatorApp-Thin.jar -C bin com/calculator
# If you want to create a thin JAR with the main class specified, use:
# jar cfe CalculatorApp-Thin.jar com.calculator.CalculatorApp -C bin com

# 3. Create Thin JAR (Method 2 - using custom manifest)
echo "Creating custom manifest..."
cat > MANIFEST.MF << EOF
Manifest-Version: 1.0
Main-Class: com.calculator.CalculatorApp
Class-Path: lib/mathlib.jar
EOF

echo "Creating Thin JAR with manifest..."
jar cfm CalculatorApp-Thin-WithManifest.jar MANIFEST.MF -C bin com/calculator

# 4. Create Fat JAR
echo "Creating Fat JAR..."
mkdir temp
cd temp
jar xf ../lib/mathlib.jar
jar xf ../CalculatorApp-Thin.jar
jar cfe ../CalculatorApp-Fat.jar com.calculator.CalculatorApp .
cd ..
rm -rf temp

echo "=== Build Complete ==="
echo "Files created:"
echo "- lib/mathlib.jar (library)"
echo "- CalculatorApp-Thin.jar (needs classpath)"
echo "- CalculatorApp-Thin-WithManifest.jar (includes classpath in manifest)"
echo "- CalculatorApp-Fat.jar (self-contained)"

echo "=== How to Run === On WINDOWS, check documentation for running JARs on Linux/MacOS=== "
echo "Thin JAR (Method 1):"
echo "java -cp ""CalculatorThin.jar;lib/MathLibrary.jar"" com.calculator.CalculatorApp"
echo
echo "Thin JAR (Method 2 - with manifest):"
echo "java -jar CalculatorApp-Thin-WithManifest.jar"
echo
echo "Fat JAR:"
echo "java -jar CalculatorApp-Fat.jar"

# Clean up
rm -f MANIFEST.MF