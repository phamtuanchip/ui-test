package org.exoplatform.selenium.platform.cs;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 * 
 * @author vuna2
 * @Date: Monday, 7 January, 2013
 */
public class CsBase extends PlatformBase{

	//Go to the calendar's page
	public static final By ELEMENT_CALENDAR_LINK = By.xpath("//a[text()='intranet']/..//a[text()='Calendar']");
	
	/*============= Calendar page ===========*/
	//Event tab
	public static final By ELEMENT_EVENT_TAB = By.xpath("//a[@class='IconHolder QuickAddEvent' and contains(text(),'Event')]"); 
	public static final By ELEMENT_INPUT_EVENT_SUMMARY = By.id("eventName");
	public static By ELEMENT_GET_ID_PAGE = By.xpath("//*[@id='UIPage']/div[@class='UIRowContainer']/div");

	//Task tab
	public static final By ELEMENT_TASK_TAB = By.xpath("//a[@class='IconHolder AddNewTask' and contains(text(),'Task')]");
	public static final By ELEMENT_INPUT_TASK_NAME = By.xpath("//*[@id='eventName']/../../td[contains(text(),'Task')]");

	//Settings tab
	public static final By ELEMENT_SETTINGS_TAB = By.xpath("//*[@class='IconHolder SettingCalendarIcon' and contains(text(),'Settings')]");
	public static final By ELEMENT_VIEW_TYPE_FIELD = By.id("viewType");
	public static final By ELEMENT_SENT_INVITAION_NEVER = By.id("send_never");
	public static final By ELEMENT_SENT_INVITAION_ALWAY = By.id("send_always");
	public static final By ELEMENT_SENT_INVITAION_ASK = By.id("send_ask");
	public static final By ELEMENT_DISPLAY_CALENDAR_TAB = By.xpath("//*[@class='MiddleTab' and text()='Displayed Calendars']");
	public static final String ELEMENT_CALENDAR_CHECKBOX = "//form[@id='UICalendarSettingForm']//*[@id='${calendarId}']";
	
	//RSS tab
	public static final By ELEMENT_CALENDAR_RSS_TAB = By.xpath("//*[@class='IconHolder CalendarRssIcon' and contains(text(),'RSS')]");

	//Header bar
	public static final By ELEMENT_DAY_VIEW_TAB = By.xpath("//a[@class='IconHolder UIDayViewIcon' and contains(text(),'Day')]");
	public static final By ELEMENT_WEEK_VIEW_TAB = By.xpath("//a[@class='IconHolder UIWeekViewIcon' and contains(text(),'Week')]");
	public static final By ELEMENT_MONTH_VIEW_TAB = By.xpath("//a[@class='IconHolder UIMonthViewIcon' and contains(text(),'Month')]");
	public static final By ELEMENT_YEAR_VIEW_TAB = By.xpath("//a[@class='IconHolder UIYearViewIcon' and contains(text(),'Year')]");
	public static final By ELEMENT_LIST_VIEW_TAB = By.xpath("//a[@class='IconHolder UIListContainerIcon' and contains(text(),'List')]");
	public static final By ELEMENT_WORK_WEEK_VIEW_TAB = By.xpath("//a[@class='IconHolder UIWorkingViewIcon' and contains(text(),'Work week')]");
	public static final By ELEMENT_CALENDAR_ADD_EVENT_CATEGORY_HEADER_BAR = By.xpath("//div[@class='AddCategoryIcon' and @title='Event Categories Management']");
	
	//Left panel 
	//Click on Calendar Actions Button
	public static final By ELEMENT_CALENDAR_ACTIONS_BUTTON = By.xpath("//*[@id='UICalendars']//div[@class='ActionsButton']");
	public static final By ELEMENT_CALENDAR_ADD_GROUP_LINK = By.xpath("//*[@id='tmpMenuElement']//a[@class='ItemIcon AddGroupIcon']");
	public static final By ELEMENT_CALENDAR_ADD_LINK = By.xpath("//*[@id='tmpMenuElement']//a[@class='ItemIcon AddCalendarIcon']");
	public static final By ELEMENT_CALENDAR_ADD_EVENT_CATEGORY_LINK = By.xpath("//*[@id='tmpMenuElement']//a[@class='ItemIcon AddCategoryIcon']");
	public static final By ELEMENT_CALENDAR_SETTINGS_LINK = By.xpath("//*[@id='tmpMenuElement']//a[@class='ItemIcon SettingCalendarIcon']");
	public static By ELEMENT_GET_ID_PANE = By.xpath("//*[@id='CalendarApplicationMinWidth']/../..");
	public static String IDPAGE = "";

