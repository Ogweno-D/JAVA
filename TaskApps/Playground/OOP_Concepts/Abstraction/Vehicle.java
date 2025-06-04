abstract class Vehicle {
    final private String name;
    final private int speed;

    public Vehicle(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public abstract void start();
    
    public abstract void stop();

    public  void description() {
        System.out.println("This is a vehicle.");
    }
}