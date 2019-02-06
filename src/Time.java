import java.util.IllformedLocaleException;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    public Time (){

    }

    public Time(int hours, int minutes, int seconds) {

        try {
            setSeconds(seconds);
        }
        catch (IllegalArgumentException e ){
            this.seconds = seconds%60;
            minutes += seconds / 60;
        }
        try{
            setMinutes(minutes);
        }
        catch (IllegalArgumentException e){
            this.minutes = minutes%60;
            hours = minutes/60;
        }
        try{
            setHours(hours);
        }
        catch (IllformedLocaleException e){
            this.hours = hours%24;
        }
    }

    @Override
    public String toString() {
        return hours + ":" + minutes + ":" + seconds;
    }

    public void setHours(int hours) {
        if(hours < 0 || hours > 24)
            throw new IllegalArgumentException("Invalid hours");
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 59)
            throw new IllegalArgumentException("Invalid minutes");
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        if (seconds < 0 || seconds > 59)
            throw new IllegalArgumentException("Invalid seconds");
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
