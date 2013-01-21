package org.exoplatform.selenium.platform.cs.functional.calendar.calendar;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.checkAlertWarning;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 * 
 * @author lientm
 * @date 22/12/2012
 *
 */
public class CS_Calendar_Calendar_Edit extends Calendar {
	@BeforeMethod
	public void beforeTest(){
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		signIn("john", "gtn");
		goToCalendarPage();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	//Case01: Edit a personal calendar with valid value
	@Test
	public void test01_EditPersonalCalendar_ValidValue(){
		String calendarOld = "CS_Calendar_Edit_01_1";
		String descOld = "CS_Calendar_Edit_description_01_1";
		String colorOld = "OrangeRed ColorCell";
		
		String calendarNew = "CS_Calendar_Edit_01_2";
		String descNew = "CS_Calendar_Edit_description_01_2";
		String colorNew = "PaleTurquoise ColorCell";
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarOld, descOld, "My Group", colorOld);
		assert getColorOfCalendar(calendarOld).equalsIgnoreCase(colorOld.split(" ")[0]);
		
		quickEditCalendar(calendarOld, calendarNew, descNew, null, colorNew);
		assert getColorOfCalendar(calendarNew).equalsIgnoreCase(colorNew.split(" ")[0]);
		
		deleteCalendar(calendarNew);
	}
	
	//Case02: Edit a personal calendar with invalid value
	@Test
	public void test02_EditPersonalCalendar_InvalidValue(){
		String calendarName = "CS_Calendar_Edit_02";
		String desc = "CS_Calendar_Edit_description_02";
		String color = "PaleTurquoise ColorCell";
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarName, desc, "My Group", color);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		
		info("Edit calendar with invalid name");
		goToEditCalendar(calendarName);
		for (int i = 0; i < DATA_SPECIAL_CHARACTER_NOT_FULL.length; i ++){
			type(ELEMENT_NAME_CAL_INPUT, DATA_SPECIAL_CHARACTER_NOT_FULL[i], true);
			save();
			checkAlertWarning(MESSAGE_ADD_CALENDAR_INVALID_NAME);
		}
		cancel();
		
		deleteCalendar(calendarName);
	}
	
	//Case12: Edit a shared calendar by shared user
	@Test
	public void test12_EditSharedCalendarBySharedUser(){
		String calendarName = "CS_Calendar_Edit_03";
		String desc = "CS_Calendar_Edit_description_03";
		String color = "SeaGreen ColorCell";
		String newColor = "Red";
		String[] user = {"demo"};
		String[] group = {};
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarName, desc, "My Group", color);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		
		info("shared this calendar");
		shareCalendar(calendarName, user, group, true);
		signOut();
		
		info("Check user "+ user[0] + " can see calendar, change color but can not edit Shared Calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForAndGetElement(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		goToEditCalendar("john- " + calendarName, false);
		checkAlertWarning(MESSAGE_EDIT_SHARED_CALENDAR);
		changeCalendarColor("john- " + calendarName, newColor);
		signOut();
		
		info("Delete calendar");
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
}
