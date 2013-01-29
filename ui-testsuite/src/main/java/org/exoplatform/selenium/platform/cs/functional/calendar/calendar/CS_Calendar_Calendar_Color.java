package org.exoplatform.selenium.platform.cs.functional.calendar.calendar;

import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.cs.Event.quickAddEvent;
import static org.exoplatform.selenium.platform.cs.Task.quickAddTask;
import static org.exoplatform.selenium.platform.cs.Task.getColorOfTask;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author lientm
 * @date 22/01/2013
 */
public class CS_Calendar_Calendar_Color extends Calendar {
	
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
	
	public static void addTaskViewCheckColor(String eventName, String  taskName, String calendarName, String color){
		info("Add new event and task for this calendar");
		quickAddEvent(eventName, null, calendarName);
		quickAddTask(taskName, calendarName);
		getColorOfTask(eventName).equalsIgnoreCase(color.split(" ")[0]);
		getColorOfTask(taskName).equalsIgnoreCase(color.split(" ")[0]);
	}
	
	public static void changeColorAndCheck(String eventName, String  taskName, String calendarName, String newColor){
		info("Change color of calendar");
		changeCalendarColor(calendarName, newColor);
		getColorOfTask(eventName).equalsIgnoreCase(newColor);
		getColorOfTask(taskName).equalsIgnoreCase(newColor);
	}
	
	public static void viewColorOfCalendarTaskView(String calendarName, String eventName, String taskName, String color){
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		getColorOfTask(eventName).equalsIgnoreCase(color.split(" ")[0]);
		getColorOfTask(taskName).equalsIgnoreCase(color.split(" ")[0]);
	}
	
	//Case01: Change personal calendar color
	@Test
	public void test01_ChangePersonalCalendarColor(){
		String calendarName = "CS_Calendar_Color_01";
		String desc = "CS_Calendar_Color_description_01";
		String color = "OrangeRed ColorCell";
		String newColor = "Red";
		String eventName = "CS_Calendar_Color_event_01";
		String taskName = "CS_Calendar_Color_task_01";
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarName, desc, null, color);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		
		addTaskViewCheckColor(eventName, taskName, calendarName, color);		
		changeColorAndCheck(eventName, taskName, calendarName, newColor);		
		
		deleteCalendar(calendarName);
	}
	
	//Case02: Change group calendar color
	@Test
	public void test02_ChangeGroupCalendarColor(){
		String calendarName = "CS_Calendar_Color_02";
		String desc = "CS_Calendar_Color_description_02";
		String color = "OrangeRed ColorCell";
		String newColor = "Red";
		String eventName = "CS_Calendar_Color_event_02";
		String taskName = "CS_Calendar_Color_task_02";
		String[] groupUser = {"/developers"};
		String[] user = {"john"};
		String[] membership = {};
		
		info("Add a calendar in group calendar");
		addCalendar(calendarName, desc, "My Group", color, groupUser, user, membership);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		
		addTaskViewCheckColor(eventName, taskName, calendarName + " (" + groupUser[0].substring(1) + ")", color);
		changeColorAndCheck(eventName, taskName, calendarName, newColor);

		deleteCalendar(calendarName);
	}
	
	//Case03: Change shared calendar color
	@Test
	public void test03_ChangeSharedCalendarColor(){
		String calendarName = "CS_Calendar_Color_03";
		String desc = "CS_Calendar_Color_description_03";
		String color = "SeaGreen ColorCell";
		String newColor = "Red";
		String eventName = "CS_Calendar_Color_event_03";
		String taskName = "CS_Calendar_Color_task_03";
		String[] user = {"demo"};
		String[] group = {};
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarName, desc, "My Group", color);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		addTaskViewCheckColor(eventName, taskName, calendarName, color);
		
		info("shared this calendar");
		shareCalendar(calendarName, user, group, true);
		signOut();
		
		info("Check user "+ user[0] + " can see calendar in Shared Calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForAndGetElement(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		viewColorOfCalendarTaskView("john- " + calendarName, eventName, taskName, color);
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		changeColorAndCheck(eventName, taskName, calendarName, newColor);
		signOut();
		
		info("Check color of calendar, task, view that are not changed when view by use " + user[0]);
		signIn("demo", "gtn");
		goToCalendarPage();
		viewColorOfCalendarTaskView("john- " + calendarName, eventName, taskName, color);
		signOut();
		
		info("Delete calendar");
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
}
