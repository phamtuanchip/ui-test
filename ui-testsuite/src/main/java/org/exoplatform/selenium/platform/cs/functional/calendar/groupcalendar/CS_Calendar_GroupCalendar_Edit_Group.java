package org.exoplatform.selenium.platform.cs.functional.calendar.groupcalendar;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CS_Calendar_GroupCalendar_Edit_Group extends Calendar {
	
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
	
	//Case57: Show a Calendar in Group(s)/Person
	@Test
	public void test57_ShowCalendarInGroup(){
		String calendarName = "CS_GroupCalendar_Edit_Group_57";
		String desc = "CS_GroupCalendar_Edit_Group_57";
		String color = "OrangeRed ColorCell";
		String[] groupUser1 = {"/developers"};
		String[] groupUser2 = {"/platform/web-contributors"};
		String[] user = {};
		By calendarGroup1 = By.xpath(ELEMENT_CALENDAR_IN_GROUP_CALENDAR.replace("${group}", groupUser1[0]).replace("${calendarName}", calendarName));
		By calendarGroup2 = By.xpath(ELEMENT_CALENDAR_IN_GROUP_CALENDAR.replace("${group}", groupUser2[0]).replace("${calendarName}", calendarName));

		info("Add a calendar in group calendar");
		addCalendar(calendarName, desc, "My Group", color, groupUser1, user);
		waitForElementPresent(calendarGroup1);
		
		info("add group for calendar");
		editCalendar(calendarName, null, null, null, null, groupUser2, user);
		waitForElementPresent(calendarGroup2);
		signOut();
		
		info("User demo belong to developers group can see calendar in group calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(calendarGroup1);
		signOut();
		
		info("User mary belong to web-contributors group can see calendar in group calendar");
		signIn("james", "gtn");
		goToCalendarPage();
		waitForElementPresent(calendarGroup2);
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
	
	//Case58: Edit a Calendar in Group(s)/Person
	@Test
	public void test58_EditCalendarGroup(){
		String calendarName = "CS_GroupCalendar_Edit_Group_58";
		String calendarNew = "CS_GroupCalendar_Edit_Group_58_New";
		String desc = "CS_GroupCalendar_Edit_Group_58";
		String color = "OrangeRed ColorCell";
		String[] groupUser1 = {"/developers"};
		String[] groupUser2 = {"/platform/web-contributors"};
		String[] groupUser3 = {"/platform/users"};
		String[] user1 = {};
		String[] user2 = {"james"};
		String[] user3 = {"john"};
		By calendarGroup1 = By.xpath(ELEMENT_CALENDAR_IN_GROUP_CALENDAR.replace("${group}", groupUser1[0]).replace("${calendarName}", calendarName));
		By calendarGroup2 = By.xpath(ELEMENT_CALENDAR_IN_GROUP_CALENDAR.replace("${group}", groupUser2[0]).replace("${calendarName}", calendarName));
		By calendarGroupNew = By.xpath(ELEMENT_CALENDAR_IN_GROUP_CALENDAR.replace("${group}", groupUser3[0]).replace("${calendarName}", calendarNew));
		
		info("Add a calendar in group calendar");
		addCalendar(calendarName, desc, "My Group", color, groupUser1, user1);
		waitForElementPresent(calendarGroup1);
		
		info("add group for calendar");
		editCalendar(calendarName, null, null, null, null, groupUser2, user2);
		waitForElementPresent(calendarGroup2);
		signOut();
		
		info("User james belong to web-contributors can edit calendar");
		signIn("james", "gtn");
		goToCalendarPage();
		editCalendar(calendarName, calendarNew, null, null, null, groupUser3, user3);
		waitForElementPresent(calendarGroupNew);
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarNew);
	}
}
