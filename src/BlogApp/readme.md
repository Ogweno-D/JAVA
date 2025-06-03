# Creating an executable JAR file

Make sure you have the application file for example here we had Main.java in our src directory

* Step 1 : In the terminal navigate to the src directory and compile the java code to obtain the class
  We shall put the class file in a different directory "bin"
  
```bash
    javac -d bin src/YourFile.java
```

* Step 2 : In the src directory create a `MANIFEST.MF` file and add the following
  
  ``` bash
    Manifest-Version: 1.0
    Main-Class: Main [For complex file structure use "com.foldername.file" Or whatever folder structure you have for your classes]
  ```

  Replace Main with the fully qualified name of your main class. You can identify the main class as the class containing the `public static void main(String[] args)` method.
  
  N/B: Remember to leave a blank line after the main-class. This is a major cause for errors
  
* Step 3 : Create the JAR file and include the manifest with a command similar to the one below:
  
  ```bash

    jar cfm myApp.jar MANIFEST.MF -C bin/ .

  ```

    This command uses:
  * `c` to create a new JAR file,
  * `f` to specify the output JAR file name (MyApp.jar),
  * `m` to include the manifest file (MANIFEST.MF), and
  * `-C bin/ .` to add the compiled classes from the bin directory.

* Step 3 : Test the execution of the jar file by  running the following command.

    ```bash
    
    java -jar MyApp.jar

    ```
