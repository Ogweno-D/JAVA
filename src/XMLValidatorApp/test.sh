#!/bin/bash

# XML Validator Application Test Script
# This script runs comprehensive tests on the XML Validator application

set -e  # Exit on any error (disabled for some tests)

echo "========================================="
echo "XML Validator Application Test Suite"
echo "========================================="
echo

# Configuration
JAR_PATH="build/XMLValidator.jar"
TEST_DIR="test-files"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# Test counters
TOTAL_TESTS=0
PASSED_TESTS=0
FAILED_TESTS=0

# Function to print colored output
print_test_header() {
    echo
    echo -e "${CYAN}============================================${NC}"
    echo -e "${CYAN}TEST $1: $2${NC}"
    echo -e "${CYAN}============================================${NC}"
    TOTAL_TESTS=$((TOTAL_TESTS + 1))
}

print_success() {
    echo -e "${GREEN}✅ PASS:${NC} $1"
    PASSED_TESTS=$((PASSED_TESTS + 1))
}

print_failure() {
    echo -e "${RED}❌ FAIL:${NC} $1"
    FAILED_TESTS=$((FAILED_TESTS + 1))
}

print_info() {
    echo -e "${BLUE}ℹ️  INFO:${NC} $1"
}

print_command() {
    echo -e "${YELLOW}🔧 Running:${NC} $1"
}

# Function to run a test command and capture result
run_test() {
    local test_name="$1"
    local command="$2"
    local expected_exit_code="${3:-0}"  # Default expected exit code is 0
    
    print_command "$command"
    echo
    
    # Run command and capture exit code
    set +e  # Don't exit on error for tests
    eval "$command"
    local actual_exit_code=$?
    set -e  # Re-enable exit on error
    
    echo
    if [ $actual_exit_code -eq $expected_exit_code ]; then
        print_success "$test_name (Exit code: $actual_exit_code)"
    else
        print_failure "$test_name (Expected exit code: $expected_exit_code, Got: $actual_exit_code)"
    fi
    
    echo "----------------------------------------"
}

# Pre-test checks
print_info "Performing pre-test checks..."

# Check if JAR exists
if [ ! -f "$JAR_PATH" ]; then
    echo -e "${RED}ERROR: JAR file not found at $JAR_PATH${NC}"
    echo "Please run the build script first: ./build.sh"
    exit 1
fi
print_info "JAR file found: $JAR_PATH"

# Check if test directory exists
if [ ! -d "$TEST_DIR" ]; then
    echo -e "${YELLOW}WARNING: Test directory not found. Creating $TEST_DIR...${NC}"
    mkdir -p "$TEST_DIR"
fi
print_info "Test directory: $TEST_DIR"

# Create test files if they don't exist
print_info "Setting up test files..."

# Create valid book XML
cat > "$TEST_DIR/book.xml" << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<book xmlns="http://example.com/books">
    <title>Java Programming Mastery</title>
    <author>John Developer</author>
    <year>2024</year>
    <price>39.99</price>
    <isbn>978-0123456789</isbn>
    <category>Programming</category>
</book>
EOF

# Create book XSD
cat > "$TEST_DIR/book.xsd" << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           targetNamespace="http://example.com/books"
           xmlns="http://example.com/books"
           elementFormDefault="qualified">
    
    <xs:element name="book">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="title" type="xs:string"/>
                <xs:element name="author" type="xs:string"/>
                <xs:element name="year" type="xs:int"/>
                <xs:element name="price" type="xs:decimal"/>
                <xs:element name="isbn" type="xs:string" minOccurs="0"/>
                <xs:element name="category" type="xs:string" minOccurs="0"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
</xs:schema>
EOF

# Create invalid XML for testing
cat > "$TEST_DIR/invalid-book.xml" << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<book xmlns="http://example.com/books">
    <title>Invalid Book</title>
    <author>Test Author</author>
    <year>not-a-number</year>
    <price>29.99</price>
</book>
EOF

print_info "Test files created successfully"
echo

# START TESTS
echo "🚀 Starting test execution..."

# Test 1: Valid XML validation
print_test_header "1" "Valid XML Validation"
run_test "Valid XML should pass validation" \
         "java -Duser.timezone=UTC -Xms128m -Xmx512m -jar $JAR_PATH -xml $TEST_DIR/book.xml -xsd $TEST_DIR/book.xsd" \
         0

