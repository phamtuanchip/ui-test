package org.exoplatform.selenium.platform.cs;

import static org.exoplatform.selenium.TestLogger.info;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class View extends Task {
	public static String ELEMENT_CALENDAR_CHECKBOX = "//*[@id='${calendarName}' and @type='checkbox']";
	public static By ELEMENT_SELECT_CATEGORY = By.id("eventCategories");
	public static By ELEMENT_WEEK_VIEW_NEXT_ICON = By.xpath("//*[@id='UIWeekView']//*[@class='NextIcon']");
	public static By ELEMENT_WEEK_VIEW_BACK_ICON = By.xpath("//*[@id='UIWeekView']//*[@class='BackIcon']");
	
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
}
