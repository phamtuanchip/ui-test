package org.exoplatform.selenium.platform.cs;

import static org.exoplatform.selenium.TestLogger.info;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class View extends Event {
	public static String ELEMENT_CALENDAR_CHECKBOX = "//*[@id='${calendarName}' and @type='checkbox']";
	public static By ELEMENT_SELECT_CATEGORY = By.id("eventCategories");
	
	//--------------------------Week view--------------------------------------------
	public static By ELEMENT_WEEK_VIEW_NEXT_ICON = By.xpath("//*[@id='UIWeekView']//*[@class='NextIcon']");
	public static By ELEMENT_WEEK_VIEW_BACK_ICON = By.xpath("//*[@id='UIWeekView']//*[@class='BackIcon']");
	public static String ELEMENT_EVENT_WEEK_VIEW = "//td[@startfull='" + getCurrentDate("EEE MMM dd yyyy") + " 00:00:00']//*[text()='${eventName}']/..//p[contains(text(), '${time_from} - ${time_to}')]";
	public static String ELEMENT_EVENT_FULLDAY_WEEKVIEW = "//*[@id='UIWeekViewGridAllDay']//div[@endtimefull='" + getCurrentDate("EEE MMM dd yyyy") + " ${time_to}:00' and @starttimefull='" + getCurrentDate("EEE MMM dd yyyy") + " ${time_from}:00']/*[contains(text(),'${eventName}')]";
	public static String ELEMENT_EVENT_FULLFEWDAY_WEEKVIEW = "//*[@id='UIWeekViewGridAllDay']//div[@endtimefull='${to_date} ${time_to}:00' and @starttimefull='" + getCurrentDate("EEE MMM dd yyyy") + " ${time_from}:00']/*[contains(text(),'${eventName}')]";
	public static String ELEMENT_EVENT_ALLDAY_WEEKVIEW = "//*[@id='UIWeekViewGridAllDay']//div[@endtimefull='" + getCurrentDate("EEE MMM dd yyyy") + " ${time_to}:59' and @starttimefull='" + getCurrentDate("EEE MMM dd yyyy") + " ${time_from}:00']/*[contains(text(),'${eventName}')]";
	public static String ELEMENT_EVENT_ALLFEWDAY_WEEKVIEW = "//*[@id='UIWeekViewGridAllDay']//div[@endtimefull='${to_date} ${time_to}:59' and @starttimefull='" + getCurrentDate("EEE MMM dd yyyy") + " ${time_from}:00']/*[contains(text(),'${eventName}')]";
	
	//--------------------------Day view---------------------------------------------
	public static String ELEMENT_EVENT_DAY_VIEW = "//*[@id='UIDayViewGrid']//p[contains(text(), '${time_from} - ${time_to}')]/../..//*[contains(text(), '${eventName}')]";
	public static String ELEMENT_EVENT_FULLDAY_DAYVIEW = "//*[@class='EventAllDay']//*[@title='" + getCurrentDate("MM/dd/yyyy") + " ${time_from}:->" + getCurrentDate("MM/dd/yyyy") + " ${time_to}' and contains(text(),'${eventName}')]";

	//--------------------------Month view-------------------------------------------
	public static String ELEMENT_EVENT_MONTH_VIEW = "//*[@id='UIMonthView']//*[@class='DayContentContainer EventBoxes' and @title = '${eventName}' and @starttimefull = '" + getCurrentDate("EEE MMM dd yyyy") + " ${time_from}:00' and @endtimefull = '" + getCurrentDate("EEE MMM dd yyyy") + " ${time_to}:00']";
	public static String ELEMENT_EVENT_FEWDAY_MONTH_VIEW = "//*[@id='UIMonthView']//*[@class='DayContentContainer EventBoxes' and @title = '${eventName}' and @starttimefull = '" + getCurrentDate("EEE MMM dd yyyy") + " ${time_from}:00' and @endtimefull = '${to_date} ${time_to}:00']";
	public static String ELEMENT_EVENT_FULLDAY_MONTHVIEW = "//*[@id='UIMonthView']//*[@class='DayContentContainer EventBoxes' and @title = '${eventName}' and @starttimefull = '" + getCurrentDate("EEE MMM dd yyyy") + " ${time_from}:00' and @endtimefull = '" + getCurrentDate("EEE MMM dd yyyy") + " ${time_to}:59']";

	//--------------------------List view--------------------------------------------
	public static String ELEMENT_EVENT_ATTACHMENT_IN_LIST = "//*[@id='UIListUsers']//tr/td/*[@class='AttachmentIcon']/../..//*[@title='${eventName}']";
	public static String ELEMENT_EVENT_CHECKBOX = "//*[@title='${eventName}']/../..//input[@type='checkbox']";
	public static String ELEMENT_EVENT_ATTACHMENT_DETAIL = "//*[@id='RowContainerDay']//div[@class='AttachmentIcon']/a[contains(@title,'${fileName}')]";
	public static String ELEMENT_EVENT_ATTACHMENT_VIEW = "//*[@id='RowContainerDay']//div[@class='AttachmentIcon']/a[contains(@title,'${fileName}')]/../following-sibling::div[1]/a[contains(text(), 'View')]";
	public static String ELEMENT_EVENT_ATTACHMENT_CLOSE_VIEW = ".//*[@id='RowContainerDay']//div[@class='AttachmentIcon']/a[contains(@title,'${fileName}')]/../following-sibling::div[1]/a[text()='Close']";
	
	/**function show/hire a task/event by check/uncheck calendar
	 * @author lientm
	 * @param calendarName: name of calendar
	 * @param name: name of task/event
	 * @param show: = true -> show
	 *              = false -> hire
	 * @param mode: = 1 -> day/week view mode
	 *              != 1 -> month view mode
	 */
	public static void showHideTaskEvent(String calendarName, String name, boolean show, int...mode){
		String calendar = getIDOfCalendar(calendarName);
		By element_checkbox = By.xpath(ELEMENT_CALENDAR_CHECKBOX.replace("${calendarName}", calendar));
		WebElement checkbox = waitForAndGetElement(element_checkbox);
		
		if (show){
			if (!checkbox.isSelected()){
				check(element_checkbox);
			}
			By task = getTaskFromViewMode(name, mode);
			waitForElementPresent(task);
			info("Show task/event of calendar successfully");
		}else {
			if (checkbox.isSelected()){
				uncheck(element_checkbox);
			}
			By task = getTaskFromViewMode(name, mode);
			waitForElementNotPresent(task);
			info("Hire task/event of calendar successfully");
		}			
	}
	
	/**function select category to view
	 * @author lientm
	 * @param category: name of category
	 */
	public static void selectCategoryView(String category){
		select(ELEMENT_SELECT_CATEGORY, category);
		pause(1000);
	}
	
	/**function go to a week on week view
	 * @author lientm
	 * @param elementWeek
	 */
	public static void goToWeek(By elementWeek, boolean next){
		while (waitForAndGetElement(elementWeek, 5000, 0) == null){
			if (next){
				click(ELEMENT_WEEK_VIEW_NEXT_ICON);
			}else{
				click(ELEMENT_WEEK_VIEW_BACK_ICON);
			}
		}
	}
	
	public static void checkAttachmentsDisplayInListView(String eventName, String file){
		waitForElementPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_IN_LIST.replace("${eventName}", file)));
		check(By.xpath(ELEMENT_EVENT_CHECKBOX.replace("${eventName}", eventName)));
		waitForElementPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_DETAIL.replace("${fileName}", file)));
		click(By.xpath(ELEMENT_EVENT_ATTACHMENT_VIEW.replace("${fileName}", file)));
		waitForElementPresent(By.xpath(ELEMENT_EVENT_ATTACHMENT_CLOSE_VIEW.replace("${fileName}", file)));
	}
}
