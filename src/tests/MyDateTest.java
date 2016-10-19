package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import date.MyDate;
import date.MyDateException;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

/*
 * Pour modifier une date il faut commencer par l'ann�e, mois et jour
 */

@RunWith(JMockit.class)
public class MyDateTest {
	MyDate myDate;
	
	@Mocked
	Calendar c = Calendar.getInstance();

	@Before
	public void init() {
		myDate = new MyDate();
		c.set(2012, 12, 21);
		myDate.setCalendar(c);
	}

	/*
	 * Check the method about the year
	 */

	@Test
	public void getMINYEARTest(){
		assertEquals(1970, MyDate.getMINYEAR());
	}

	@Test
	public void getMAXYEARTest(){
		assertEquals(2050, MyDate.getMAXYEAR());
	}

	@Test
	public void getDateArrayTest(){
		String [] DAYARRAY = {"Sunday", "Monday", "Tuesday" , "Wednesday", "Thurday", "Friday", "Saturday"};
		
		for(int i = 0 ; i < MyDate.getDayArray().length ; i++){
			assertEquals(MyDate.getDayArray()[i], DAYARRAY[i]);
		}
	}
	
	@Test
	public void getMonthArrayTest(){
		String [] MONTHARRAY = {"January", "February" , "March",
				"April", "May", "June", "July", "August", "September", "October",
				"November", "December"};
		
		for(int i = 0 ; i < MyDate.getMonthArray().length ; i++){
			assertEquals(MyDate.getMonthArray()[i], MONTHARRAY[i]);
		}
	}
	
	@Test
	public void todayTest() {
		new Expectations(){
			{
				c.getTime();
				result=new Date(2012,12,21);
				times=1;
			}
		};
		
		assertEquals(myDate.today(), new Date(2012,12,21));
	}
	
	@Test
	public void fromTimeStampTest(){
		long actual, expected;
		actual = MyDate.fromTimeStamp(0);
		expected = System.currentTimeMillis() / 1000L;
		assertEquals(actual, expected);
	}
	
	@Test
	public void replaceTest(){

	}

	@Test
	public void toOrdinalTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(19);
		
