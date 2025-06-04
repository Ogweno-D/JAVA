public class Car extends  Vehicle {

    private String model;
    private String color;

    public Car(String name, int speed, String model, String color) {
        // This is inherited from the abstract class Vehicle
        super(name, speed);
        this.model = model;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    // Methods inherited from Vehicle
    @Override
    public void start() {
        System.out.println("Car " + getName() + " is starting.");
    }

    @Override
    public void stop() {
        System.out.println("Car " + getName() + " is stopping.");
    }
}