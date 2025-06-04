public class Main {
    public static void main(String[] args) {
        // Create a Tesla object
        Tesla tesla = new Tesla("Model S", 250, "Sedan", "Red", "Autopilot 2.0");
        
        // Call static method from Vehicle class
        tesla.description(); 
        
        // Start the Tesla
        tesla.start();

        // Stop the Tesla
        tesla.stop();

        // Print Tesla details
        System.out.println("Tesla Model: " + tesla.getModel() + ", Color: " + tesla.getColor() + ", Autopilot Version: " + tesla.getAutopilotVersion());
    }
}