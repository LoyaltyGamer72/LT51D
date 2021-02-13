package trab3;

import java.io.Serializable;

public abstract class Call implements Comparable<Call>, Serializable{
    private Time time;
    private final String number;

    public Call(Time t, String number){
        time=t;
        this.number=number;
    }

    public Time getTime() {
        return time;
    }

    public String getNumber() {
        return number;
    }

    public int compareTo(Call other){
        return time.compareTo(other.time);
    }

    public String toString(){
        return number+" "+time.toString();
    }

    public boolean merge(Call other){
        time=other.time;
        return true;
    }
}
