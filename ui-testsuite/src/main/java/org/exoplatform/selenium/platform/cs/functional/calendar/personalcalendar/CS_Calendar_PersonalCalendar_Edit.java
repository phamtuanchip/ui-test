package org.exoplatform.selenium.platform.cs.functional.calendar.personalcalendar;

import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CS_Calendar_PersonalCalendar_Edit extends Calendar {
	
	@BeforeMethod
	public void beforeTest(){
		getDriverAutoSave();
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
	
	//Case61: Enable public access to Personal Calendar URL
	@Test
	public void test61_EnablePublicAccessToPersonalCalendar(){
		String calendarName = "CS_PersonalCalendar_Edit_61";
		String desc = "CS_PersonalCalendar_Edit_61";
		String color = "OrangeRed ColorCell";
		
		info("Add a calendar in personal calendar"); 
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		goToPublicAccessLinkOfCalendar(calendarName);
		driver.get(baseUrl);
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
}
