public class Tesla extends Car {

    private String autopilotVersion;

    public Tesla(String name, int speed, String model, String color, String autopilotVersion) {
        super(name, speed, model, color);
        this.autopilotVersion = autopilotVersion;
    }

    public String getAutopilotVersion() {
        return autopilotVersion;
    }

    public void setAutopilotVersion(String autopilotVersion) {
        this.autopilotVersion = autopilotVersion;
    }

    @Override
    public void start() {
        System.out.println("Tesla " + getName() + " is starting with autopilot version " + autopilotVersion + ".");
    }

    @Override
    public void stop() {
        System.out.println("Tesla " + getName() + " is stopping.");
    }
}