	//Quick add event tab -> More details
	public static final By ELEMENT_MORE_DETAILS_BUTTON = By.xpath("//*[@id='QuickAddEventContainer']//a[contains(text(), 'More Details')]");
	public static final By ELEMENT_REMINDERS_TAB = By.xpath("//*[@id='UIPopupAddEventContainer']//*[text()='Reminders']");
	public static final By ELEMENT_PARTICIPANTS_TAB = By.xpath("//*[@id='UIPopupAddEventContainer']//*[text()='Participants']");
	public static final By ELEMENT_SCHEDULE_TAB = By.xpath("//*[@id='UIPopupAddEventContainer']//*[text()='Schedule']");
	
	//Quick Search
	public static By ELEMENT_QUICK_SEARCH = By.id("value");
	public static By ELEMENT_QUICK_SEARCH_LIST_RESULT = By.xpath("//*[@class='Result ListBar' and contains(text(),'Search Result')]");
	
	//---------------------Export form-----------------------------------------------------------
	public static By ELEMENT_TASK_EXPORT_POPUP = By.id("UICalendarPopupWindow");
	public static By ELEMENT_TASK_EXPORT_FILE = By.id("name");
	
	/*================== Common functions for CS =================*/
	
	public static void goToCalendarPage(){	
		mouseOver(ELEMENT_MYSITE, true);
		mouseOver(ELEMENT_INTRANET_SITE_LINK, true);
		click(ELEMENT_CALENDAR_LINK);
		waitForElementPresent(ELEMENT_EVENT_TAB);
		IDPAGE = waitForAndGetElement(ELEMENT_GET_ID_PANE).getAttribute("id");
	}

	//Event tab
	public static void goToEvent(){		
		click(ELEMENT_EVENT_TAB);
		
	}

	//Task tab
	public static void goToTask(){
		click(ELEMENT_TASK_TAB);
		waitForElementPresent(ELEMENT_INPUT_TASK_NAME);
	}

	//Settings form
	public static void goToSettings(){
		click(ELEMENT_SETTINGS_TAB);	
		waitForElementPresent(ELEMENT_VIEW_TYPE_FIELD);
	}

	public static void goToSettingsByDropDownMenu(){
		click(ELEMENT_CALENDAR_ACTIONS_BUTTON);
		pause(1000);
		click(ELEMENT_CALENDAR_SETTINGS_LINK);
		waitForElementPresent(ELEMENT_VIEW_TYPE_FIELD);
	}

	//Day view tab	
	public static void goToDayViewTab(){
		click(ELEMENT_DAY_VIEW_TAB);	
		pause(1000);
	}

	//Week view tab
	public static void goToWeekViewTab(){
		click(ELEMENT_WEEK_VIEW_TAB);	
		pause(1000);
	}

	//Month view tab
	public static void goToMonthViewTab(){
		click(ELEMENT_MONTH_VIEW_TAB);	
		pause(1000);
	}

	//Year view tab 
	public static void goToYearViewTab(){
		click(ELEMENT_YEAR_VIEW_TAB);
		pause(1000);
	}

	//List view tab
	public static void goToListViewTab(){
		click(ELEMENT_LIST_VIEW_TAB);
		pause(1000);
	}

	//Work week view tab
	public static void goToWorkWeekViewTab(){
		click(ELEMENT_WORK_WEEK_VIEW_TAB);
		pause(1000);
	}

	//RSS tab
	public static void goToRSSTab(){
		click(ELEMENT_CALENDAR_RSS_TAB);
		waitForTextPresent("Feed");
	}

