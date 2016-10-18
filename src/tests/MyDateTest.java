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
		myDate = new MyDate(c);
	}

	/*
	 * Check the method about the year
	 */
	
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
	public void isBissextile() throws MyDateException{
		assertTrue( myDate.isBissextile(2016));
	}
	
	@Test
	public void isNotBissextile() throws MyDateException{
		assertFalse( myDate.isBissextile(2017));
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
		assertTrue( myDate.is31Month(5) );
	}
	
	@Test
	public void isNot31Month() throws MyDateException{
		assertFalse( myDate.is31Month(6) );
	}
	
	
	
	@Test
	public void replaceTest(){
	
	}
	
	@Test
	public void toOrdinalTest(){
		
	}
	
	@Test
	public void  weekDayTest() throws MyDateException{
		myDate.setDay(17);
		myDate.setMonth(10);
		myDate.setYear(2016);
		
		assertEquals(0, myDate.getDay());
	}
	
	@Test(expected = MyDateException.class)
	public void  weekDayWithErrorTest() throws MyDateException{
		myDate.setDay(18);
		myDate.setMonth(10);
		myDate.setYear(2016);
		
		assertEquals(0, myDate.getDay());
	}
	
	@Test
	public void isoWeekdayTest() throws MyDateException{
		myDate.setDay(17);
		myDate.setMonth(10);
		myDate.setYear(2016);
		
		assertEquals(1, myDate.getDay());
	}
	
	@Test(expected = MyDateException.class)
	public void isoWeekdayWithErrorTest() throws MyDateException{
		myDate.setDay(18);
		myDate.setMonth(10);
		myDate.setYear(2016);
		
		myDate.getDay();
	}
	
	@Test
	public void isoCalendarTest() throws MyDateException{
		myDate.setDay(17);
		myDate.setMonth(10);
		myDate.setYear(2016);
		
		assertEquals("2016, 10, 17", myDate.isoCalendar());
	}
	
	@Test
	public void isoFormatTest() throws MyDateException{
		myDate.setDay(17);
		myDate.setMonth(10);
		myDate.setYear(2016);
	
		assertEquals("2016-10-17", myDate.isoFormat());
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
		myDate.setDay(17);
		myDate.setMonth(10);
		myDate.setYear(2016);
		
		assertEquals("Mon Oct 17 2016", myDate.ctime());
	}
		
	// limit premier jour du mois
	@Test 
	public void setDayLimiInfTest() throws MyDateException{
		myDate.setDay(1);
		assertEquals(1, myDate.getDay() );
	}
	
	// Jour existant dans un mois
	@Test
	public void setDayTest() throws MyDateException{
		myDate.setDay(17);
		assertEquals(17, myDate.getDay() );
	}
	
	// Test le jour 29 d'une ann�e bissextile
	@Test 
	public void setDayLimiSupBissextileTest() throws MyDateException{
		myDate.setDay(29);
		myDate.setMonth(02);
		myDate.setYear(2016);
		assertEquals(29, myDate.getDay() );
	}
	
	// Test le 30eme jour des mois de 30 jours
	@Test 
	public void setDayLimiSup30Test() throws MyDateException{
		myDate.setDay(30);
		myDate.setMonth(11);
		myDate.setYear(2016);
		assertEquals(30, myDate.getDay() );
	}
	
	// Test du 31eme avec des mois de 31 jours
	@Test 
	public void setDayLimiSup31Test() throws MyDateException{
		myDate.setDay(31);
		myDate.setMonth(10);
		myDate.setYear(2016);
		assertEquals(31, myDate.getDay() );
	}
	
	// Test du jour 0 dans un mois
	@Test (expected = MyDateException.class)
	public void setDayInvalidateLimitInfTest() throws MyDateException{
		myDate.setDay(0);
		
		myDate.getDay();
	}
	
	// Test du jour 29 dans un mois de 28 jours
	@Test (expected = MyDateException.class)
	public void setDayNotBissextileWith29DayTest() throws MyDateException{
		myDate.setYear(2017);
		myDate.setMonth(02);
		myDate.setDay(29);
		
		myDate.getDay();
	}
	
	// Test du jour 30 dans un mois de 29 jours
	@Test ( expected = MyDateException.class )
	public void setDayBissextileWith30DayTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(02);
		myDate.setDay(30);

		myDate.getDay();
	}
	
	// Test du 31eme jours dans un mois de 30 jours
	@Test (expected = MyDateException.class)
	public void setDayInvalidateLimitSup30Test() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(11);
		myDate.setDay(31);
		
		myDate.getDay();
	}
	
	// Test du 32eme jours dans un mois de 31 jours
	@Test (expected = MyDateException.class)
	public void setDayInvalidateLimitSup31Test() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(32);
		
		myDate.getDay();
	}
	
	@Test
	public void setMonthLimitInfTest() throws MyDateException{
		myDate.setMonth(1);
		assertEquals(1, myDate.getMonth() );
	}
	
	@Test
	public void setMonthTest() throws MyDateException{
		myDate.setMonth(3);
		assertEquals(3, myDate.getMonth() );
	}
	
	@Test
	public void setMonthLimitSupTest() throws MyDateException{
		myDate.setMonth(12);
		assertEquals(12, myDate.getMonth() );
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
		assertEquals(1970, myDate.getYear() );
	}
		
	@Test
	public void setYearTest() throws MyDateException{
		myDate.setYear(2016);
		assertEquals(2016, myDate.getYear() );
	}
	
	@Test
	public void setYearLimitSupTest() throws MyDateException{
		myDate.setYear(2050);
		assertEquals(2050, myDate.getYear() );
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
