package org.exoplatform.selenium.platform.cs.functional.calendar.calendar;

import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.cs.View.goToWeek;
import static org.exoplatform.selenium.platform.cs.Task.viewTaskByMouseOver;
import static org.exoplatform.selenium.platform.cs.Task.getColorOfTask;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.checkAlertWarning;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CS_Calendar_Calendar_RemoteCall extends Calendar {
	public By ELEMENT_WEEK_CALENDAR_TEST = By.xpath("//*[@id='UIWeekView']//div[contains(text(),'Week') and contains(text(),'4')]");
	public String ELEMENT_TASK_NAME_TEST = "Task1";
	public String ELEMENT_VIEW_NAME_TEST = "Event1";
	public String ELEMENT_REMOTE_SELECT_COLOR = "//div[@class='UIFormColorPicker']/*[@selectedcolor='${color} ']";
	public By ELEMENT_BEFOREDATE_SELECTED_OPTION = By.xpath("//*[@id='beforeDate']/option[@selected='selected']");
	public By ELEMENT_AFTERDATE_SELECTED_OPTION = By.xpath("//*[@id='afterDate']/option[@selected='selected']");
	public String user = "exomailtest01";
	public String pass = "exoadmin";
	
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
	
	public void addRemoteCalendarAndCheck(int type, String url, String calendarName, String desc, String color, String user, String pass){
		info("Add a remote calendar");
		addRemoteCalendar(type, url, calendarName, desc, color, null, null, true, user, pass);
		refreshCalendar(calendarName, 0);
		assert getColorOfCalendar(calendarName).equalsIgnoreCase(color.split(" ")[0]);
		
		goToWeekViewTab();
		goToWeek(ELEMENT_WEEK_CALENDAR_TEST, false);
		goToMonthViewTab();
		viewTaskByMouseOver(ELEMENT_TASK_NAME_TEST, 2);
		assert getColorOfTask(ELEMENT_TASK_NAME_TEST, 2).equalsIgnoreCase(color.split(" ")[0]);
		viewTaskByMouseOver(ELEMENT_VIEW_NAME_TEST, 2);
		assert getColorOfTask(ELEMENT_VIEW_NAME_TEST, 2).equalsIgnoreCase(color.split(" ")[0]);
	}
	
	//Case49: Add remote calendar with ics type in case valid data entry
	@Test
	public void test49_AddRemoteCalendarWithIcsType_ValidData(){
		String url = "https://www.google.com/calendar/ical/exomailtest01%40gmail.com/private-47a54cd73a4d6de427f810e501bee715/basic.ics";
		String calendarName = "CS_Calendar_RemoteCall_49";
		String desc = "CS_Calendar_RemoteCall_description_49";
		String color = "Yellow ColorCell";
		
		addRemoteCalendarAndCheck(0, url, calendarName, desc, color, user, pass);
		deleteCalendar(calendarName);
	}
	
	//Case50: Add remote calendar with calDav type in case valid data entry
	@Test
	public void test50_AddRemoteCalendarWithCalDavType_ValidData(){
		String url = "https://www.google.com/calendar/dav/exomailtest01@gmail.com/events/";
		String calendarName = "CS_Calendar_RemoteCall_50";
		String desc = "CS_Calendar_RemoteCall_description_50";
		String color = "Yellow ColorCell";
		
		addRemoteCalendarAndCheck(1, url, calendarName, desc, color, user, pass);	
		deleteCalendar(calendarName);
	}
	
	//Case51: Add remote calendar in case input invalid url
	@Test
	public void test51_AddRemoteCalendarWithCalDavType_InvalidData(){
		subscribeCalendar(1, null);
		next();
		checkAlertWarning(MSG_ADD_REMOETE_EMPTY_URL);
		
		inputFormSubscribeCalendar(1, "test51_AddRemoteCalendarWithCalDavType_InvalidData");
		next();
		checkAlertWarning(MSG_ADD_REMOTE_INVALID_URL);
		cancel();
	}
	
	//Case52: Add remote calendar in case do not input required field in the Remote Calendar form
	@Test(groups={"pending"}) //refer issue EXOGTN-1191
	public void test52_AddRemoteCalendar_EmptyRequireField_RemoteCalendarForm(){
		String url = "https://www.google.com/calendar/dav/exomailtest01@gmail.com/events/";
		String calendarName = "CS_Calendar_RemoteCall_52";
		
		info("Add remote calendar with empty calendar name");
		subscribeCalendar(1, url);
		next();
		save();
		checkAlertWarning(MSG_ADD_REMOTE_EMPTY_NAME);
		
		info("Add remote calendar with empty user name");
		inputFormRemoteCalendar(calendarName, null, null, null, null, true, null, pass);
		save();
		checkAlertWarning(MSG_ADD_REMOTE_EMPTY_USER);
		
		info("Add remote calendar with empty pass");
		inputFormRemoteCalendar(null, null, null, null, null, true, user, "");
		save();
		checkAlertWarning(MSG_ADD_REMOTE_EMPTY_PASS);
		cancel();
	}
	
	//Case53: Add remote calendar in case invalid authentication user name and password
	@Test
	public void test53_AddRemoteCalendar_InvalidAuthentication(){
		String url = "https://www.google.com/calendar/dav/exomailtest01@gmail.com/events/";
		String calendarName = "CS_Calendar_RemoteCall_53";
		
		info("Add remote calendar with invalid username");
		subscribeCalendar(1, url);
		next();
		inputFormRemoteCalendar(calendarName, null, null, null, null, true, "exomail", pass);
		save();
		checkAlertWarning(MSG_ADD_REMOTE_EMPTY_PASS);
		
		info("Add remote calendar with invalid password");
		inputFormRemoteCalendar(null, null, null, null, null, true, user, "exo");
		save();
		checkAlertWarning(MSG_ADD_REMOTE_EMPTY_PASS);
		cancel();
	}
	
	//Case54: Check Back function when add a remote calendar
	@Test
	public void test54_CheckBackFuntion(){
		String url = "https://www.google.com/calendar/dav/exomailtest01@gmail.com/events/";
		String calendarName = "CS_Calendar_RemoteCall_54";
		String desc = "CS_Calendar_RemoteCall_description_54";
		String color = "Yellow ColorCell";
		String date = "1 Week";
		String user = "exomailtest";
		String pass = "exoadmin";
		By elementColor = By.xpath(ELEMENT_REMOTE_SELECT_COLOR.replace("${color}", color.split(" ")[0]));
		
		info("Add remote calendar with wrong user/pass");
		subscribeCalendar(1, url);
		next();
		inputFormRemoteCalendar(calendarName, desc, color, date, date, true, user, pass);
		save();
		checkAlertWarning(MSG_ADD_REMOTE_EMPTY_PASS);
		
		info("After back, all input data is kept");
		getValue(ELEMENT_NAME_REMOTE_CAL_INPUT).equalsIgnoreCase(calendarName);
		getValue(ELEMENT_DESC_REMOTE_CAL_INPUT).equalsIgnoreCase(desc);
		click(ELEMENT_COLOR_CAL_INPUT); 
		waitForElementPresent(elementColor);
		getText(ELEMENT_AFTERDATE_SELECTED_OPTION).equalsIgnoreCase(date);
		getText(ELEMENT_BEFOREDATE_SELECTED_OPTION).equalsIgnoreCase(date);
		getValue(ELEMENT_USERNAME_REMOTE_INPUT).equalsIgnoreCase(user);
		getValue(ELEMENT_PASS_REMOTE_INPUT).equalsIgnoreCase(pass);
		
		cancel();
	}
	
	//case55, 56: NA -> not found auto refresh
}