		assertEquals(736256, myDate.toOrdinal());
	}
	
	@Test
	public void isLeapYearTest() throws MyDateException{
		assertTrue( myDate.isLeapYear(2016));
	}

	@Test
	public void isNotLeapYearTest() throws MyDateException{
		assertFalse( myDate.isLeapYear(2017));
	}

	@Test
	public void is30Month() throws MyDateException{
		assertTrue(myDate.is30Month(11));
	}

	@Test
	public void isNot30Month() throws MyDateException{
		assertFalse(myDate.is30Month(1));
	}

	@Test
	public void is31Month() throws MyDateException{
		assertTrue(myDate.is31Month(5));
	}

	@Test
	public void isNot31Month() throws MyDateException{
		assertFalse(myDate.is31Month(6));
	}


	@Test
	public void weekDayWithFirstMonth() throws MyDateException{
		myDate.setYear(2017);
		myDate.setMonth(1);
		myDate.setDay(17);
		assertEquals(2, myDate.weekDay());
	}
	
	@Test
	public void  weekDaySunTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(16);
		
		assertEquals(0, myDate.weekDay());
	}

	@Test
	public void  weekDayMidWeekTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(19);
		
		assertEquals(3, myDate.weekDay());
	}
	
	@Test
	public void  weekDayMonTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(22);
		
		assertEquals(6, myDate.weekDay());
	}
	

	@Test
	public void isoWeekdayMonTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(17);
		assertEquals(1, myDate.isoWeekday());
	}
		
	@Test
	public void isoWeekdaySunTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(16);
		assertEquals(7, myDate.isoWeekday());
	}

	@Test
	public void isoCalendarTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(17);

		assertEquals("2016, 10, 17", myDate.isoCalendar());
	}

	@Test
	public void isoFormatTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(17);

		assertEquals("2016-10-17", myDate.isoFormat());
	}

	@Test
	public void isoWeekNumberTest() throws MyDateException{
		myDate.setYear(2017);
		myDate.setMonth(10);
		myDate.setDay(17);
		
		assertEquals(42, myDate.isoWeekNumber());
	}
	
	@Test
	public void isoWeekNumberATest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(20);
		
		assertEquals(42, myDate.isoWeekNumber());
	}
	
	@Test
	public void dayPastInYearInFirstMonthTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(1);
		myDate.setDay(17);
		
		assertEquals(17, myDate.dayPastInYear());
	}
	
	@Test
	public void dayPastInYearInOctoberMonthTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(17);

		assertEquals(291, myDate.dayPastInYear());
	}
	
	@Test
	public void dayPastInLeapYearInDecemberMonthTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(12);
		myDate.setDay(31);

		assertEquals(366, myDate.dayPastInYear());
	}
	
	@Test
	public void dayPastInNotLeapYearInDecemberMonthTest() throws MyDateException{
		myDate.setYear(2017);
		myDate.setMonth(12);
		myDate.setDay(31);

		assertEquals(365, myDate.dayPastInYear());
	}
	
	@Test
	public void dayPastInYearTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(17);
		
		assertEquals(291, myDate.dayPastInYear());
	}
	
	@Test
	public void toStringTest() throws MyDateException {
		myDate.setDay(17);
		myDate.setMonth(10);
		myDate.setYear(2016);

		assertEquals("17/10/2016", myDate.toString());
	}

	@Test
	public void ctimeTest() throws MyDateException {
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(17);

		assertEquals("Monday October 17 2016", myDate.ctime());
	}

	// limit premier jour du mois
	@Test 
	public void setDayLimiInfTest() throws MyDateException{
		myDate.setDay(1);
		assertEquals(1, myDate.getDay());
	}

	// Jour existant dans un mois
	@Test
	public void setDayTest() throws MyDateException{
		myDate.setDay(17);
		assertEquals(17, myDate.getDay());
	}

	// Test le jour 29 d'une ann�e bissextile
	@Test 
	public void setDayLimiSupLeapYearTest() throws MyDateException{
		myDate.setDay(29);
		myDate.setMonth(02);
		myDate.setYear(2016);
		assertEquals(29, myDate.getDay());
	}

	// Test le 30eme jour des mois de 30 jours
	@Test 
	public void setDayLimiSup30Test() throws MyDateException{
		myDate.setDay(30);
		myDate.setMonth(11);
		myDate.setYear(2016);
		assertEquals(30, myDate.getDay());
	}

	// Test du 31eme avec des mois de 31 jours
	@Test 
	public void setDayLimiSup31Test() throws MyDateException{
		myDate.setDay(31);
		myDate.setMonth(10);
		myDate.setYear(2016);
		assertEquals(31, myDate.getDay());
	}

	// Test du jour 0 dans un mois
	@Test (expected = MyDateException.class)
	public void setDayInvalidateLimitInfTest() throws MyDateException{
		myDate.setDay(0);
	}

	// Test du jour 29 dans un mois de 28 jours
	@Test (expected = MyDateException.class)
	public void setDayNotBissextileWith29DayTest() throws MyDateException{
		myDate.setYear(2017);
		myDate.setMonth(02);
		myDate.setDay(29);
	}

	// Test du jour 30 dans un mois de 29 jours
	@Test ( expected = MyDateException.class )
	public void setDayBissextileWith30DayTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(02);
		myDate.setDay(30);
	}

	// Test du 31eme jours dans un mois de 30 jours
	@Test (expected = MyDateException.class)
	public void setDayInvalidateLimitSup30Test() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(11);
		myDate.setDay(31);
	}

	// Test du 32eme jours dans un mois de 31 jours
	@Test (expected = MyDateException.class)
	public void setDayInvalidateLimitSup31Test() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(32);
	}

	@Test
	public void setMonthLimitInfTest() throws MyDateException{
		myDate.setMonth(1);
		assertEquals(1, myDate.getMonth());
	}

	@Test
	public void setMonthTest() throws MyDateException{
		myDate.setMonth(3);
		assertEquals(3, myDate.getMonth());
	}

	@Test
	public void setMonthLimitSupTest() throws MyDateException{
		myDate.setMonth(12);
		assertEquals(12, myDate.getMonth());
	}

	@Test (expected = MyDateException.class)
	public void setMonthInvalidateWith0Month() throws MyDateException{
		myDate.setMonth(0);
	}

	@Test (expected = MyDateException.class)
	public void setMonthInvalidateWith13Months() throws MyDateException{
		myDate.setMonth(13);
	}

	@Test
	public void setYearLimitInfTest() throws MyDateException{
		myDate.setYear(1970);
		assertEquals(1970, myDate.getYear());
	}

	@Test
	public void setYearTest() throws MyDateException{
		myDate.setYear(2016);
		assertEquals(2016, myDate.getYear());
	}

	@Test
	public void setYearLimitSupTest() throws MyDateException{
		myDate.setYear(2050);
		assertEquals(2050, myDate.getYear());
	}

	@Test (expected = MyDateException.class)
	public void setYearInvalidateLess1970() throws MyDateException{
		myDate.setYear(1945);
	}

	@Test (expected = MyDateException.class)
	public void setYearInvalidateMore2050() throws MyDateException{
		myDate.setYear(2100);
	}



}
