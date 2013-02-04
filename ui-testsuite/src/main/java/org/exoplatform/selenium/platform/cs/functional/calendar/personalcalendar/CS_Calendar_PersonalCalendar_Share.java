package org.exoplatform.selenium.platform.cs.functional.calendar.personalcalendar;

import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.cs.functional.calendar.calendar.CS_Calendar_Calendar_Share.checkUserHaveEditSharedCalendar;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CS_Calendar_PersonalCalendar_Share extends Calendar {
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
	
	//Case59: Share a Personal Calendar in Group(s)/Person without Editing permission
	@Test(groups={"pending"})
	public void test59_SharePersonalCalendarWithoutEditPermission(){
		String calendarName = "CS_PersonalCalendar_Share_59";
		String desc = "CS_PersonalCalendar_Share_description_59";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		
		addCalendarAndShare(calendarName, desc, color, user, group, false);
		signOut();
		
		info("user demo see calendar in shared group");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
	
	//Case60: Share a Personal Calendar in Group(s)/Person with Editing permission
	@Test(groups={"pending"})
	public void test60_SharePersonalCalendarWithEditPermission(){
		String calendarName = "CS_PersonalCalendar_Share_60";
		String desc = "CS_PersonalCalendar_Share_description_60";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		String newColor = "Red";
		String eventName = "CS_PersonalCalendar_Shared_event_60";
		String taskName = "CS_PersonalCalendar_Shared_task_60";
		String fileName = "test60_SharePersonalCalendarWithEditPermission";
		String path = "TestData/CS_Calendar_Share_02.ics";
		
		addCalendarAndShare(calendarName, desc, color, user, group, true);
		signOut();
		
		info("user demo see calendar in shared group");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		checkUserHaveEditSharedCalendar(eventName, taskName, calendarName, color, newColor, path, fileName);
		info("User can remove calendar");
		deleteCalendar("john- " + calendarName);
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
}
