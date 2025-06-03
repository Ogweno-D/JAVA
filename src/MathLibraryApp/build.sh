#!/bin/bash

# Create output directories if they don't exist
mkdir -p bin
mkdir -p lib

REBUILD_MATHLIB=false
REBUILD_CALCAPP=false

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