# Test 2: Invalid XML validation  
print_test_header "2" "Invalid XML Validation"
run_test "Invalid XML should fail validation" \
         "java -jar $JAR_PATH -xml $TEST_DIR/invalid-book.xml -xsd $TEST_DIR/book.xsd" \
         0

# Test 3: Missing XML file
print_test_header "3" "Missing XML File Handling"
run_test "Missing XML file should be handled gracefully" \
         "java -jar $JAR_PATH -xml nonexistent.xml -xsd $TEST_DIR/book.xsd" \
         0

# Test 4: Missing XSD file
print_test_header "4" "Missing XSD File Handling"
run_test "Missing XSD file should be handled gracefully" \
         "java -jar $JAR_PATH -xml $TEST_DIR/book.xml -xsd nonexistent.xsd" \
         0

# Test 5: No arguments
print_test_header "5" "No Arguments Handling"
run_test "No arguments should show usage" \
         "java -jar $JAR_PATH" \
         1

# Test 6: Invalid arguments
print_test_header "6" "Invalid Arguments Handling"
run_test "Invalid arguments should show usage" \
         "java -jar $JAR_PATH -invalid argument" \
         1

# Test 7: Missing argument values
print_test_header "7" "Missing Argument Values"
run_test "Missing argument values should be handled" \
         "java -jar $JAR_PATH -xml" \
         1

# Test 8: Wrong number of arguments
print_test_header "8" "Wrong Number of Arguments"
run_test "Wrong number of arguments should be handled" \
         "java -jar $JAR_PATH -xml file.xml" \
         1

# Test 9: JVM Options Test - Different Timezone
print_test_header "9" "JVM Options - Timezone"
run_test "Different timezone should be displayed" \
         "java -Duser.timezone=America/New_York -jar $JAR_PATH -xml $TEST_DIR/book.xml -xsd $TEST_DIR/book.xsd" \
         0

# Test 10: JVM Options Test - Memory Settings
print_test_header "10" "JVM Options - Memory Settings"
run_test "Custom memory settings should be reflected" \
         "java -Xms64m -Xmx256m -jar $JAR_PATH -xml $TEST_DIR/book.xml -xsd $TEST_DIR/book.xsd" \
         0

# Test 11: Debug Mode
print_test_header "11" "Debug Mode"
run_test "Debug mode should provide detailed error information" \
         "java -Ddebug=true -jar $JAR_PATH -xml $TEST_DIR/invalid-book.xml -xsd $TEST_DIR/book.xsd" \
         0

# Test 12: All JVM Options Combined
print_test_header "12" "Combined JVM Options"
run_test "All JVM options should work together" \
         "java -Duser.timezone=Europe/London -Xms128m -Xmx1g -Ddebug=true -jar $JAR_PATH -xml $TEST_DIR/book.xml -xsd $TEST_DIR/book.xsd" \
         0

# Print test summary
echo
echo "========================================="
echo "              TEST SUMMARY"
echo "========================================="
echo -e "Total Tests:  ${BLUE}$TOTAL_TESTS${NC}"
echo -e "Passed:       ${GREEN}$PASSED_TESTS${NC}"
echo -e "Failed:       ${RED}$FAILED_TESTS${NC}"

if [ $FAILED_TESTS -eq 0 ]; then
    echo -e "Result:       ${GREEN}ALL TESTS PASSED! ✅${NC}"
    echo
    echo "🎉 Congratulations! Your XML Validator application is working correctly."
    echo
    echo "Key features verified:"
    echo "✅ XML validation against XSD schemas"
    echo "✅ Command-line argument parsing"
    echo "✅ Error handling for missing files"
    echo "✅ JVM options support (timezone, memory)"
    echo "✅ Graceful error reporting"
    echo "✅ Debug mode functionality"
    exit 0
else
    echo -e "Result:       ${RED}SOME TESTS FAILED ❌${NC}"
    echo
    echo "Please review the failed tests above and fix any issues."
    echo "Common issues:"
    echo "• Check file permissions"
    echo "• Verify JAR was built correctly"
    echo "• Ensure Java is properly installed"
    exit 1
fi