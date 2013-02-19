package org.exoplatform.selenium.platform.cs.functional.calendar.event;

import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToUsersAndGroupsManagement;
import static org.exoplatform.selenium.platform.UserGroupManagement.searchUser;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.cs.EventCategory.addEventCategory;
import static org.exoplatform.selenium.platform.cs.EventCategory.deleteEventCategory;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.checkAlertInfo;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.checkAlertWarning;
import static org.exoplatform.selenium.platform.ks.ForumBase.ELEMENT_DELETE;
import static org.exoplatform.selenium.platform.ks.ForumBase.checkFileExisted;
import static org.exoplatform.selenium.platform.ks.KsBase.selectUserPermission;
import static org.exoplatform.selenium.platform.ks.ForumBase.goToMail;
import static org.exoplatform.selenium.platform.UserGroupManagement.editUser;

import java.util.List;

import org.exoplatform.selenium.platform.cs.View;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CS_Calendar_Event_Add extends View {
	public By elementTime_DayWeekView = By.xpath("//*[@id='UIWeekViewGrid']//td[@startfull='" + getCurrentDate("EEE MMM dd yyyy") + " 12:00:00']");
	public By elementTime_MonthView = By.xpath("//*[@id='UIMonthViewGrid']//td[@class='CalendarContentNomal UICellBlock' and @starttimefull='" + getCurrentDate("EEE MMM dd yyyy") + " 00:00:00']");
	public String FROM = getCurrentDate("MM/dd/yyyy") + " ${time_from}";
	public String TO = getCurrentDate("MM/dd/yyyy") + " ${time_to}";
	public String ELEMENT_CATEGORY_SELECT_OPTION_QUICK_ADD = "//*[@id='UIQuickAddEvent']//select[@id='category']/option[@selected='selected' and text()='${category}']";
	public String ELEMENT_CATEGORY_SELECT_OPTION_DETAIL_ADD = "//*[@id='eventDetail']//select[@id='category']/option[@selected='selected' and text()='${category}']";	
	public String ELEMENT_EVENT_REPEAT = "//*[@title='${eventName}' and contains(@starttimefull, '${time_from}:00') and contains(@endtimefull,'${time_to}:00')]";
	public By ELEMENT_EVENT_PARENT = By.xpath("//*[@id='UIMonthView']//*[@class='RowContainerDay MainWorkingPanel']");
	public String OLD_MAIL_ADDRESS_DEMO = "jack.miller@acme.exoplatform.com";
	public By ELEMENT_YES_LINK_IN_MAIL = By.xpath("//a[@target='_blank' and text()='Yes']");
	public By ELEMENT_NO_LINK_IN_MAIL = By.xpath("//a[@target='_blank' and text()='No']");
	public By ELEMENT_YES_IMPORT_LINK_IN_MAIL = By.xpath("//a[@target='_blank' and text()='Import to your eXo Calendar']");
	public By ELEMENT_NOT_SURE_LINK_IN_MAIL = By.xpath("//a[@target='_blank' and text()='Not sure']");
	public String participant_mail = "exomailtest01@gmail.com";
	
	@BeforeMethod
	public void beforeTest(){
		getDriverAutoSaveAndOpenWindow();
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
	
	public void addEventThenCheck(String eventName, String calendarName){
		waitForElementPresent(ELEMENT_QUICK_ADD_EVENT_POPUP);
		inputDataQuickAddEventForm(eventName, null, null, null, false, calendarName);
		waitForElementNotPresent(ELEMENT_QUICK_ADD_EVENT_POPUP);
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", eventName)));
	}
	
	public static void addDetailEvent_DetailTab(String eventName, String desc, String location, boolean allDay, String from
	           , String to, String calendarName, String priority){
		String[] typeRepeat = {};
		String[] occurrencesAndDate = {};
		String[] opt = {priority , calendarName, ""};

		info("Quick add detail event with name, description, calendar");
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_FORM);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_DETAIL_TAB);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_REMINDER_TAB);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_SCHEDULE_TAB);
		inputDataDetailEvent_DetailTab(eventName, desc, null, allDay, from, to, false, typeRepeat, occurrencesAndDate, opt);
		save();
		save();
	}
	
	public void updateEmailForUser(String user, String participant_mail){
		goToUsersAndGroupsManagement();
		searchUser(user, "User Name");
		editUser(user);
		type(ELEMENT_INPUT_EMAIL, participant_mail, true);
		save();
		closeMessageDialog();
		pause(1000);
	}
	
	public void goToCheckParticipantStatus(String eventName, String status){
		signIn("john", "gtn");
		goToCalendarPage();
		goToEditEvent(eventName);
		checkStatusOfParticipant(participant_mail, status);
	}
	
	//Case01: Add quick event from action bar
	@Test
	public void test01_QuickAddEvent_FromActionBar(){
		String calendarName = "CS_Calendar_Event_Calendar_01";
		String desc = "CS_Calendar_Add_description_01";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_01";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		quickAddEvent(eventName, null, calendarName);
		deleteCalendar(calendarName);
	}
	
	//Case02: Add quick event from left pane
	@Test(groups={"pending"})
	public void test02_QuickAddEventFromLeftPanel(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_02";
		String desc = "CS_Calendar_Event_Add_description_02";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_02";
		
		info("Add new calendar with default group");
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		info("Add event for this calendar by click action -> Add Event");
		goToAddTaskFromCalendar(calendarName, "0");
		addEventThenCheck(eventName, calendarName);
		
		deleteCalendar(calendarName);
	}
	
	//Case03: Add quick event from working pane  by left click
	@Test
	public void test03_QuickAddEventFromWorkingPanel_ByLeftClick(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_03";
		String desc = "CS_Calendar_Event_Add_description_03";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_03";
		
		info("Add new calendar with default group");
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		click(elementTime_DayWeekView);
		addEventThenCheck(eventName, calendarName);
		deleteCalendar(calendarName);
	}
	
	//Case04: Add quick event from working pane by right click
	@Test
	public void test04_QuickAddEventFromWorkingPanel_ByRightClick(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_04";
		String desc = "CS_Calendar_Event_Add_description_04";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_04";
		
		info("Add new calendar with default group");
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		rightClickOnElement(elementTime_DayWeekView);
		click(ELEMENT_ADD_EVENT_OPTION);
		addEventThenCheck(eventName, calendarName);
		
		deleteCalendar(calendarName);
	}
	
	//Case05: Add quick event from existing event or task -> NA
	
	//Case06: Add quick event from calendar view by drop and drag ->NA
	
	//Case07: Add detail event from action bar
	@Test
	public void test07_AddDetailEventFromActionBar(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_07";
		String desc = "CS_Calendar_Event_Add_description_07";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_07";
		
		info("Add new calendar with default group");
		quickAddCalendar(calendarName, desc, "My Group", color);
		goToEvent();
		quickAddDetailEvent(eventName, null, calendarName);
		
		deleteCalendar(calendarName);
	}
	
	//Case09: Add new event with duplicated name
	@Test
	public void test09_AddEventWithDuplicateName(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_09";
		String desc = "CS_Calendar_Event_Add_description_09";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_09";
		
		info("Add new calendar with default group");
		quickAddCalendar(calendarName, desc, "My Group", color);
		goToEvent();
		quickAddDetailEvent(eventName, null, calendarName);
		goToEvent();
		quickAddDetailEvent(eventName, null, calendarName);
		
		deleteCalendar(calendarName);
	}
	
	//Case10: Add new event with special characters -> NA because is not true
	
	//Case11: Add new event in case name of event begin with space
	@Test
	public void test11_AddNewEvent_NameBeginWithSpace(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_11";
		String desc = "CS_Calendar_Event_Add_description_11";
		String color = "OrangeRed ColorCell";
		String eventName = "  CS_Calendar_Event_Add_11";
		
		info("Add new calendar with default group");
		quickAddCalendar(calendarName, desc, "My Group", color);
		goToEvent();
		quickAddDetailEvent(eventName, null, calendarName, false);
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", eventName.replace(" ", ""))));
		
		deleteCalendar(calendarName);
	}
	
	//Case12: Add new event in a period of time in one day
	@Test
	public void test12_AddNewEventInPeriodTimeInOneDay(){
		String eventCatName = "CS_Calendar_Event_Add_EventCategory_12";
		String calendarName = "CS_Calendar_Event_Add_Calendar_12";
		String desc = "CS_Calendar_Event_Add_description_12";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_12";
		String time_from = "12:00";
		String time_to = "12:30";
		String from = FROM.replace("${time_from}", time_from);
		String to = TO.replace("${time_to}", time_to);
		By elementEvent = By.xpath(ELEMENT_EVENT_WEEK_VIEW.replace("${eventName}", eventName).replace("${time_from}", time_from).replace("${time_to}", time_to));
		
		addEventCategory(eventCatName, desc);
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		addEvent(eventName, desc, from, to, false, calendarName, eventCatName);
		waitForElementPresentNotDisplay(elementEvent);
		
		deleteCalendar(calendarName);
		deleteEventCategory(eventCatName);
	}
	
	//Case13: Add new event in a period of time in one day by selecting from working pane in Day View
	@Test
	public void test13_AddNewEventInPeriodTimeInOneDay_DayView(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_13";
		String desc = "CS_Calendar_Event_Add_description_13";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_13";
		String event2 = "CS_Calendar_Event_Add_13_2";
		String time_from = "12:00";
		String time_to = "14:00";
		String from = FROM.replace("${time_from}", time_from);
		String to = TO.replace("${time_to}", time_to);
		By elementEvent = By.xpath(ELEMENT_EVENT_DAY_VIEW.replace("${eventName}", eventName).replace("${time_from}", time_from).replace("${time_to}", time_to));
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		quickAddEvent(eventName, null, calendarName);
		
		goToDayViewTab();
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", eventName)));
		
		goToEvent();
		checkDateDefaultWhenAddEvent();
		cancel();
		
		addEvent(event2, desc, from, to, false, calendarName);
		waitForElementPresent(elementEvent);
		
		deleteCalendar(calendarName);
	}
	
	//Case14: Add new event in a period of time in one day by selecting from working pane in Week View
	@Test
	public void test14_AddNewEventInPeriodTimeInOneDay_WeekView(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_14";
		String desc = "CS_Calendar_Event_Add_description_14";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_14";
		String event2 = "CS_Calendar_Event_Add_14_2";
		String time_from = "12:00";
		String time_to = "14:00";
		String from = FROM.replace("${time_from}", time_from);
		String to = TO.replace("${time_to}", time_to);
		By elementEvent = By.xpath(ELEMENT_EVENT_WEEK_VIEW.replace("${eventName}", eventName).replace("${time_from}", time_from).replace("${time_to}", time_to));

		quickAddCalendar(calendarName, desc, "My Group", color);
		quickAddEvent(event2, null, calendarName);
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", event2)));

		goToEvent();
		checkDateDefaultWhenAddEvent();
		cancel();
		
		addEvent(eventName, desc, from, to, false, calendarName);
		waitForElementPresent(elementEvent);
		
		deleteCalendar(calendarName);
	}
	
	//Case15: Add new event in a specific day from working pane in Month View
	@Test
	public void test15_AddNewEventInDay_MonthView(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_15";
		String desc = "CS_Calendar_Event_Add_description_15";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_15";
		String time_from = "00:00";
		String time_to = "23:59";
		By elementEvent = By.xpath(ELEMENT_EVENT_MONTH_VIEW.replace("${eventName}", eventName).replace("${time_from}", time_from).replace("${time_to}", time_to));

		quickAddCalendar(calendarName, desc, "My Group", color);
		goToMonthViewTab();
		
		click(elementTime_MonthView);
		waitForElementPresent(ELEMENT_QUICK_ADD_EVENT_POPUP);
		checkDateDefaultWhenAddEvent(time_from, time_to);
		inputDataQuickAddEventForm(eventName, null, null, null, false, calendarName);
		waitForElementNotPresent(ELEMENT_QUICK_ADD_EVENT_POPUP);		
		waitForElementPresent(elementEvent);
		
		deleteCalendar(calendarName);
	}
	
	//Case16: Add new event in a period of time in one day by selecting from working pane in Work Week View
	@Test
	public void test16_AddNewEventInPeriodTimeInOneDay_WorkWeekView(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_16";
		String desc = "CS_Calendar_Event_Add_description_16";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_16";
		String time_from = "12:00";
		String time_to = "14:00";
		String from = FROM.replace("${time_from}", time_from);
		String to = TO.replace("${time_to}", time_to);
		By elementEvent = By.xpath(ELEMENT_EVENT_WEEK_VIEW.replace("${eventName}", eventName).replace("${time_from}", time_from).replace("${time_to}", time_to));

		quickAddCalendar(calendarName, desc, "My Group", color);

		goToWorkWeekViewTab();
		click(elementTime_DayWeekView);
		waitForElementPresent(ELEMENT_QUICK_ADD_EVENT_POPUP);
		checkDateDefaultWhenAddEvent();
		inputDataQuickAddEventForm(eventName, null, from, to, false, calendarName);
		waitForElementNotPresent(ELEMENT_QUICK_ADD_EVENT_POPUP);		
		waitForElementPresent(elementEvent);
		
		deleteCalendar(calendarName);
	}
	
	//Case17: Add new event in a period of time in case From and To time is the start and end time of day
	@Test
	public void test17_AddNewEventInPeriodTime_FromIsTheStart_ToIsTheEndOfDay(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_17";
		String desc = "CS_Calendar_Event_Add_description_17";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_17";
		String time_from = "00:12";
		String time_to = "23:48";
		String from = FROM.replace("${time_from}", time_from);
		String to = TO.replace("${time_to}", time_to);
		By elementEvent = By.xpath(ELEMENT_EVENT_FULLDAY_WEEKVIEW.replace("${eventName}", eventName).replace("${time_from}", time_from).replace("${time_to}", time_to));

		quickAddCalendar(calendarName, desc, "My Group", color);
		
		goToEvent();
		addDetailEvent_DetailTab(eventName, desc, null, false, from, to, calendarName, "");
		waitForElementPresent(elementEvent);
		
		deleteCalendar(calendarName);
	}
	
	//Case18: Add new event when choose a specific  category to use
	@Test
	public void test18_AddNewEvent_ChooseCategory(){
		String category = "Meeting";
		String calendarName = "CS_Calendar_Event_Add_Calendar_18";
		String desc = "CS_Calendar_Event_Add_description_18";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_18";
		String[] typeRepeat = {};
		String[] occurrencesAndDate = {};
		String[] opt = {null, calendarName, null};
		By select_category_detail_add = By.xpath(ELEMENT_CATEGORY_SELECT_OPTION_DETAIL_ADD.replace("${category}", category));
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		selectCategoryView(category);
				
		click(elementTime_DayWeekView);	
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		waitForElementPresent(select_category_detail_add);
		inputDataDetailEvent_DetailTab(eventName, desc, null, false, null, null, false, typeRepeat, occurrencesAndDate, opt);
		save();
		save();
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", eventName)));
		
		deleteCalendar(calendarName);
	}
	
	//Case19: Add new event in a period of time in case From time is greater than To time
	@Test
	public void test19_AddNewEvent_FromTimeIsGreaterThanToTime(){
		String eventName = "CS_Calendar_Event_Add_19";
		String time_from = "12:00";
		String time_to = "10:00";
		String from = FROM.replace("${time_from}", time_from);
		String to = TO.replace("${time_to}", time_to);
		
		goToEvent();
		addDetailEvent_DetailTab(eventName, null, null, false, from, to, "", "");
		checkAlertInfo(MSG_ADD_EVENT_FROM_GREATER_TO);
		cancel();
	}
	
	//Case20: Add all day event
	@Test
	public void test20_AddAllDayEvent(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_20";
		String desc = "CS_Calendar_Event_Add_description_20";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_20";
		String time_from = "00:00";
		String time_to = "23:59";
		By elementEventWeekView = By.xpath(ELEMENT_EVENT_ALLDAY_WEEKVIEW.replace("${eventName}", eventName).replace("${time_from}", time_from).replace("${time_to}", time_to));
		By elementEventDayView = By.xpath(ELEMENT_EVENT_FULLDAY_DAYVIEW.replace("${eventName}", eventName).replace("${time_from}", time_from).replace("${time_to}", time_to));
		By elementEventMonthView = By.xpath(ELEMENT_EVENT_FULLDAY_MONTHVIEW.replace("${eventName}", eventName).replace("${time_from}", time_from).replace("${time_to}", time_to));
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		goToEvent();
		info("Add new all day event");
		addDetailEvent_DetailTab(eventName, desc, null, true, null, null, calendarName, "");
		
		info("All day event displays in week view at allDayGrid");
		waitForElementPresent(elementEventWeekView);
		
		info("All day event display in day view at allDayGrid");
		goToDayViewTab();
		waitForElementPresent(elementEventDayView);
		
		info("All day event display in work week view at allDayGrid");
		goToWorkWeekViewTab();
		waitForElementPresent(elementEventWeekView);
		
		info("All day event display in month view as normal");
		goToMonthViewTab();
		waitForElementPresent(elementEventMonthView);
		
		deleteCalendar(calendarName);
	}
	
	//Case21: Add event in full several days
	@Test
	public void test21_AddEventInFullSeveralDays(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_21";
		String desc = "CS_Calendar_Event_Add_description_21";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_21";
		String time_from = "00:00";
		String time_to = "23:59";
		String from = getCurrentDate("MM/dd/yyyy");
		String to = addDateFromCurrentDate("MM/dd/yyyy", 2);
		By elementEventWeekView = By.xpath(ELEMENT_EVENT_ALLFEWDAY_WEEKVIEW.replace("${eventName}", eventName).replace("${time_from}",
				time_from).replace("${time_to}", time_to).replace("${to_date}", addDateFromCurrentDate("EEE MMM dd yyyy", 2)));
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		info("Add new all several days event");
		goToEvent();
		addDetailEvent_DetailTab(eventName, desc, null, true, from, to, calendarName, "");
		
		info("All day event displays in week view at allDayGrid");
		waitForElementPresent(elementEventWeekView);
		
		deleteCalendar(calendarName);
	}
	
	//Case22: Add event in several days but not full day
	@Test
	public void test22_AddEventInSeveralDaysNotFullDay(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_22";
		String desc = "CS_Calendar_Event_Add_description_22";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_22";
		String time_from = "10:00";
		String time_to = "20:59";
		String from = getCurrentDate("MM/dd/yyyy") + " " + time_from;
		String to = addDateFromCurrentDate("MM/dd/yyyy", 2) + " " + time_to;
		By elementEventWeekView = By.xpath(ELEMENT_EVENT_FULLFEWDAY_WEEKVIEW.replace("${eventName}", eventName).replace("${time_from}",
				time_from).replace("${time_to}", time_to).replace("${to_date}", addDateFromCurrentDate("EEE MMM dd yyyy", 2)));
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		info("Add new all several days event");
		goToEvent();
		addDetailEvent_DetailTab(eventName, desc, null, false, from, to, calendarName, "");
		
		info("All day event displays in week view at allDayGrid");
		waitForElementPresent(elementEventWeekView);
		
		deleteCalendar(calendarName);
	}
	
	//Case26: Add event in several days from working pane of Month View
	@Test
	public void test26_AddEventInSeveralDays_FromWorkingPanelOfMonthView(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_26";
		String desc = "CS_Calendar_Event_Add_description_26";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_26";
		String time_from = "10:00";
		String time_to = "20:59";
		String from = getCurrentDate("MM/dd/yyyy") + " " + time_from;
		String to = addDateFromCurrentDate("MM/dd/yyyy", 2) + " " + time_to;
		By elementEvent = By.xpath(ELEMENT_EVENT_FEWDAY_MONTH_VIEW.replace("${eventName}", eventName).replace("${time_from}", time_from)
				.replace("${time_to}", time_to).replace("${to_date}", addDateFromCurrentDate("EEE MMM dd yyyy", 2)));

		quickAddCalendar(calendarName, desc, "My Group", color);
		goToMonthViewTab();
		
		click(elementTime_MonthView);
		addDetailEvent_DetailTab(eventName, desc, null, false, from, to, calendarName, "");
		waitForElementPresent(elementEvent);
		
		deleteCalendar(calendarName);
	}
	
	//Case27: Add new event in case all categories was deleted -> NA
	
	//Case28: Add event with priority
	@Test
	public void test28_AddEventWithPriority(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_28";
		String desc = "CS_Calendar_Event_Add_description_28";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_28_none";
		String eventNameHigh = "CS_Calendar_Event_Add_28_High";
		String eventNameNormal = "CS_Calendar_Event_Add_28_normal";
		String eventNameLow = "CS_Calendar_Event_Add_28_low";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		info("Add event with priority = none");
		goToEvent();
		addDetailEvent_DetailTab(eventName, desc, null, false, null, null, calendarName, "None");
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", eventName)));
		
		info("Add event with priority = high");
		goToEvent();
		addDetailEvent_DetailTab(eventNameHigh, desc, null, false, null, null, calendarName, "High");
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT_HIGH_PRIORITY.replace("${eventName}", eventNameHigh)));
		
		info("Add event with priority = normal");
		goToEvent();
		addDetailEvent_DetailTab(eventNameNormal, desc, null, false, null, null, calendarName, "Normal");
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT_NORMAL_PRIORITY.replace("${eventName}", eventNameNormal)));
		
		info("Add event with priority = Low");
		goToEvent();
		addDetailEvent_DetailTab(eventNameLow, desc, null, false, null, null, calendarName, "Low");
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT_LOW_PRIORITY.replace("${eventName}", eventNameLow)));
		
		deleteCalendar(calendarName);
	}
	
	//Case29: Add new event when selected value is No repeat 
	@Test
	public void test29_AddNewEvent_NoRepeat(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_29";
		String desc = "CS_Calendar_Event_Add_description_29";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_29";
		String time_from = "01:00";
		String time_to = "02:00";
		String from = FROM.replace("${time_from}", time_from);
		String to = TO.replace("${time_to}", time_to);
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		goToMonthViewTab();
		
		click(elementTime_MonthView);
		addDetailEvent_DetailTab(eventName, desc, null, false, from, to, calendarName, "");
		waitForElementNotPresent(ELEMENT_ADD_DETAIL_EVENT_FORM);
		List<WebElement> event = getListElement(ELEMENT_EVENT_PARENT, By.xpath(ELEMENT_EVENT_REPEAT.replace("${eventName}", eventName).replace("${time_from}", time_from)
				.replace("${time_to}", time_to)));
		info("Number of event is " + event.size());
		assert (event.size() == 1);
		
		deleteCalendar(calendarName);
	}
	
	//Case30: Add new event when selected value is Repeat
	@Test
	public void test30_AddNewEvent_Repeat(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_30";
		String desc = "CS_Calendar_Event_Add_description_30";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_30";
		String time_from = "01:00";
		String time_to = "02:00";
		String from = FROM.replace("${time_from}", time_from);
		String to = TO.replace("${time_to}", time_to);
		String[] typeRepeat = {"Daily", "2", "Date"};
		String[] occurrencesAndDate = {"", addDateFromCurrentDate("MM/dd/yyyy", 5)};
		String[] opt = {"", calendarName, ""};
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		goToMonthViewTab();
		
		click(elementTime_MonthView);
		info("Add event has repeat");
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		inputDataDetailEvent_DetailTab(eventName, desc, null, false, from, to, true, typeRepeat, occurrencesAndDate, opt);
		save();
		save();
		waitForElementNotPresent(ELEMENT_ADD_DETAIL_EVENT_FORM);
		
		List<WebElement> event = getListElement(ELEMENT_EVENT_PARENT, By.xpath(ELEMENT_EVENT_REPEAT.replace("${eventName}", eventName).replace("${time_from}", time_from)
					.replace("${time_to}", time_to)));
		info("Number of event is " + event.size());
		assert (event.size() == 3);
		
		deleteCalendar(calendarName);		
	}
	
	//Case32: Add new event with attachments
	@Test
	public void test32_AddNewEventWithAttachments(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_32";
		String desc = "CS_Calendar_Event_Add_description_32";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_32";
		String[] typeRepeat = {};
		String[] occurrencesAndDate = {};
		String[] opt = {"", calendarName, ""};
		String file1 = "TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";
		String file2 = "TestData/ECMS_DMS_SE_Upload_docfile.doc";
		String fileName1 = file1.split("/")[1];
		String fileName2 = file2.split("/")[1];
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		inputDataDetailEvent_DetailTab(eventName, desc, null, false, null, null, false, typeRepeat, occurrencesAndDate, opt, file1, file2);
		save();
		save();
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", eventName)));
		
		info("Check attachments of event in List view");
		goToListViewTab();
		waitForElementPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_IN_LIST.replace("${eventName}", eventName)));
		check(By.xpath(ELEMENT_EVENT_CHECKBOX.replace("${eventName}", eventName)));
		
		info("File .jpg can download and view");
		waitForElementPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_DETAIL.replace("${fileName}", fileName1)));
		for (int i = 0; i < 5; i++ ){
			click(By.xpath(ELEMENT_EVENT_ATTACHMENT_VIEW.replace("${fileName}", fileName1)));
			WebElement close = waitForAndGetElement(By.xpath(ELEMENT_EVENT_ATTACHMENT_CLOSE_VIEW.replace("${fileName}", fileName1)), 5000, 0);
			if (close != null){
				break;
			}
		}
		waitForElementPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_CLOSE_VIEW.replace("${fileName}", fileName1)));
		click(By.xpath(ELEMENT_EVENT_ATTACHMENT_DETAIL.replace("${fileName}", fileName1)));
		assert checkFileExisted(fileName1);
		
		info("file .doc can download but can not view");
		waitForElementPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_DETAIL.replace("${fileName}", fileName2)));
		waitForElementNotPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_VIEW.replace("${fileName}", fileName2)));
		click(By.xpath(ELEMENT_EVENT_ATTACHMENT_DETAIL.replace("${fileName}", fileName2)));
		assert checkFileExisted(fileName2);
		
		deleteCalendar(calendarName);
	}
	
	//Case33: Add new event with attachments in case total file size is more than 10MB
	@Test
	public void test33_AddNewEventWithAttachmentSizeMoreThan10MB(){
		String file1 = "TestData/CS_Event_Add_033_1.mp3";
		String file2 = "TestData/CS_Event_Add_033_2.mp3";
		String file3 = "TestData/CS_Event_Add_033_3.mp3";
		
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		attachSomeFiles(file1, file2, file3);
		checkAlertWarning(MSG_ADD_EVENT_ATTACHMENT_GREATER_10MB);
		cancel();
		cancel();
	}
	
	//Case34: Add new event with attachments in case no browsed file 
	@Test
	public void test34_AddNewEvent_AttachmentNoBrowsedFile(){
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		click(ELEMENT_ADD_TASK_DETAIL_ATTACH);
		click(ELEMENT_ATTACHMENT_SAVE_BUTTON);
		checkAlertWarning(MSG_ADD_EVENT_ATTACHMENT_NO_FILE);
		cancel();
		cancel();
	}
	
	//Case35: Add new event with attachments in case do not upload selected file -> NA
	
	//Case36: Add more attachments after attaching file for event
	@Test
	public void test36_AddMoreAttachment(){
		String file1 = "TestData/ECMS_DMS_SE_Addsymlink.jpg";
		String file2 = "TestData/ECMS_DMS_SE_Upload_docfile.doc";
		String file3 = "TestData/CS_Event_Add_033_1.mp3";
		String file4 = "TestData/CS_Event_Add_033_2.mp3";
		
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		attachSomeFiles(file1, file2);
		waitForElementPresent(By.xpath(ELEMENT_ADD_EVENT_FILE_ATTACHMENT.replace("${fileName}", file1.split("/")[1])));
		waitForElementPresent(By.xpath(ELEMENT_ADD_EVENT_FILE_ATTACHMENT.replace("${fileName}", file2.split("/")[1])));
		
		attachSomeFiles(file3, file4);
		waitForElementPresent(By.xpath(ELEMENT_ADD_EVENT_FILE_ATTACHMENT.replace("${fileName}", file3.split("/")[1])));
		waitForElementPresent(By.xpath(ELEMENT_ADD_EVENT_FILE_ATTACHMENT.replace("${fileName}", file4.split("/")[1])));
		
		cancel();
	}
	
	//Case38: Remove attachment when adding new event
	@Test
	public void test38_RemoveAttachmentWhenAddingNewEvent(){
		String file1 = "TestData/ECMS_DMS_SE_Addsymlink.jpg";
		String file2 = "TestData/ECMS_DMS_SE_Upload_docfile.doc";
		String calendarName = "CS_Calendar_Event_Add_Calendar_32";
		String desc = "CS_Calendar_Event_Add_description_32";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_32";
		String[] typeRepeat = {};
		String[] occurrencesAndDate = {};
		String[] opt = {"", calendarName, ""};
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		inputDataDetailEvent_DetailTab(eventName, desc, null, false, null, null, false, typeRepeat, occurrencesAndDate, opt, file1, file2);
		click(By.xpath(ELEMENT_ADD_EVENT_FILE_REMOVE_ICON.replace("${fileName}", file1.split("/")[1])));
		save();
		save();
		waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", eventName)));
		
		goToListViewTab();
		check(By.xpath(ELEMENT_EVENT_CHECKBOX.replace("${eventName}", eventName)));
		
		info("Event has only one file that is not removed");
		waitForElementPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_DETAIL.replace("${fileName}", file2.split("/")[1])));
		waitForElementNotPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_DETAIL.replace("${fileName}", file1.split("/")[1])));
		
		deleteCalendar(calendarName);
	}
	
	//Case45: Check input participant automatically to send invitation email
	@Test
	public void test45_CheckInputParticipantAutomaticallyToSendInvitaionEmail(){
		String user1 = "demo";
		String user2 = "James";
		
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		info("Add participant by select user");
		click(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
		click(ELEMENT_ADD_MORE_PARTICIPANT_ICON);
		click(ELEMENT_SELECT_USER_PARTICIPANT);
		selectUserPermission(user1, 1);
		waitForElementPresent(By.xpath(ELEMENT_PARTICIPANT_IN_TEXTAREA.replace("${user}", user1)));
		click(ELEMENT_SELECT_USER_PARTICIPANT);
		selectUserPermission(user2, 2);
		waitForElementPresent(By.xpath(ELEMENT_PARTICIPANT_IN_TEXTAREA.replace("${user}", "james")));
		cancel();
		cancel();
	}
	
	//Case46: Check input email(s) automatically to send invitation email
	@Test
	public void test46_CheckInputEmailAutomaticallyToSendInvitaionEmail(){
		String[] userInfo = {"james", "james"};
		
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		info("Add participant by select contact");
		click(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
		click(ELEMENT_ADD_MORE_PARTICIPANT_ICON);
		click(ELEMENT_ADD_CONTACT_PARTICIPANT_ICON);
		selectUserForEvent(userInfo);
		click(ELEMENT_ADD_BUTTON);
		waitForElementPresent(By.xpath(ELEMENT_PARTICIPANT_IN_TEXTAREA.replace("${user}", "James Davis (james.davis@acme.exoplatform.com)")));
		waitForElementPresent(By.xpath(ELEMENT_PARTICIPANT_IN_TEXTAREA.replace("${user}", "james")));
		cancel();
		cancel();
	}
	
	//Case47: Check input participant who is not existed by manual to send invitation mail
	@Test
	public void test47_CheckInputParticipant_WhoNotExist(){
		String invalidUser = "Test47";
		
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		info("Add perticipant by type a invalid user");
		click(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
		click(ELEMENT_ADD_MORE_PARTICIPANT_ICON);
		type(ELEMENT_INPUT_PARTICIPANT_TEXTAREA, invalidUser, true);
		save();
		checkAlertWarning(MSG_PARTICIPANT_INVALID.replace("${user}", invalidUser));
		cancel();
		cancel();
	}
	
	//Case48: Check input invalid email address by manual to send invitation mail -> NA

	//Case49: Remove participant(s) or email (s) which was selected to send invitation mail
	@Test
	public void test49_RemoveParticipant(){
		String user = "demo";
		String[] userInfo = {"james", "james"};
		By elementDelete1 = By.xpath(ELEMENT_PARTICIPANT_REMOVE_ICON.replace("${user}", "Jack Miller"));
		By elementDelete2 = By.xpath(ELEMENT_PARTICIPANT_REMOVE_ICON.replace("${user}", "James Davis (james.davis@acme.exoplatform.com)"));
		
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		click(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
		click(ELEMENT_ADD_MORE_PARTICIPANT_ICON);
		click(ELEMENT_SELECT_USER_PARTICIPANT);
		selectUserPermission(user, 1);
		click(ELEMENT_ADD_CONTACT_PARTICIPANT_ICON);
		selectUserForEvent(userInfo);
		click(ELEMENT_ADD_BUTTON);
		save();
		
		click(elementDelete1);
		acceptAlert();
		waitForElementNotPresent(elementDelete1);
		
		click(elementDelete2);
		acceptAlert();
		waitForElementNotPresent(elementDelete2);
		cancel();
	}
	
	//Case50: Check input many participants,emails to send invitation mail
	@Test
	public void test50_CheckInputManyParticipantEmail(){
		String data = "demo;james,mary.williams@acme.exoplatform.com";
		
		goToEvent();
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		info("Add perticipant by type a invalid user");
		click(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
		click(ELEMENT_ADD_MORE_PARTICIPANT_ICON);
		type(ELEMENT_INPUT_PARTICIPANT_TEXTAREA, data, true);
		save();
		
		waitForElementPresent(By.xpath(ELEMENT_PARTICIPANT_IN_LIST.replace("${user}", "Jack Miller")));
		waitForElementPresent(By.xpath(ELEMENT_PARTICIPANT_IN_LIST.replace("${user}", "James Davis")));
		waitForElementPresent(By.xpath(ELEMENT_PARTICIPANT_IN_LIST.replace("${user}", "mary.williams@acme.exoplatform.com")));
		
		cancel();
	}
	
	//Case51: Check Send invitation mail when use calendar setting to be Never send invitation
	@Test
	public void test51_CheckSendInvitaionMail_CalendarSettingNeverSendInvitation(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_51";
		String desc = "CS_Calendar_Event_Add_description_51";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_51";
		By mail = By.xpath("//b[contains(text(), '[Invitation] "+ eventName + "')]");
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		setSendEventInvitationForCalendar(1);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 0);
	    String handlesBefore = driver.getWindowHandle();
		pause(60000);
		goToMail();
		waitForElementNotPresent(mail);
		
		driver.switchTo().window(handlesBefore);
		deleteCalendar(calendarName);
		setSendEventInvitationForCalendar(2);
	}
	
	//Case52: Check Send invitation mail when use calendar setting to be Always send invitation
	@Test(groups={"pending"})
	public void test52_CheckSendInvitationMail_CalendarSettingAlwaysSendInvitation(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_52";
		String desc = "CS_Calendar_Event_Add_description_52";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_52";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		setSendEventInvitationForCalendar(2);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 0);
	    String handlesBefore = driver.getWindowHandle();
		pause(60000);
		goToMail();
		checkInvitationWhenAddEventThenDetele(eventName);
		
		driver.switchTo().window(handlesBefore);
		deleteCalendar(calendarName);
	}
	
	//Case53: Check Send invitation mail when use calendar setting to be Ask when send invitation
	@Test(groups={"pending"})
	public void test53_CheckSendInvitationMail_CalendarSettingAskWhenSendInvitation(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_53";
		String desc = "CS_Calendar_Event_Add_description_53";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_53";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		setSendEventInvitationForCalendar(3);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 0, false);
	    String handlesBefore = driver.getWindowHandle();
		pause(60000);
		goToMail();
		checkInvitationWhenAddEventThenDetele(eventName);
		
		driver.switchTo().window(handlesBefore);
		deleteCalendar(calendarName);
		setSendEventInvitationForCalendar(2);
	}
	
	//Case54: Check Send invitation mail when use calendar setting to be Ask sending invitation but don't send invitation when confirm 
	@Test
	public void test54_CheckSendInvitationMail_CalendarSettingAskWhenSendInvitation_DoNotSend(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_54";
		String desc = "CS_Calendar_Event_Add_description_54";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_54";
		By mail = By.xpath("//b[contains(text(), '[Invitation] "+ eventName + "')]");
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		setSendEventInvitationForCalendar(3);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 0, false, false);
	    String handlesBefore = driver.getWindowHandle();
		pause(60000);
		goToMail();
		waitForElementNotPresent(mail);
		
		driver.switchTo().window(handlesBefore);
		deleteCalendar(calendarName);
		setSendEventInvitationForCalendar(2);
	}
	
	//Case55: Check Send invitation mail when Choose Never send invitation mail while creating event
	@Test
	public void test55_CheckSendInvitationMail_ChooseNeverSendInvitationMail_WhileCreatingEvent(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_55";
		String desc = "CS_Calendar_Event_Add_description_55";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_55";
		By mail = By.xpath("//b[contains(text(), '[Invitation] "+ eventName + "')]");
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 1);
	    String handlesBefore = driver.getWindowHandle();
		pause(60000);
		goToMail();
		waitForElementNotPresent(mail);
		
		driver.switchTo().window(handlesBefore);
		deleteCalendar(calendarName);
	}
	
	//Case56: Check Send invitation mail when Choose Always send invitation mail while creating event
	@Test(groups={"pending"})
	public void test56_CheckSendInvitationMail_ChooseAlwaysSendInvitationMail_WhileCreatingEvent(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_56";
		String desc = "CS_Calendar_Event_Add_description_56";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_56";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 2);
	    String handlesBefore = driver.getWindowHandle();
		pause(60000);
		goToMail();
		checkInvitationWhenAddEventThenDetele(eventName);
		
		driver.switchTo().window(handlesBefore);
		deleteCalendar(calendarName);
	}
	
	//Case57: Check Send invitation mail when Choose Ask send invitation mail while creating event
	@Test(groups={"pending"})
	public void test57_CheckSendInvitationMail_ChooseAskSendInvitationMail_WhileCreatingEvent(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_57";
		String desc = "CS_Calendar_Event_Add_description_57";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_57";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 3, false);
	    String handlesBefore = driver.getWindowHandle();
		pause(60000);
		goToMail();
		checkInvitationWhenAddEventThenDetele(eventName);
		
		driver.switchTo().window(handlesBefore);
		deleteCalendar(calendarName);
	}
	
	//Case58: Check Send invitation mail in case Choose Ask send invitation mail but choose only Save event when confirm  while creating event
	@Test
	public void test58_CheckSendInvitationMail_ChooseAskDoNotSendInvitationMail_WhileCreatingEvent(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_58";
		String desc = "CS_Calendar_Event_Add_description_58";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_58";
		By mail = By.xpath("//b[contains(text(), '[Invitation] "+ eventName + "')]");
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 3, false, false);
	    String handlesBefore = driver.getWindowHandle();
		pause(60000);
		goToMail();
		waitForElementNotPresent(mail);
		
		driver.switchTo().window(handlesBefore);
		deleteCalendar(calendarName);
	}
	
	//Case59: Check status of participant after create event with No invitation mail
	@Test
	public void test59_CheckStatusOfParticipant_NoInvitationMail(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_59";
		String desc = "CS_Calendar_Event_Add_description_59";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_59";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 1);
		goToEditEvent(eventName);
		checkStatusOfParticipant(participant_mail, "");
		
		deleteCalendar(calendarName);
	}
	
	//Case60: Check status of participant after create event with invitation mail
	@Test
	public void test60_CheckStatusOfParticipant_SendInvitationMail(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_60";
		String desc = "CS_Calendar_Event_Add_description_60";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_60";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		
		addEventSetParticipant(eventName, calendarName, participant_mail, 0, 0, 2);
		goToEditEvent(eventName);
		checkStatusOfParticipant(participant_mail, "pending");
		
		deleteCalendar(calendarName);
	}
	
	//Case61: Check status of participant after create event with invitation mail and the participant agree attend the event
	@Test
	public void test61_CheckStatusOfParticipant_SendInvitationMail_AgreeAttentEvent(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_61";
		String desc = "CS_Calendar_Event_Add_description_61";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_61";
		By mail = By.xpath("//b[contains(text(), '[Invitation] "+ eventName + "')]");
		String user = "demo";
		
		//update mail address for user demo is participant
		updateEmailForUser(user, participant_mail);
		
		goToCalendarPage();
		quickAddCalendar(calendarName, desc, "My Group", color);
		addEventSetParticipant(eventName, calendarName, user, 0, 0, 2);
		signOut();
		signIn(user, "gtn");
		
	    String handlesBefore = driver.getWindowHandle();
	    pause(60000);
		goToMail();
		click(mail);
		click(ELEMENT_YES_LINK_IN_MAIL);
		click(ELEMENT_DELETE);
		
		driver.switchTo().window(handlesBefore);
		signOut();
		goToCheckParticipantStatus(eventName, "yes");
		
		deleteCalendar(calendarName);
		updateEmailForUser(user, OLD_MAIL_ADDRESS_DEMO);
	}
	
	//Case62: Check status of participant after create event with invitation mail and the participant want to add event to his calendar
	@Test
	public void test62_CheckStatusOfParticipant_SendInvitationMail_AddEventToHisCalendar(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_62";
		String desc = "CS_Calendar_Event_Add_description_62";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_62";
		By mail = By.xpath("//b[contains(text(), '[Invitation] "+ eventName + "')]");
		String user = "demo";
		
		//update mail address for user demo is participant
		updateEmailForUser(user, participant_mail);
		
		goToCalendarPage();
		quickAddCalendar(calendarName, desc, "My Group", color);
		addEventSetParticipant(eventName, calendarName, user, 0, 0, 2);
		signOut();
		signIn(user, "gtn");
		
	    String handlesBefore = driver.getWindowHandle();
	    pause(60000);
		goToMail();
		click(mail);
		String handlesBefore1 = driver.getWindowHandle();
		
		info("Import event from invitation mail");
		click(ELEMENT_YES_IMPORT_LINK_IN_MAIL);
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_FORM);
		save();
		save();
		waitForElementPresentNotDisplay(By.xpath("//*[@class='EventContainer' and text()='" + eventName + "']"));
		deleteTask(eventName, true);
		
		driver.switchTo().window(handlesBefore1);
		click(ELEMENT_DELETE);
		
		driver.switchTo().window(handlesBefore);
		signOut();
		goToCheckParticipantStatus(eventName, "yes");
		
		deleteCalendar(calendarName);
		updateEmailForUser(user, OLD_MAIL_ADDRESS_DEMO);
	}
	
	//Case63: Check status of participant after create event with invitation mail but the participant do not want to attend at the event
	@Test
	public void test63_CheckStatusOfParticipant_SendInvitationMail_NotWantToAttendEvent(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_63";
		String desc = "CS_Calendar_Event_Add_description_63";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_63";
		By mail = By.xpath("//b[contains(text(), '[Invitation] "+ eventName + "')]");
		String user = "demo";
		
		//update mail address for user demo is participant
		updateEmailForUser(user, participant_mail);
		
		goToCalendarPage();
		quickAddCalendar(calendarName, desc, "My Group", color);
		addEventSetParticipant(eventName, calendarName, user, 0, 0, 2);
		signOut();
		signIn(user, "gtn");
		
	    String handlesBefore = driver.getWindowHandle();
	    pause(60000);
		goToMail();
		click(mail);
		click(ELEMENT_NO_LINK_IN_MAIL);
		click(ELEMENT_DELETE);
		
		driver.switchTo().window(handlesBefore);
		signOut();
		goToCheckParticipantStatus(eventName, "no");
		
		deleteCalendar(calendarName);
		updateEmailForUser(user, OLD_MAIL_ADDRESS_DEMO);
	}
	
	//Case64: Check Status of participant when he switch between yes/no attend at the event in case  before No or status
	@Test
	public void test64_CheckStatusOfParticipant_SendInvitationMail_SwitchAttendEvent(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_64";
		String desc = "CS_Calendar_Event_Add_description_64";
		String color = "OrangeRed ColorCell";
		String eventName = "CS_Calendar_Event_Add_64";
		By mail = By.xpath("//b[contains(text(), '[Invitation] "+ eventName + "')]");
		String user = "demo";
		
		//update mail address for user demo is participant
		updateEmailForUser(user, participant_mail);
		
		goToCalendarPage();
		quickAddCalendar(calendarName, desc, "My Group", color);
		addEventSetParticipant(eventName, calendarName, user, 0, 0, 2);
		signOut();
		signIn(user, "gtn");
		
	    String handlesBefore = driver.getWindowHandle();
	    pause(60000);
		goToMail();
		click(mail);
		String handlesBefore1 = driver.getWindowHandle();
		click(ELEMENT_YES_LINK_IN_MAIL);
		
		driver.switchTo().window(handlesBefore);
		signOut();
		goToCheckParticipantStatus(eventName, "yes");
		signOut();
		signIn(user, "gtn");
		
		info("Click not sure link in invitation mail");
		driver.switchTo().window(handlesBefore1);
		click(ELEMENT_NOT_SURE_LINK_IN_MAIL);
		click(ELEMENT_DELETE);
		
		driver.switchTo().window(handlesBefore);
		signOut();
		goToCheckParticipantStatus(eventName, "pending");
		
		deleteCalendar(calendarName);
		updateEmailForUser(user, OLD_MAIL_ADDRESS_DEMO);
	}
	
	//Case73: Add new event into a shared calendar when shared user has edit right
	@Test
	public void test73_AddNewEventIntoSharedCalendar_HasEditRight(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_73";
		String desc = "CS_Calendar_Event_Add_Calendar_description_73";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		String eventName = "CS_Calendar_Event_Add_73";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		shareCalendar(calendarName, user, group, true);
		signOut();
		
		info("User demo can add event for this shared calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		quickAddEvent(eventName, null, "john - " + calendarName);
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
	
	//Case74: Add new event into a shared calendar when shared user does not have edit right
	@Test
	public void test74_AddNewEventIntoSharedCalendar_DoesNotHaveEditRight(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_74";
		String desc = "CS_Calendar_Event_Add_Calendar_description_74";
		String color = "SeaGreen ColorCell";
		String[] user = {"demo"};
		String[] group = {};
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		shareCalendar(calendarName, user, group, false);
		signOut();
		
		info("User demo can add event for this shared calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", "john- " + calendarName));
		goToEvent();
		String id = getIDOfCalendar("john- " + calendarName);
		waitForElementNotPresent(By.xpath(ELEMENT_SHARED_CALENDAR_OPTION_ADD_EVENT.replace("${calendarId}", id)));
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
	
	//Case75: Add new event into a group calendar when user has edit right
	@Test
	public void test75_AddNewEventInGroupCalendar_HasEditRight(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_75";
		String desc = "CS_Calendar_Event_Add_Calendar_description_75";
		String color = "SeaGreen ColorCell";
		String eventName = "CS_Calendar_Event_Add_75";
		String[] groupUser = {"/developers"};
		String[] user = {"demo"};
		By calendarGroup = By.xpath(ELEMENT_CALENDAR_IN_GROUP_CALENDAR.replace("${group}", groupUser[0]).replace("${calendarName}", calendarName));
		
		info("Add a calendar in group calendar");
		addCalendar(calendarName, desc, "My Group", color, groupUser, user);
		signOut();
		
		info("User demo can add event for this group calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(calendarGroup);
		quickAddEvent(eventName, null, calendarName + " (" + groupUser[0].substring(1) + ")");
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
	
	//Case76: Add new event into a group calendar when user does not have edit right
	@Test
	public void test76_AddNewEventInGroupCalendar_DoesNotHaveEditRight(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_76";
		String desc = "CS_Calendar_Event_Add_Calendar_description_76";
		String color = "SeaGreen ColorCell";
		String[] groupUser = {"/developers"};
		String[] user = {};
		By calendarGroup = By.xpath(ELEMENT_CALENDAR_IN_GROUP_CALENDAR.replace("${group}", groupUser[0]).replace("${calendarName}", calendarName));
		
		info("Add a calendar in group calendar");
		addCalendar(calendarName, desc, "My Group", color, groupUser, user);
		signOut();
		
		info("User demo can add event for this group calendar");
		signIn("demo", "gtn");
		goToCalendarPage();
		waitForElementPresent(calendarGroup);
		goToEvent();
		String id = getIDOfCalendar(calendarName);
		waitForElementNotPresent(By.xpath(ELEMENT_SHARED_CALENDAR_OPTION_ADD_EVENT.replace("${calendarId}", id)));
		signOut();
		
		signIn("john", "gtn");
		goToCalendarPage();
		deleteCalendar(calendarName);
	}
	
	//Case77: Add new event into an imported calendar
	@Test
	public void test77_AddNewEvent_InImportedCalendar(){
		String path = "TestData/CS_Calendar_Share_02.ics";
		String calendarName = "CS_Calendar_Event_Add_Calendar_77";
		String desc = "CS_Calendar_Event_Add_Calendar_description_77";
		String color = "SeaGreen ColorCell";
		String eventName = "CS_Calendar_Event_Add_77";
		By elementCalendar = By.xpath(ELEMENT_MY_CAL_ICON.replace("{$calendar}", calendarName));
		
		importCalendar(path, calendarName, desc, "My Group", color);
		waitForElementPresent(elementCalendar);
		info("Import calendar successfully");
		
		quickAddEvent(eventName, null, calendarName);
		deleteCalendar(calendarName);
	}
	
	//Case78: Add new event when its calendar is not selected to view
	@Test
	public void test78_AddNewEvent_CalendarIsNotSelectedToView(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_78";
		String desc = "CS_Calendar_Event_Add_Calendar_description_78";
		String color = "SeaGreen ColorCell";
		String eventName = "CS_Calendar_Event_Add_78";
		String fileName = "CS_Event_Add_78_HireCalendar.jpg";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		String id = getIDOfCalendar(calendarName);
		click(By.xpath(ELEMENT_CALENDAR_CHECKBOX.replace("${calendarName}", id)));
		
		quickAddEvent(eventName, null, calendarName, false);
		waitForElementNotPresent(ELEMENT_QUICK_ADD_EVENT_POPUP);
		captureScreen(fileName);
		waitForElementNotPresent(By.xpath(ELEMENT_EVENT.replace("${eventName}", eventName)));
		
		deleteCalendar(calendarName);
	}
	
	//Case79: Add new event into a category when it is not selected to view
	@Test
	public void test79_AddNewEvent_CategoryIsNotSelectedToView(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_79";
		String desc = "CS_Calendar_Event_Add_Calendar_description_79";
		String color = "SeaGreen ColorCell";
		String eventName = "CS_Calendar_Event_Add_79";
		String fileName = "CS_Event_Add_79_HireCategory.jpg";
		
		quickAddCalendar(calendarName, desc, "My Group", color);	
		addEvent(eventName, null, null, null, false, calendarName, "Meeting");
		
		selectCategoryView("Calls");
		captureScreen(fileName);
		waitForElementNotPresent(By.xpath(ELEMENT_EVENT.replace("${eventName}", eventName)));
		
		deleteCalendar(calendarName);
	}
	
	//Case80: Add new event in not working time
	@Test
	public void test80_AddNewEventInNotWorkingTime(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_79";
		String desc = "CS_Calendar_Event_Add_Calendar_description_79";
		String color = "SeaGreen ColorCell";
		String eventName = "CS_Calendar_Event_Add_79";
		String start = "12:00";
		String end = "14:00";
		String time_from = "10:00";
		String time_to = "10:30";
		String from = FROM.replace("${time_from}", time_from);
		String to = TO.replace("${time_to}", time_to);
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		settingShowWorkingTime(true, start, end);
		waitForElementPresent(By.xpath("//*[contains(@class,'WorkOffTime')]//*[contains(@startfull,'11:30:00')]"));
		waitForElementPresent(By.xpath("//*[contains(@class,'WorkOffTime')]//*[contains(@startfull,'14:00:00')]"));
		waitForElementPresent(By.xpath("//tr[not(contains(@class,'WorkOffTime'))]//td[contains(@startfull,'12:00:00')]"));
		waitForElementPresent(By.xpath("//tr[not(contains(@class,'WorkOffTime'))]//td[contains(@startfull,'13:30:00')]"));
		
		addEvent(eventName, null, from, to, false, calendarName);
		deleteCalendar(calendarName);
		settingShowWorkingTime(true, "00:00", "23:59");
	}
	
	//Case81: Add new event in same day with 4 other events or tasks in Month View
	@Test
	public void test81_AddNewEventInSameDayWith4Events_MonthView(){
		String calendarName = "CS_Calendar_Event_Add_Calendar_81";
		String desc = "CS_Calendar_Event_Add_Calendar_description_81";
		String color = "SeaGreen ColorCell";
		String eventName1 = "CS_Calendar_Event_Add_81_1";
		String eventName2 = "CS_Calendar_Event_Add_81_2";
		String eventName3 = "CS_Calendar_Event_Add_81_3";
		String eventName4 = "CS_Calendar_Event_Add_81_4";
		String eventName5 = "CS_Calendar_Event_Add_81_5";
		String fileName1 = "CS_Event_Add_81_More4Event_InDay.jpg";
		String fileName2 = "CS_Event_Add_81_More4Event_ClickMore.jpg";
		
		quickAddCalendar(calendarName, desc, "My Group", color);
		quickAddEvent(eventName1, null, calendarName);
		quickAddEvent(eventName2, null, calendarName);
		quickAddEvent(eventName3, null, calendarName);
		quickAddEvent(eventName4, null, calendarName);
		
		goToMonthViewTab();
		quickAddEvent(eventName5, null, calendarName, false);
		captureScreen(fileName1);
		waitForElementPresentNotDisplay(By.xpath("//*[@id='UIMonthView']//*[@class='MoreEvent']//*[text()='" + eventName1 + "']"));
		waitForElementPresentNotDisplay(By.xpath("//*[@id='UIMonthView']//*[@class='MoreEvent']//*[text()='" + eventName2 + "']"));
		waitForElementPresentNotDisplay(By.xpath("//*[@class='DayContentContainer EventBoxes']//*[text()='" + eventName3 + "']"));
		waitForElementPresentNotDisplay(By.xpath("//*[@class='DayContentContainer EventBoxes']//*[text()='" + eventName4 + "']"));
		waitForElementPresentNotDisplay(By.xpath("//*[@class='DayContentContainer EventBoxes']//*[text()='" + eventName5 + "']"));
		
		click(By.xpath("//*[@class='MoreEventLabel' and contains(text(),'more')]"));
		captureScreen(fileName2);
		
		deleteCalendar(calendarName);
	}
}