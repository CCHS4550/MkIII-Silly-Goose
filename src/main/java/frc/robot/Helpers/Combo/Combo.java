package frc.robot.Helpers.Combo;

import java.util.ArrayList;
import frc.robot.Helpers.Timer;

public class Combo {
    private ArrayList<Input> combo;
    private Timer timing;
    private Timer concurrent;
    private double accepted;
    
    public Combo(double accepted){
        combo = new ArrayList<Input>();
        timing = new frc.robot.Helpers.Timer(accepted);
        concurrent = new Timer(0);
        this.accepted = accepted;
        concurrent.start();
    }

    public String order(){
        String res = "";
        for(Input input : combo){
            res += input.id() + " ";
        }
        return res;
    }

    public void add(Input input){
        timing.start();
        input.setDelay(concurrent.elapsed());
        combo.add(input);
    }

    public boolean over(){
        return timing.started() && timing.triggered();
    }

    public String orderName(){
        String res = "";
        for(Input input : combo){
            res += input.name() + " ";
        }
        return res;
    }

    public boolean contains(String check){
        for(int i = 0; i < combo.size(); i++){
            if(concurrent.elapsed() - combo.get(i).delay() > accepted) combo.remove(i--);
        }
        String c = this.orderName();
        for(int i = 0; i < c.length() - check.length() + 1; i++){
            if(c.substring(i, i + check.length()).equals(check)){
                return true;
            }
        }
        return false;
    }

    public int containsLast(String check1, String check2, int val){
        for(int i = 0; i < combo.size(); i++){
            if(concurrent.elapsed() - combo.get(i).delay() > accepted) combo.remove(i--);
        }
        String c = this.orderName();
        String shor = check1.length() > check2.length() ? check2 : check1;
        String lon = check1.length() <= check2.length() ? check2 : check1;
        for(int i = c.length() - shor.length(); i >= 0; i--){
            if(i + lon.length() <= c.length() && c.substring(i, i + lon.length()).equals(lon)){
                return lon.equals(check1) ? val : -val;
            }
            if(c.substring(i, i + shor.length()).equals(shor)){
                return shor.equals(check1) ? val : -val;
            }
        }
        return 0;
    }
}
