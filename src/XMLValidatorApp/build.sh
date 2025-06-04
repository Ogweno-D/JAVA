#!/bin/bash

# XML Validator Application Build Script
# This script compiles the Java application and creates a runnable JAR

set -e  # Exit on any error

echo "======================================"
echo "XML Validator Application Build Script"
echo "======================================"
echo

# Configuration
PROJECT_NAME="XMLValidator"
MAIN_CLASS="com.xmlvalidator.XMLValidatorApp"
SRC_DIR="src/main/java"
BUILD_DIR="build"
JAR_NAME="XMLValidator.jar"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if Java is installed
print_status "Checking Java installation..."
if ! command -v javac &> /dev/null; then
    print_error "javac (Java compiler) not found. Please install JDK."
    exit 1
fi

if ! command -v jar &> /dev/null; then
    print_error "jar command not found. Please install JDK."
    exit 1
fi

JAVA_VERSION=$(javac -version 2>&1 | cut -d' ' -f2)
print_success "Java compiler found: $JAVA_VERSION"
echo

# Clean previous build
print_status "Cleaning previous build..."
if [ -d "$BUILD_DIR" ]; then
    rm -rf "$BUILD_DIR"
    print_success "Cleaned build directory"
else
    print_status "No previous build to clean"
fi

# Create build directory structure
print_status "Creating build directory..."
mkdir -p "$BUILD_DIR"
print_success "Build directory created"
echo

# Check source files exist
print_status "Checking source files..."
MAIN_JAVA="$SRC_DIR/com/xmlvalidator/XMLValidatorApp.java"
ERROR_HANDLER_JAVA="$SRC_DIR/com/xmlvalidator/XMLValidationErrorHandler.java"

if [ ! -f "$MAIN_JAVA" ]; then
    print_error "Main source file not found: $MAIN_JAVA"
    print_error "Please ensure the source files are in the correct location."
    exit 1
fi

if [ ! -f "$ERROR_HANDLER_JAVA" ]; then
    print_error "Error handler source file not found: $ERROR_HANDLER_JAVA"
    print_error "Please ensure the source files are in the correct location."
    exit 1
fi

print_success "Source files found"
echo

# Compile Java files
print_status "Compiling Java source files..."
echo "  - XMLValidatorApp.java"
echo "  - XMLValidationErrorHandler.java"
echo

# Use -Xlint for additional warnings
javac -Xlint:all -d "$BUILD_DIR" "$SRC_DIR"/com/xmlvalidator/*.java

if [ $? -eq 0 ]; then
    print_success "Compilation completed successfully"
else
    print_error "Compilation failed"
    exit 1
fi
echo

# Create manifest file
print_status "Creating JAR manifest..."
MANIFEST_FILE="$BUILD_DIR/MANIFEST.MF"
cat > "$MANIFEST_FILE" << EOF
Manifest-Version: 1.0
Main-Class: $MAIN_CLASS
Created-By: XML Validator Build Script
Implementation-Title: XML Validator Application
Implementation-Version: 1.0.0
Built-Date: $(date)

EOF

print_success "Manifest file created"
echo

# Create JAR file
print_status "Creating JAR file: $JAR_NAME"
cd "$BUILD_DIR"
jar cfm "$JAR_NAME" MANIFEST.MF com/

if [ $? -eq 0 ]; then
    print_success "JAR file created successfully"
else
    print_error "Failed to create JAR file"
    exit 1
fi

# Move back to project root
cd ..

# Verify JAR file
print_status "Verifying JAR file..."
JAR_PATH="$BUILD_DIR/$JAR_NAME"
if [ -f "$JAR_PATH" ]; then
    JAR_SIZE=$(ls -lh "$JAR_PATH" | awk '{print $5}')
    print_success "JAR file verified: $JAR_PATH ($JAR_SIZE)"
else
    print_error "JAR file not found at expected location"
    exit 1
fi
echo

# Display build summary
echo "======================================"
echo "BUILD SUMMARY"
echo "======================================"
echo "✅ Project: $PROJECT_NAME"
echo "✅ JAR File: $JAR_PATH"
echo "✅ Main Class: $MAIN_CLASS"
echo "✅ Size: $JAR_SIZE"
echo "✅ Build Date: $(date)"
echo

# Display usage instructions
echo "======================================"
echo "USAGE INSTRUCTIONS"
echo "======================================"
echo "Basic usage:"
echo "  java -jar $JAR_PATH -xml <xml_file> -xsd <xsd_file>"
echo
echo "With JVM options:"
echo "  java -Duser.timezone=UTC -Xms256m -Xmx1g -jar $JAR_PATH -xml <xml_file> -xsd <xsd_file>"
echo
echo "Example:"
echo "  java -jar $JAR_PATH -xml test-files/sample.xml -xsd test-files/sample.xsd"
echo

# Test if we can run the JAR (basic syntax check)
print_status "Testing JAR execution (help output)..."
if java -jar "$JAR_PATH" 2>/dev/null; then
    print_warning "JAR executed but showed usage (expected - no arguments provided)"
else
    # Exit code 1 is expected when no arguments are provided
    if [ $? -eq 1 ]; then
        print_success "JAR execution test passed"
    else
        print_warning "JAR execution returned unexpected exit code: $?"
    fi
fi

echo
echo "======================================"
print_success "BUILD COMPLETED SUCCESSFULLY!"
echo "======================================"
echo
echo "Next steps:"
echo "1. Create test XML and XSD files in test-files/ directory"
echo "2. Run the application with: java -jar $JAR_PATH -xml <xml> -xsd <xsd>"
echo "3. Use the provided test script to run automated tests"
echo End of build script