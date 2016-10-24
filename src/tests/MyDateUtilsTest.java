package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import date.MyDate;
import date.MyDateException;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class MyDateUtilsTest {

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
}
