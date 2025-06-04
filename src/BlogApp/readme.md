# JARS ( Java Archives)

## What is a JAR file?

- JAR (Java Archive) files are a package file format used to bundle Java class files, associated metadata, and resources into a single file for distribution.
- They are based on the ZIP file format and typically have a .jar file extension.
  
- Key features and uses of JAR files:
  - Aggregation: JAR files combine multiple files into one, simplifying distribution and deployment of Java applications
  - Compression: They support compression, reducing the file size and improving download times.
  - Platform Independence: JAR files are platform-independent, allowing them to run on any system with a compatible Java Virtual Machine (JVM).
  - Security: JAR files can be digitally signed to authenticate their origin and ensure integrity.
  - Versatility: They can be used to package different types of Java programs, including applets, applications, and libraries.
  
- Manifest File:
A JAR file includes a manifest file, which contains metadata about the JAR file and its contents.

### Creating an executable JAR

The jar command-line tool, included with the Java Development Kit (JDK), is used to create, extract, and update JAR files

#### Example

- Using the files in this repository as an example;
Make sure you have the application file for example here we had Main.java in our src directory

- Step 1 : In the terminal navigate to the src directory and compile the java code to obtain the class
  We shall put the class file in a different directory "bin"
  
```bash
    javac -d bin src/YourFile.java
```

- Step 2 : In the src directory create a `MANIFEST.MF` file and add the following
  
  ``` bash
    Manifest-Version: 1.0
    Main-Class: Main [For complex file structure use "com.foldername.file" Or whatever folder structure you have for your classes]
  ```

  Replace Main with the fully qualified name of your main class. You can identify the main class as the class containing the `public static void main(String[] args)` method.
  
  N/B: Remember to leave a blank line after the main-class. This is a major cause for errors
  
- Step 3 : Create the JAR file and include the manifest with a command similar to the one below:
  
  ```bash

    jar cfm myApp.jar MANIFEST.MF -C bin/ .

  ```

    This command uses:
  - `c` to create a new JAR file,
  - `f` to specify the output JAR file name (MyApp.jar),
  - `m` to include the manifest file (MANIFEST.MF), and
  - `-C bin/ .` to add the compiled classes from the bin directory.

- Step 3 : Test the execution of the jar file by  running the following command.

    ```bash
    
    java -jar MyApp.jar

    ```

#### Common `jar` command options

- c: Creates a new JAR file,
- f: Specifies the JAR file name,
- v: Provides verbose output,
- x: Extracts the contents of a JAR file, and
- u: Updates an existing JAR file.
- m: Include the manifest file(Custom)

