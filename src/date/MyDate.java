package date;

public class MyDate {
	private final static int MINYEAR = 1970;
	private final static int MAXYEAR = 2050;
	private final static String [] DATEARRAY = {"Monday", "Tuesday" , "Wednesday", "Thurday", "Friday", "Saturday", "Sunday"};

	private int year = -1;
	private int month = -1;
	private int day = -1;

	public MyDate(){

	}

	public static  int getMINYEAR() { return MINYEAR; }

	public static int getMAXYEAR() { return MAXYEAR; }

	public static String[] getDatearray() {	return DATEARRAY; }

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

	public boolean isBissextile(int year){
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
		}else if( isBissextile(year) &&  day > 29 && month == 2){
			throw new MyDateException("This is a leap year");
		}else
			if( day > 28   && month == 2 )
				throw new MyDateException("This is not a leap year");

		this.day = day;
	}

	public MyDate today(){
		// TODO Auto-generated method stub
		return this;
	}

	public MyDate fromTimeStamp(long  timeStamp){
		// TODO Auto-generated method stub
		return this;
	}

	public MyDate fromOrdinal(int ordinal){
		// TODO Auto-generated method stub
		return this;
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
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Return an integer between 1 at 7
	 * @return
	 */
	public int isoWeekday() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Return a 3-tuple, (ISO year, ISO week number, ISO weekday)
	 * @return
	 */
	public String isoCalendar() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return a string representing the date in ISO 8601 format, ‘YYYY-MM-DD’.
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
		return getDay() + " " + getMonth() + " " + getYear() ;
	}

}
