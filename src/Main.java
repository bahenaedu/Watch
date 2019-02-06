public class Main {

    public static void main(String[] args) {
        Time t = new Time(10, 8, 45);
        System.out.println(t);
        t.setSeconds(45);
        System.out.println(t);
        try{
            t.setSeconds(654);
        }
        catch (IllegalArgumentException e){
            System.out.println("oops");
        }

        System.out.println(t);

        Time t2 = new Time (24, 0, 1);
        System.out.println(t2);

    }
}
