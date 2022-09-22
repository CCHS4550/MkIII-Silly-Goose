package frc.robot.Helpers.Combo;

public class Input {
    private int id;
    private String name;
    private double timeSince;

    public Input(int id, String name, double timeSince){
        this.id = id;
        this.name = name;
        this.timeSince = timeSince;
    }

    public Input(int id, String name){
        this.id = id;
        this.name = name;
        timeSince = 0;
    }

    public int id(){
        return id;
    }

    public String name(){
        return name;
    }

    public double delay(){
        return timeSince;
    }

    public void setDelay(double delay){
        timeSince = delay;
    }
    
}