	//Add group form
	public static void goToAddGroupForm(){
		click(ELEMENT_CALENDAR_ACTIONS_BUTTON);
		pause(1000);
		click(ELEMENT_CALENDAR_ADD_GROUP_LINK);
		waitForTextPresent("Calendar Groups");
	}

	//Add Calendar form
	public static void goToAddCalendarForm(){
		click(ELEMENT_CALENDAR_ACTIONS_BUTTON);
		pause(1000);
		click(ELEMENT_CALENDAR_ADD_LINK);
		waitForTextPresent("Calendar Details");
	}

	//Event Category Form
	public static void goToAddEventCategoryByHeaderBar(){
		click(ELEMENT_CALENDAR_ADD_EVENT_CATEGORY_HEADER_BAR);
		waitForTextPresent("Event Category");
	}

	public static void goToAddEventCategoryFormInCalendarActions(){
		click(ELEMENT_CALENDAR_ACTIONS_BUTTON);
		pause(1000);
		click(ELEMENT_CALENDAR_ADD_EVENT_CATEGORY_LINK);
		waitForTextPresent("Event Category");
	}

	//Add Event > More Details > Reminders tab
	public static void goToRemindersTab(){
		goToEvent();
		click(ELEMENT_MORE_DETAILS_BUTTON);
		//waitForTextPresent("Attachments");
		click(ELEMENT_REMINDERS_TAB);
		//waitForTextPresent("Remind by Email");
	}

	//Add Event > More Details > Participants tab
	public static void goToParticipantsTab(){
		goToEvent();
		click(ELEMENT_MORE_DETAILS_BUTTON);
		//waitForTextPresent("Attachments");
		click(ELEMENT_PARTICIPANTS_TAB);
		//waitForTextPresent("Participants");
	}

	//Add Event > More Details > Schedule tab
	public static void goToScheduleTab(){
		goToEvent();
		click(ELEMENT_MORE_DETAILS_BUTTON);
		//waitForTextPresent("Attachments");
		click(ELEMENT_SCHEDULE_TAB);
		//waitForTextPresent("Apply Selected Date");
	}
	
	/**
	 * @author lientm
	 * @param key: keyword that needs search
	 * @return: true -> has result
	 *          false -> does not have any result
	 */
	public static boolean quickSearch(String key){
		String page_id = waitForAndGetElement(ELEMENT_GET_ID_PAGE).getAttribute("id");
		
		info("Quick search with key " + key);
		type(ELEMENT_QUICK_SEARCH, key, true);
		((JavascriptExecutor) driver).executeScript("javascript:eXo.webui.UIForm.submitForm('" + page_id + "#UISearchForm','Search',true)");
		waitForElementPresent(ELEMENT_QUICK_SEARCH_LIST_RESULT);
		if (isTextPresent("No events or tasks to show")){
			return false;
		} else return true;
	}
	
	/**
	 * @author lientm
	 * @param way: way to choose send event invitation
	 */
	public static void chooseWayToSendInvitaion(int way){
		switch (way){
		case 1: click(ELEMENT_SENT_INVITAION_NEVER);
		        break;		
		case 2: click(ELEMENT_SENT_INVITAION_ALWAY);
				break;
		default: click(ELEMENT_SENT_INVITAION_ASK);
				break;
		}
	}
	
	/**function set up send event invitation for a calendar
	 * @author lientm
	 * @param way: way to choose send event invitation
	 */
	public static void setSendEventInvitationForCalendar(int way){
		goToSettings();
		chooseWayToSendInvitaion(way);
		save();
		waitForElementNotPresent(ELEMENT_VIEW_TYPE_FIELD);
	}
	
	/**function export a item (calendar/task/event)
	 * @author lientm
	 * @param fileName
	 */
	public static void exportItem(String fileName){
		waitForElementPresent(ELEMENT_TASK_EXPORT_POPUP);
		type(ELEMENT_TASK_EXPORT_FILE, fileName, true);
		save();
		waitForElementNotPresent(ELEMENT_TASK_EXPORT_POPUP);
	}
	
	/**function return value id of calendar page
	 * @author lientm
	 * @return
	 */
	public static String getPageId(){
		return waitForAndGetElement(By.xpath("//*[@id='UIPage']/div/div")).getAttribute("id");
	}
}
