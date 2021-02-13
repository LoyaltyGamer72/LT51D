package trab3;

public class SentCall extends Call{
    private Duration d;

    public SentCall(Time t, String number, Duration d) {
        super(t, number);
        this.d=d;
    }

    public Duration getDuration() {
        return d;
    }

    public boolean merge(Call other) {
        if(other instanceof SentCall){
            super.merge(other);
            d=d.add(((SentCall) other).d);
            return true;
        }
        return false;
    }
}
