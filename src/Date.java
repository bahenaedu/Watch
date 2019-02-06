import java.lang.Math;
public class Date
{
    /* Let's do this thing properly and set up a look-up table
     * which will be used in to return month as string bit at
     * the end. We'll store the months in an array of type
     * String and as computers count from zero, our first entry
     * will be monthToString[0], returning January. */
    public static String [] monthToString=
            {
                    "Jan", "Feb", "Mar", "Apr", "May",
                    "Jun", "Jul", "Aug", "Sep", "Oct",
                    "Nov", "Dec"
            };
    /* I'm going to use a look-up table for the number of days
     * in each month as follows: */
    public static int [] noOfDays=
            {
                    31,28,31,30,31,30,31,31,30,31,30,31
            };
    /* I'm going to attempt to calculate the day entered based
     * on the numeric date entered (oh no! Maths :-( !!!) so, I
     * need a look-up table for that... Extra day added for
     * leap-years (ie, anything after a legal leap-year has an
     * off-set), this first table is for the 20th Century, ie,
     * a 19 perfix on the year: */
    public static String [] daysOfWeek20th=
            {
                    "Mon","Tue","Wed","Thu",
                    "Fri","Sat","Sun","Mon"
            };
    /* And this is the corrosponding look-up table for the 21st
     * century boy! */
    public static String [] daysOfWeek21st=
            {
                    "Sun","Mon","Tue","Wed","Thu",
                    "Fri","Sat","Sun"
            };
    /* Here are the month codes! */
    public static int [] monthCodes=
            {
                    6,2,2,5,0,3,5,1,4,6,2,4
            };
    /* And we need to declare some variables for working out the day: */
    public static float a, x, y, z;
    public static int v, c;
    /* Here is a variable of type char that'll force a line-feed
     * when using System.out.print ("text"+ln+"more text"); */
    public static char ln=13;
    /* The following variable of type String is also used in
     * the sub-routine that returns the month as string */
    public static String mon="";
    /* Okay, here's some variables of type integer (whole numbers)
     * which will be used in the constructor thingy */
    private int day, month, year;

    /* Error catching for type date in DD/MM/YY format to
     * check for legal dates, assuming a 19xx or 20xx prefix*/
    public Date (int dd, int mm, int yy)
    {
        day=dd; month=mm; year=yy;
        if (month<1 || month>12)
        {
            System.out.println("?Month not valid error"+ln+"Reset to default value.");
            month=01;
        }
        if (day<1 || day>31)
        {
            System.out.println("?Day not valid error"+ln+"Reset to default value.");
            day=01;
        }
        /* As there is no easy way to store a leap-year in a look-up table
         * I'm going to check for the 29th February first and validate it
         * later. Of course, this is advanced programming, isn't it? */
        if (day>29 && month==2)
        {
            System.out.println("?Day not valid error"+ln+"Except for leap-years, Feburary has only 28 days.");
            day=01;
        }
        /* Now, let's check to see if a legal leap-year has been entered
         * by the user, shall we? Oh, we need to do some maths. */
        if (day==29 && month==2 && ((year-2000)%4!=0))
        {
            System.out.println("?Date not valid error"+ln+"Not a leap-year.");
            day=28;
        }
        /* So, we've tested for a valid leap-year, the only thing that could
         * trip up our array above for the numbers of days in each month. */
        if (day<1 || day>noOfDays[month-1] && month!=2)
        {
            System.out.println("?Date not valid error"+ln+"Not a legal date.");
            day=01;
        }
        /* That's a whole lot more efficient and advanced than having a
         * 'switch case' or a load of 'if' statements checking for each
         * condition, isn't it? Okay, I'd use a switch case if I thought
         * that the person maintaining the Java code wasn't very good and needed
         * it to be readable, but if you use comments correctly then you
         * don't necessarily need code to be more readable, should you? */
    }

    /* This will initialise the date and then we'll do some stuff wid it */
    public Date()
    {
        /* Because I've gone slightly over-board on the error checking,
         * I need to initialise the date to 1,1,0 (Day, Month, Year) otherwise
         * ?Day not valid error and ?Month not valid error is thrown back at
         * the user, at least on the command line interface (ie, DOS).
         * Still, you can't be too diligent! */
        this(1,1,0);
    }
    /* This takes a copy of the date. */
    public Date (Date other)
    {
        this (other.day, other.month, other.year);
    }

