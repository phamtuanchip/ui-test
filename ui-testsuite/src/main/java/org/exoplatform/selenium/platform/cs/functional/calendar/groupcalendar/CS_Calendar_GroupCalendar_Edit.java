package org.exoplatform.selenium.platform.cs.functional.calendar.groupcalendar;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CS_Calendar_GroupCalendar_Edit extends Calendar {
	
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
		
	//Case62: Enable public access to Group Calendar URL
	@Test
	public void test62_EnablePublicAccessToGroupCalendar(){
		String calendarName = "CS_GroupCalendar_Edit_62";
		String desc = "CS_GroupCalendar_Edit_62";
		String color = "OrangeRed ColorCell";
		String[] groupUser = {"/developers"};
		String[] user = {};
		
		info("Add a calendar in group calendar"); 
		addCalendar(calendarName, desc, "My Group", color, groupUser, user);
		
		goToPublicAccessLinkOfCalendar(calendarName);
		driver.get(baseUrl);
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
}
