package date;

import java.util.Calendar;
import java.util.Date;

public class MyDate {
	private final static int MINYEAR = 1970;
	private final static int MAXYEAR = 2050;
	private final static String [] DAYARRAY = {"Sunday", "Monday", "Tuesday" , "Wednesday", "Thurday", "Friday", "Saturday"};
	private final static String [] MONTHARRAY = {"January", "February" , "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December"};
	  

	private Calendar c;
	
	private int year;
	private int month;
	private int day ;

	public MyDate(Calendar c){
		this.c = c;
	}

	public static int getMINYEAR() { return MINYEAR; }

	public static int getMAXYEAR() { return MAXYEAR; }

	public static String[] getDayArray() {	return DAYARRAY; }

	public static String[] getMonthArray() { return MONTHARRAY; }

	
	public int getYear() {
		return year; 
	}

	public void setYear(int year) throws MyDateException {
		if( year < MINYEAR || year > MAXYEAR) 
			throw new MyDateException("The year is not available");

		this.year = year; 
	}

	public int getMonth() {	
		return month; 
	}

	public void setMonth(int month) throws MyDateException { 
		if( month < 1 || month > 12 ) 
			throw new MyDateException("The month is not availabe");
		this.month = month; 
	}

	public int getDay() {
		return day;
	}

	public boolean is31Month(int month){
		if ( month == 1 || month == 3 || month == 5 || month == 7 || 
				month == 8 || month == 10 || month == 12) 
			return true;
		return false;
	}

	public boolean is30Month(int month){
		if ( month == 4 || month == 6 || month == 9 || month == 11 )
			return true;
		return false;
	}

	public boolean isLeapYear(int year){
		if( (year % 4 ==0 && year % 100 != 0) || year % 400 == 0)
			return true;
		return false;
	}

	public void setDay(int day) throws MyDateException {
		if( day < 1  )
			throw new MyDateException("The day is not available");

		if ( is31Month(month) &&  day > 31) {
			System.out.println("Le mois a 31 jours");
			throw new MyDateException("The day is greater than 31");
		}else if ( is30Month(month) && day > 30){
			System.out.println("Le mois a 30 jours");
			throw new MyDateException("The day is greater than 30");
		}else if( isLeapYear(year) &&  day > 29 && month == 2){
			throw new MyDateException("This is a leap year");
		}else
			if( day > 28   && month == 2 )
				throw new MyDateException("This is not a leap year");

		this.day = day;
	}

	public Date today(){
		return c.getTime();
	}

	public static long fromTimeStamp(long  timeStamp){
		return System.currentTimeMillis() / 1000L;
	}

	public MyDate fromOrdinal(int ordinal){
		// TODO Auto-generated method stub
		return this;
	}
	
	public int findDay(){
		int num;
		int z;
		
		if( month < 3  ) z = year - 1;
		else z = year;
		
		num = ( (23*month) / 9) + day + 4 + year + (year / 4 ) - 
				(year / 100) + (year / 400);
		
		if( month >= 3 ) return (num - 2 ) % 7;
		return ( num ) % 7;
		
	}
	

	/**
	 * Return a date with the same value, except for those parameters given 
	 * new values by whichever keyword arguments are specified
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public MyDate replace(int year, int month, int day) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the proleptic Gregorian ordinal of the date
	 * @return
	 */
	public MyDate toOrdinal() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the day of the week as an integer, where Monday is 0 and Sunday is 6
	 * @return
	 */
	public int weekDay() {
		return findDay();
	}

	/**
	 * Return an integer between 1 at 7
	 * @return
	 */
	public int isoWeekday() {
		if(findDay()==0)
			return 7;
		return findDay();
	}

	/**
	 * Return a number of day past in year with the current day
	 * @return
	 */
	public int dayPastInYear(){
		int dayPastInYear = day ;
	
		if( month == 1 ) return dayPastInYear;
		
		for(int i = 1 ; i < month ; i++){
			if( i == 2 && isLeapYear(year)) dayPastInYear +=29;
			if( i == 2 && ! isLeapYear(year)) dayPastInYear +=28;
			
			if( is30Month(i) ) dayPastInYear+= 30; 
			if( is31Month(i) )  dayPastInYear += 31;
		}
		
		return dayPastInYear;
	}
	
	public int isoWeekNumber(){
		return dayPastInYear() / 7;
	}
	
	/**
	 * Return a 3-tuple, (ISO year, ISO week number, ISO weekday)
	 * @return
	 */
	public String isoCalendar() {
		
		return null;
	}

	/**
	 * Return a string representing the date in ISO 8601 format, �YYYY-MM-DD�.
	 * @return
	 */
	public String isoFormat() {
		return getYear() + "-" + getMonth() + "-" + getDay();
	}

	/**
	 * Return a string representing DD/MM/YYYY
	 */
	public String toString(){
		return getDay() + "/" + getMonth() + "/" + getYear();
	}

	/**
	 * Return a string representing the date Wed Dec 4 2002
	 * @return
	 */
	public String ctime() {
		return DAYARRAY[findDay()] + " " + MONTHARRAY[getMonth()-1] + " " + getDay() + " " + getYear() ;
	}

}
