package org.exoplatform.selenium.platform.cs;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

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
	
	//Task tab
	public static final By ELEMENT_TASK_TAB = By.xpath("//a[@class='IconHolder AddNewTask' and contains(text(),'Task')]");
    public static final By ELEMENT_INPUT_TASK_NAME = By.xpath("//*[@id='eventName']/../../td[contains(text(),'Task')]");
	
	//Settings tab
	public static final By ELEMENT_SETTINGS_TAB = By.xpath("//*[@class='IconHolder SettingCalendarIcon' and contains(text(),'Settings')]");
	public static final By ELEMENT_VIEW_TYPE_FIELD = By.id("viewType");

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
	
	/*================== Common functions for CS =================*/
	public static void goToCalendarPage(){
		mouseOver(ELEMENT_MYSITE, true);
		mouseOver(ELEMENT_INTRANET_SITE_LINK, true);
		click(ELEMENT_CALENDAR_LINK);
		waitForElementPresent(ELEMENT_EVENT_TAB);
	}

	//Event tab
	public static void goToEvent(){		
		click(ELEMENT_EVENT_TAB);
		waitForElementPresent(ELEMENT_INPUT_EVENT_SUMMARY);
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

}
