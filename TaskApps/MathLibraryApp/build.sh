#!/bin/bash

# Create output directories if they don't exist
mkdir -p bin
mkdir -p lib

# Initialize flags to determine if recompilation is  needed
# REBUILD_MATHLIB and REBUILD_CALCAPP
# will be set to true if the respective source files are newer than their compiled classes
# or if the MathLibrary.jar does not exist.
REBUILD_MATHLIB=false
REBUILD_CALCAPP=false

# Check if MathLibrary.jar exists,
if  [[ ! -f lib/MathLibrary.jar ]]; then
  REBUILD_MATHLIB=true
fi

# Check if MathLibrary.java is newer than its class
if [[ src/com/mathlib/MathLibrary.java -nt bin/com/mathlib/MathLibrary.class ]]; then
  REBUILD_MATHLIB=true
fi



# Check if CalculatorApp.java is newer than its class
if [[ src/com/calculator/CalculatorApp.java -nt bin/com/calculator/CalculatorApp.class ]]; then
  REBUILD_CALCAPP=true
fi

if $REBUILD_MATHLIB || $REBUILD_CALCAPP; then
  if $REBUILD_MATHLIB; then
    echo "Compiling MathLibrary..."
    javac -d bin src/com/mathlib/MathLibrary.java

    echo "Creating MathLibrary.jar..."
    jar cf lib/MathLibrary.jar -C bin com/mathlib
  fi

  echo "Compiling CalculatorApp..."
  javac -cp lib/MathLibrary.jar -d bin src/com/calculator/CalculatorApp.java

  echo "Creating CalculatorApp-Thin.jar..."
  jar cfe CalculatorThin.jar com.calculator.CalculatorApp -C bin com

  echo "Creating Fat JAR..."
  mkdir temp
  cd temp
  jar xf ../lib/MathLibrary.jar
  jar xf ../CalculatorApp-Thin.jar
  jar cfe ../CalculatorApp-Fat.jar com.calculator.CalculatorApp .
  cd ..
  rm -rf temp

  echo "✅ Build complete."
else
  echo "No changes detected. Skipping compilation and packaging."
fi

# A note for me and you!
# If you want to run the CalculatorApp, you can use:
# java -cp lib/MathLibrary.jar:CalculatorApp-Thin.jar com.calculator.CalculatorApp
# or
# java -jar CalculatorApp-Fat.jar


# A last one for me!
# If you want to create an executable script to run the CalculatorApp,
# you can create a script like this:  
# my_script.sh - This  is the file name, you can change it as you like.
# Make sure to place it in the same directory as the JAR files.
# The content of my_script.sh would be: 
# #!/bin/bash
# java -cp lib/MathLibrary.jar:CalculatorApp-Thin.jar com.calculator.CalculatorApp
# Save this script as my_script.sh in the same directory as your JAR files
# and then you can run it with:
# ./my_script.sh
# Don't forget to give it execute permissions:
# BY making it executable with:
# chmod +x my_script.sh