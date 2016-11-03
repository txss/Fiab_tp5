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
	public void replaceTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(16);
		
		MyDate test = new MyDate();
		test.setYear(2016);
		test.setMonth(11);
		test.setDay(30);

		assertEquals(test.toString(), myDate.replace(2016, 11, 30).toString());
	}
	
	@Test(expected = MyDateException.class)
	public void replaceWithInvalidateDateTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(16);
		
		MyDate test = new MyDate();
		test.setYear(2017);
		test.setMonth(2);
		test.setDay(29);
	}

	@Test
	public void fromOrdinalTest() throws MyDateException{
		assertEquals("21/12/2012", myDate.fromOrdinal(734858));
	}
	
	@Test
	public void toOrdinalTest() throws MyDateException{
		myDate.setYear(2012);
		myDate.setMonth(12);
		myDate.setDay(21);
	
		assertEquals(734858, myDate.toOrdinal());
	}

	
	
	@Test
	public void weekDayWithFirstMonth() throws MyDateException{
		myDate.setYear(2017);
		myDate.setMonth(1);
		myDate.setDay(17);
		assertEquals(2, myDate.weekDay());
	}
	
	@Test
	public void weekDaySunTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(16);
		
		assertEquals(0, myDate.weekDay());
	}

	@Test
	public void weekDayMidWeekTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(19);
		
		assertEquals(3, myDate.weekDay());
	}
	
	@Test
	public void weekDayMonTest() throws MyDateException{
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

		assertEquals("2016-42-1", myDate.isoCalendar());
	}

	@Test
	public void isoFormatTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(17);

		assertEquals("2016-10-17", myDate.isoFormat());
	}

	@Test
	public void isoWeekNumberWhithLeapYearTest() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(17);
		
		assertEquals(42, myDate.isoWeekNumber());
	}
	
	@Test
	public void isoWeekNumberWithNotLeapYearTest() throws MyDateException{
		myDate.setYear(2017);
		myDate.setMonth(10);
		myDate.setDay(17);
		
		assertEquals(42, myDate.isoWeekNumber());
	}
	
	@Test
	public void isoWeekNumberMonthFirst() throws MyDateException{
		myDate.setYear(2018);
		myDate.setMonth(10);
		myDate.setDay(17);
		
		assertEquals(42, myDate.isoWeekNumber());
	}
	
	@Test
	public void isoWeekNumber53WeekEndYear() throws MyDateException{
		myDate.setYear(2020);
		myDate.setMonth(12);
		myDate.setDay(30);
		
		assertEquals(53, myDate.isoWeekNumber());
	}
	
	
	@Test
	public void isoWeekNumber53LeapYear() throws MyDateException{
		myDate.setYear(2016);
		myDate.setMonth(1);
		myDate.setDay(1);
		
		assertEquals(53, myDate.isoWeekNumber());
	}

	@Test
	public void ctimeTest() throws MyDateException {
		myDate.setYear(2016);
		myDate.setMonth(10);
		myDate.setDay(17);

		assertEquals("Monday October 17 2016", myDate.ctime());
	}

}