    /* This method will return the monthAsString, as suggested by its' name */
    public String monthAsString()
    {
        /* This is very efficient because it has in-built error checking
         * actually without testing for loads of conditions */
        if (month>0 && month<13)
        {
            mon=monthToString[month-1];
            return mon;
        }
        /* Just in case any errors have sneaked into validating the month,
         * It will return a Month not set message */
        else
            return "Month not set";
    }
    /* This compares one date to another to find out if it's earlier than or
     * not, if so, returning true. */
    public boolean earlierThan(Date other)
    {
        if(year<other.year)
        {
            return true;
        }
        else
        if (year==other.year && month<other.month)
        {
            return true;
        }
        else
        if (month==other.month && day<other.day)
        {
            return true;
        }
        else
            return false;
    }
    /* This compares dates, if equal then true, if not then false. */

    public boolean equals (Date other)
    {
        if (day==other.day && month==other.month && year==other.year)
        {
            return true;
        }
        else
            return false;
    }

    public String suffix()
    {
        /* Here is where a switch case is useful, in terms of returning a
         * date: because most days as numbers have a th suffix (ie, 4th),
         * we'll use a switch case for the exceptions to that rule. */
        switch(day)
        {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            case 21: return "st";
            case 22: return "nd";
            case 23: return "rd";
            case 31: return "st";
            default: return "th";
        }
    }

    public String dayFromDate()
    {
/** This method will return the day of week based on a numeric
 * date entered after 1st March 1900, because 1900 isn't a
 * leap-year, whereas 2000 is (ie, leap-years ending 00 only
 * happen every 400 years). It's highly unlikely that a student
 * will be born before this date, so for our purposes, it works
 * fine.
 * The basic maths are to take the year and add a 25% to it ignoring
 * any decimal points (ie, 25% of 01 = 0.25, so we ignore the .25)
 * then we have the following month codes: Jan=6, Feb=2, Mar=2,
 * Apr=5, May=0, Jun=3, Jul=5, Aug=1, Sep=4, Oct=6, Nov=2, Dec=4
 * which is stored in a look-up table. We take the numberic day
 * and add it to our sum, (so, month+(abs(month/4))+monthCode+day)
 * We then need the absolute value of that and pass that to a
 * type int, and then take the remainder of that number when
 * devided by 7. If the answer is negative, we add 7 until it's
 * positive, then we check for leap-years, and minus 1 off the
 * answer if the month is January or February and return the
 * dayOfWeek according to the look-up table. Simple! */
        a=year+(Math.abs(year/4));
        // This takes the year and should add 25% to it.
        x=monthCodes[month-1];
        // This will pass the 'month code' to x for our mathematics
        y=day;
        // We need to know the date to do calculations with it.
        z=a+x+y;
        // This is used to work out the day
        a=Math.abs(z);
        c=Math.round(a);
        v=c%7;
        while(v<1)
        {
            v=v+7;
        }
        if ((year-2000)%4!=0)
        {
            return daysOfWeek20th[v];
        }
        else
        if ((year-2000)%4==0 && (month>0 && month<3))
        {
            return daysOfWeek20th[v-1];
        }
        else
        if ((year-2000)%4==0 && (month>2))
        {
            return daysOfWeek20th[v];
        }
        else
            return "Day unknown";
    }

    public String toString()
    {
        /* This is another attempt at error trapping, just in case
         * anything above has failled. */
        if(month!=0 || day!=0)
        {
            if(year<10)
            {
                /* A nested if statement to do some padding on years less than xx10 */
                return dayFromDate()+" "+day+suffix()+" of "+monthAsString()+" 0"+year;
            }
            else
                return dayFromDate()+" "+day+suffix()+" of "+monthAsString()+" "+year;
        }
        else
            return "Date not set";
    }

    public void copy(Date other)
        /* This is where the date is copied to, I think. */
    {
        day=other.day; month=other.month; year=other.year;
    }
}
