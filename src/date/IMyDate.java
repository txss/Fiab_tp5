package date;

import java.util.Calendar;
import java.util.Date;

public interface IMyDate {
	
	public static Date today() {
		return Calendar.getInstance().getTime();
	}
	
	public static MyDate fromTimeStamp(long  timeStamp) {
		return null;
	}
	
	public static MyDate fromOrdinal(int ordinal) {
		return null;
	}
	
	public IMyDate replace(int year, int month, int day); // Return a date with the same value, except for those parameters given new values by whichever keyword arguments are specified
	public IMyDate toOrdinal(); // Return the proleptic Gregorian ordinal of the date
	public int weekDay(); // Return the day of the week as an integer, where Monday is 0 and Sunday is 6
	public int isoWeekday(); // Return an integer between 1 at 7
	public void isoCalendar(); // Return a 3-tuple, (ISO year, ISO week number, ISO weekday)
	public String isoFormat(); // Return a string representing the date in ISO 8601 format, ‘YYYY-MM-DD’.
	public String toString (); // Return a string representing DD/MM/YYYY
	public String ctime(); // Return a string representing the date Wed Dec 4 2002
}
