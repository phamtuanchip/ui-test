package org.exoplatform.selenium.platform.cs.functional.calendar.calendar;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToNewStaff;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToUsersAndGroupsManagement;
import static org.exoplatform.selenium.platform.UserGroupManagement.deleteUser;
import static org.exoplatform.selenium.platform.UserGroupManagement.searchUser;
import static org.exoplatform.selenium.platform.ManageAccount.addNewUserAccount;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.checkAlertWarning;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.checkAlertInfo;
import static org.exoplatform.selenium.platform.cs.functional.calendar.calendar.CS_Calendar_Calendar_Color.addTaskViewCheckColor;
import static org.exoplatform.selenium.platform.cs.functional.calendar.calendar.CS_Calendar_Calendar_Color.changeColorAndCheck;
import static org.exoplatform.selenium.platform.cs.Task.importTaskEventToCalendar;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CS_Calendar_Calendar_Share extends Calendar {
	
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
	
	public static void checkUserHaveEditSharedCalendar(String eventName, String taskName, 
			String calendarName, String color, String newColor, String path, String fileName){
		info("user can add task/event, change color calendar in shared group");
		addTaskViewCheckColor(eventName, taskName, "john - " + calendarName, color);
		changeColorAndCheck(eventName, taskName, "john- " + calendarName, newColor);
		
		info("User can import task/event, export calendar");
		importTaskEventToCalendar("john- " + calendarName, 1, path);
		exportCalendar("john- " + calendarName, fileName, 1);
	}
	
	public static void checkUserNotHaveEditSharedCalendar(String calendarName, String newColor){
		String id = getIDOfCalendar(calendarName);
		String color = getColorOfCalendar(calendarName);
		
		info("user can not add task/event for shared calendar in shared group");
		goToTask();
		waitForElementNotPresent(By.xpath(ELEMENT_SHARED_CALENDAR_OPTION_ADD_TASK.replace("${calendarId}", id)));
		cancel();
		goToEvent();
		waitForElementNotPresent(By.xpath(ELEMENT_SHARED_CALENDAR_OPTION_ADD_EVENT.replace("${calendarId}", id)));
		cancel();
		
		info("User can not import task/event, export calendar");
		executeActionCalendar(id, "ImportCalendar", color, 1);
		waitForElementNotPresent(By.xpath(ELEMENT_SHARED_CALENDAR_OPTION_IMPORT.replace("${calendarId}", id)));
		cancel();
		
		executeActionCalendar(id, "ExportCalendar", color, 1);
		checkAlertInfo(MSG_EDIT_CALENDAR_NOT_HAVE_RIGHT);
		
		info("User demo just can change color calendar");
		changeCalendarColor(calendarName, newColor);
	}
	
	public void deleteCalendarWithJohn(String calendarName){
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
	
	//Case01: Share a personal calendar
	@Test(groups={"pending"})
	public void test01_SharePersonalCalendar(){
		String calendarName = "CS_Calendar_Share_01";
		String desc = "CS_Calendar_Share_description_01";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		
		addCalendarAndShare(calendarName, desc, color, user, group, true);
		signOut();
		
		info("user demo see calendar in shared group");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		signOut();
		
		deleteCalendarWithJohn(calendarName);
	}
	
	//Case02: Share an imported calendar
	@Test(groups={"pending"})
	public void test02_ShareImportedCalendar(){
		String path = "TestData/CS_Calendar_Share_02.ics";
		String calendarName = "CS_Calendar_Share_02";
		String desc = "CS_Calendar_Share_description_02";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		By elementCalendar = By.xpath(ELEMENT_MY_CAL_ICON.replace("{$calendar}", calendarName));
		By element_shared = By.xpath(ELEMENT_SHARED_ICON.replace("${calendarName}", calendarName));
		
		importCalendar(path, calendarName, desc, "My Group", color);
		waitForElementPresent(elementCalendar);
		info("Import calendar successfully");
		
		info("shared this calendar");
		shareCalendar(calendarName, user, group, true);
		waitForElementPresent(element_shared);
		signOut();
		
		info("user demo see calendar in shared group");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		signOut();
		
		deleteCalendarWithJohn(calendarName);
	}
	
	//Case03: Share a shared calendar
	@Test(groups={"pending"})
	public void test03_ShareSharedCalendar(){
		String calendarName = "CS_Calendar_Share_03";
		String desc = "CS_Calendar_Share_description_03";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};

		addCalendarAndShare(calendarName, desc, color, user, group, true);
		signOut();
		
		info("user demo see calendar in shared group");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		goToShareCalendar("john- " + calendarName);
		waitForTextPresent("Unknown error");
		click(ELEMENT_OK_BUTTON);
		waitForElementNotPresent(ELEMENT_TITLE_SHARE_CAL_FORM);
		signOut();
		
		deleteCalendarWithJohn(calendarName);
	}
	
	//Case04: Share a group calendar
	@Test(groups={"pending"})
	public void test04_ShareGroupCalendar(){
		String calendarName = "CS_Calendar_Shared_04";
		String desc = "CS_Calendar_Shared_description_04";
		String color = "OrangeRed ColorCell";
		String[] groupUser = {"/developers"};
		String[] user = {"demo"};
		
		info("Add a calendar in group calendar");
		addCalendar(calendarName, desc, "My Group", color, groupUser, user);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		
		goToShareCalendar(calendarName);
		waitForTextPresent("Unknown error");
		click(ELEMENT_OK_BUTTON);
		waitForElementNotPresent(ELEMENT_TITLE_SHARE_CAL_FORM);
		
		deleteCalendar(calendarName);
	}
	
	//Case05: Share calendar with invalid user
	@Test
	public void test05_ShareCalendar_InvalidUser(){
		String calendarName = "CS_Calendar_Share_05";
		String desc = "CS_Calendar_Share_description_05";
		String color = "SeaGreen ColorCell";
		String[] user = {"abc"};
		String[] group = {};
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarName, desc, "My Group", color);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		
		info("shared this calendar");
		goToShareCalendar(calendarName);
		inputFormShareCalendar(user, group, true, 0);
		save();
		checkAlertWarning(MSG_SHARED_INVALID_USER.replace("${user}", user[0]));
		cancel();
		
		deleteCalendar(calendarName);
	}
	
	//Case06: Share calendar for user with edit permission
	@Test(groups={"pending"})
	public void test06_ShareCalendar_UserWithEditPermission(){
		String calendarName = "CS_Calendar_Share_06";
		String desc = "CS_Calendar_Share_description_06";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		String newColor = "Red";
		String eventName = "CS_Calendar_Shared_event_06";
		String taskName = "CS_Calendar_Shared_task_06";
		String fileName = "test06_ShareCalendar_UserWithEditPermission";
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
		
		deleteCalendarWithJohn(calendarName);
	}
	
	//Case07: Share calendar for user without edit permission
	@Test(groups={"pending"})
	public void test07_ShareCalendar_UserWithoutEditPermission(){
		String calendarName = "CS_Calendar_Share_07";
		String desc = "CS_Calendar_Share_description_07";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		String newColor = "Red";

		addCalendarAndShare(calendarName, desc, color, user, group, false);
		signOut();
		
		info("user demo see calendar in shared group");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		
		checkUserNotHaveEditSharedCalendar("john- " + calendarName, newColor);
		info("User can remove calendar");
		deleteCalendar("john- " + calendarName);
		signOut();
		
		deleteCalendarWithJohn(calendarName);
	}
	
	//Cas08: Share calendar without specifying any user
	@Test
	public void test08_ShareCalendar_WithoutAnyUser(){
		String calendarName = "CS_Calendar_Share_08";
		String desc = "CS_Calendar_Share_description_08";
		String color = "SeaGreen ColorCell";
		String[] user = {};
		String[] group = {};
		
		info("Add a calendar in personal calendar");
		quickAddCalendar(calendarName, desc, "My Group", color);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		
		info("shared this calendar");
		goToShareCalendar(calendarName);
		inputFormShareCalendar(user, group, false);
		save();
		checkAlertInfo(MSG_SHARED_NULL_USER);
		cancel();
		
		deleteCalendar(calendarName);
	}
	
	//Case09: Change Edit permission on a shared calendar from Yes to No
	@Test(groups={"pending"})
	public void test09_ChangeEditPermissionOnSharedCalendar_FromYesToNo(){
		String calendarName = "CS_Calendar_Share_09";
		String desc = "CS_Calendar_Share_description_09";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		String newColor = "Red";
		String eventName = "CS_Calendar_Shared_event_09";
		String taskName = "CS_Calendar_Shared_task_09";
		String fileName = "test09_ShareCalendar_UserWithEditPermission";
		String path = "TestData/CS_Calendar_Share_02.ics";

		addCalendarAndShare(calendarName, desc, color, user, group, true);
		signOut();
		
		info("user demo see calendar in shared group");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));	
		checkUserHaveEditSharedCalendar(eventName, taskName, calendarName, color, newColor, path, fileName);
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		editPermissionSharedCalendar(calendarName, "demo", false);
		signOut();
		
		signIn("demo", "gtn");
		goToCalendarPage();
		checkUserNotHaveEditSharedCalendar("john- " + calendarName, color.split(" ")[0]);
		signOut();
		
		deleteCalendarWithJohn(calendarName);
	}
	
	//Case10: Change Edit permission on a shared calendar from No to Yes
	@Test(groups={"pending"})
	public void test10_ChangeEditPermissionOnSharedCalendar_FromNoToYes(){
		String calendarName = "CS_Calendar_Share_10";
		String desc = "CS_Calendar_Share_description_10";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		String newColor = "Red";
		String eventName = "CS_Calendar_Shared_event_10";
		String taskName = "CS_Calendar_Shared_task_10";
		String fileName = "test09_ShareCalendar_UserWithEditPermission";
		String path = "TestData/CS_Calendar_Share_02.ics";

		addCalendarAndShare(calendarName, desc, color, user, group, false);
		signOut();
		
		info("user demo see calendar in shared group");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		checkUserNotHaveEditSharedCalendar("john- " + calendarName, newColor);
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		editPermissionSharedCalendar(calendarName, "demo", true);
		signOut();
		
		signIn("demo", "gtn");
		goToCalendarPage();
		checkUserHaveEditSharedCalendar(eventName, taskName, calendarName, newColor, color.split(" ")[0], path, fileName);
		signOut();
		
		deleteCalendarWithJohn(calendarName);
	}
	
	//Case11: Remove shared permission on a shared calendar
	@Test(groups={"pending"})
	public void test11_RemoveSharedPemission(){
		String calendarName = "CS_Calendar_Share_11";
		String desc = "CS_Calendar_Share_description_11";
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
		
		info("Remove shared permission of user");
		signIn("john", "gtn");
		goToCalendarPage();
		deleteShareCalendar(calendarName, user[0]);
		cancel();
		signOut();
		
		info("User demo can not see calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementNotPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		signOut();
		
		deleteCalendarWithJohn(calendarName);
	}

	//Case12: Remove shared status for a shared calendar
	@Test(groups={"pending"})
	public void test12_RemoveSharedStatus(){
		String calendarName = "CS_Calendar_Share_12";
		String desc = "CS_Calendar_Share_description_12";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo", "mary"};
		String[] group = {};
		By element_shared = By.xpath(ELEMENT_SHARED_ICON.replace("${calendarName}", calendarName));
		
		addCalendarAndShare(calendarName, desc, color, user, group, false);
		
		deleteShareCalendar(calendarName, user[0], user[1]);
		cancel();

		info("Shared icon is not existed");
		waitForElementNotPresent(element_shared);
		deleteCalendar(calendarName);
	}
	
	//Case13: Check a shared calendar in case delete account of its shared users.
	@Test(groups={"pending"})
	public void test13_DeleteAccountIsSharedUserOfSharedCalendar(){
		String username = "lientm";
		String password = "lientm@123";
		String firstName = "tm";
		String lastName = "lien";
		String email = "lientm@exoplatform.com";
		String calendarName = "CS_Calendar_Share_13";
		String desc = "CS_Calendar_Share_description_13";
		String color = "SeaGreen ColorCell";
		String[] user = {username};
		String[] group = {};
		
		info("Add new user");
		goToNewStaff();
		addNewUserAccount(username, password, password, firstName,
				lastName, email, "", "", true);
		
		goToCalendarPage();
		addCalendarAndShare(calendarName, desc, color, user, group, true);
		
		info("Delete shared user");
		goToUsersAndGroupsManagement();
		searchUser(username, "User Name");
		deleteUser(username);
		
		info("Shared user is not listed in shared permission list");
		goToCalendarPage();
		goToShareCalendar(calendarName);
		waitForElementNotPresent(ELEMENT_EDIT_SHARE_ICON.replace("{$user}", username));
		cancel();
		
		deleteCalendar(calendarName);
	}
}
