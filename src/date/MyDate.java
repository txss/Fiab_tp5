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

	public MyDate() {}

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

	public void setCalendar(Calendar c) {
		this.c = c;
	}//setCalendar()

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
			throw new MyDateException("The day is greater than 31");
		}else if ( is30Month(month) && day > 30){
			throw new MyDateException("The day is greater than 30");
		}else if( isLeapYear(year) &&  day > 29 && month == 2){
			throw new MyDateException("This is a leap year");
		}else
			if( day > 28   && month == 2 )
				throw new MyDateException("This is not a leap year");

		this.day = day;
	}

	/**
	 * Return Date of day
	 * @return Date
	 */
	public Date today(){
		return c.getTime();
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
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static long fromTimeStamp(long  timeStamp){
		return System.currentTimeMillis() / 1000L;
	}

	/**
	 * 
	 * @param ordinal
	 * @return
	 */
	public MyDate fromOrdinal(int ordinal){ //Méthode de Kévin & Ben

		return this;
	}

	/**
	 * Return the proleptic Gregorian ordinal of the date
	 * @return
	 */
	public int toOrdinal(int year, int month, int day) {
		int nbBisex = (year-1) / 4;
		int nbDayInYears = ((year-1) * 365) + nbBisex ;

		System.out.println("nbBisex : " + nbBisex);
		int tab_month [] = {0, 31 , 59,
				90, 120, 151, 181, 212, 243, 273,
				304, 334};

		int nbDayInThisYear = tab_month[month-1] + day;

		System.out.println(nbDayInThisYear + " - " + month);

		if(isLeapYear(year) && month > 2 ) {
			nbDayInThisYear++;
		}

		System.out.println(nbDayInYears + " - " + tab_month[month-1] + " - " + nbDayInThisYear);

		return nbDayInYears + nbDayInThisYear;
	}

	/**
	 * Return the day of the week as an integer, where Monday is 0 and Sunday is 6
	 * @return
	 */
	public int weekDay() {
		int num;

		num = ( (23*month) / 9) + day + 4 + year + (year / 4 ) - 
				(year / 100) + (year / 400);

		if( month >= 3 ) return (num - 2 ) % 7;
		return ( num ) % 7;

	}

	/**
	 * Return an integer between 1 at 7
	 * @return
	 */
	public int isoWeekday() {
		if(weekDay()==0)
			return 7;
		return weekDay();
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



	/**
	 * Return the current number of the week
	 * @return
	 * @throws MyDateException
	 */
	public int isoWeekNumber() throws MyDateException{
		MyDate tmp = new MyDate();
		tmp.setYear(year);
		tmp.setMonth(1);
		tmp.setDay(1);

		MyDate tmpweek = new MyDate();
		tmpweek.setYear(year-1);
		tmpweek.setMonth(1);
		tmpweek.setDay(1);
		int dayPast = dayPastInYear();
		int result;

		if(tmp.isoWeekday() > 4){
			result = ((dayPast - (8-tmp.isoWeekday()) ) / 7);
		}else
			result = ( ((tmp.isoWeekday()-1) + dayPast) /7);
			
		if( tmpweek.isoWeekday()==4)
			if (result == 0)
				return 53;
		
		return result+1;
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
		return DAYARRAY[weekDay()] + " " + MONTHARRAY[getMonth()-1] + " " + getDay() + " " + getYear() ;
	}

}
