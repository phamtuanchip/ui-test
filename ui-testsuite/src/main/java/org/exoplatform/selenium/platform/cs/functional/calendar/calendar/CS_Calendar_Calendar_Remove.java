package org.exoplatform.selenium.platform.cs.functional.calendar.calendar;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.cs.functional.calendar.calendar.CS_Calendar_Calendar_Color.addTaskViewCheckColor;
import static org.exoplatform.selenium.platform.cs.Task.getTaskFromViewMode;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.checkAlertInfo;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CS_Calendar_Calendar_Remove extends Calendar {
	
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
	 
	public static void removeCalendarTaskEvent(String calendarName, String taskName, String eventName){
		deleteCalendar(calendarName);
		waitForElementNotPresent(getTaskFromViewMode(taskName));
		waitForElementNotPresent(getTaskFromViewMode(eventName));
	}
	
	//Case01: Remove a personal calendar  from left pane
	@Test
	public void test01_RemovePersonalCalendarFromLeftPanel(){
		String calendarName = "CS_Calendar_Remove _01";
		String desc = "CS_Calendar_Remove _description_01";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Remove_event_01";
		String taskName = "CS_Calendar_Remove_task_01";
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarName, desc, "My Group", color);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		
		addTaskViewCheckColor(eventName, taskName, calendarName, color);

		removeCalendarTaskEvent(calendarName, taskName, eventName);
	}
	
	//Case02: Remove a shared calendar by the shared user without edit right
	@Test(groups={"pending"})
	public void test02_RemoveSharedCalendar_BySharedUserWithoutEditRight(){
		String calendarName = "CS_Calendar_Remove_02";
		String desc = "CS_Calendar_Remove_description_02";
		String color = "SeaGreen ColorCell";
		String eventName = "CS_Calendar_Remove_event_02";
		String taskName = "CS_Calendar_Remove_task_02";
		String[] user = {"demo", "mary"};
		String[] group = {};
		By element_shared = By.xpath(ELEMENT_SHARED_ICON.replace("${calendarName}", calendarName));
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarName, desc, "My Group", color);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		addTaskViewCheckColor(eventName, taskName, calendarName, color);
		
		info("shared this calendar");
		shareCalendar(calendarName, user, group, false);
		signOut();
		
		info("Remove calendar with user demo");
		signIn("demo", "gtn");
		goToCalendarPage();
		removeCalendarTaskEvent("john- " + calendarName, taskName, eventName);
		signOut();
		
		info("Check user john still see shared icon calendar");
		signIn("john", "gtn");
		goToCalendarPage();
		waitForElementPresent(element_shared);
		signOut();
		
		info("Remove calendar with user mary");
		signIn("mary", "gtn");
		goToCalendarPage();
		removeCalendarTaskEvent("john- " + calendarName, taskName, eventName);
		signOut();
		
		info("Check user john does not see shared icon calendar");
		signIn("john", "gtn");
		goToCalendarPage();
		waitForElementNotPresent(element_shared);
		deleteCalendar(calendarName);
	}
	
	//Case03: Remove a shared calendar by the shared user with edit right
	@Test(groups={"pending"})
	public void test03_RemoveSharedCalendar_BySharedUserWithEditRight(){
		String calendarName = "CS_Calendar_Remove_03";
		String desc = "CS_Calendar_Remove_description_03";
		String color = "SeaGreen ColorCell";
		String eventName = "CS_Calendar_Remove_event_03";
		String taskName = "CS_Calendar_Remove_task_03";
		String[] user = {"demo", "mary"};
		String[] group = {};
		By element_shared = By.xpath(ELEMENT_SHARED_ICON.replace("${calendarName}", calendarName));
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarName, desc, "My Group", color);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		addTaskViewCheckColor(eventName, taskName, calendarName, color);
		
		info("shared this calendar");
		shareCalendar(calendarName, user, group, true);
		signOut();
		
		info("Remove calendar with user demo");
		signIn("demo", "gtn");
		goToCalendarPage();
		removeCalendarTaskEvent("john- " + calendarName, taskName, eventName);
		signOut();
		
		info("Check user john still see shared icon calendar");
		signIn("john", "gtn");
		goToCalendarPage();
		waitForElementPresent(element_shared);
		signOut();
		
		info("Remove calendar with user mary");
		signIn("mary", "gtn");
		goToCalendarPage();
		removeCalendarTaskEvent("john- " + calendarName, taskName, eventName);
		signOut();
		
		info("Check user john does not see shared icon calendar");
		signIn("john", "gtn");
		goToCalendarPage();
		waitForElementNotPresent(element_shared);
		deleteCalendar(calendarName);
	}
	
	//Case04: Remove group calendar by user who does not have edit right
	@Test
	public void test04_RemoveGroupCalendar_ByUserWithoutEditRight(){
		String calendarName = "CS_Calendar_Remove_04";
		String desc = "CS_Calendar_Remove_description_04";
		String color = "OrangeRed ColorCell";
		String[] groupUser = {"/developers"};
		String[] user = {};
		String[] membership = {};
		
		info("Add a calendar in group calendar");
		addCalendar(calendarName, desc, "My Group", color, groupUser, user, membership);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		signOut();

		info("User demo belong to development group but does not have edit calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName, false);
		checkAlertInfo(MSG_REMOVE_CALENDAR_NOT_HAVE_RIGHT);
		waitForElementPresent(ELEMENT_CALENDAR_ICON.replace("{$calendar}", calendarName));
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
	
	//Case05: Remove group calendar by user who has edit right
	@Test
	public void test05_RemoveGroupCalendar_ByUserHasEditRight(){
		String calendarName = "CS_Calendar_Remove_05";
		String desc = "CS_Calendar_Remove_description_05";
		String color = "OrangeRed ColorCell";
		String[] groupUser = {"/developers"};
		String eventName = "CS_Calendar_Remove_event_05";
		String taskName = "CS_Calendar_Remove_task_05";
		String[] user = {"demo"};
		String[] membership = {};
		
		info("Add a calendar in group calendar");
		addCalendar(calendarName, desc, "My Group", color, groupUser, user, membership);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		addTaskViewCheckColor(eventName, taskName, calendarName + " (" + groupUser[0].substring(1) + ")", color);
		signOut();

		info("User demo have edit calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		removeCalendarTaskEvent(calendarName, taskName, eventName);
	}
}
