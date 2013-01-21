package org.exoplatform.selenium.platform.cs.functional.calendar.calendar;

import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.checkAlertWarning;

import org.exoplatform.selenium.platform.cs.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author lientm
 * @date 21/01/2013
 */
public class CS_Calendar_Calendar_Add extends Calendar {
	public static String ELEMENT_CALENDAR_IN_GROUP = "//*[@title='${groupName}']/../..//*[@class='CalendarName' and text()='${calendarName}']";
	
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
	
	//Case01: Add calendar without any group
	@Test
	public void test01_AddCalendarWithoutGroup(){
		String calendar = "CS_Calendar_Add_01";
		
		//delete group default
		deleteAllGroup();
		
		info("Add Calendar without Group");
		quickAddCalendar(calendar, null, null, null, false);		
		checkAlertWarning(MESSAGE_ADD_CALENDAR_NOGROUP);
		cancel();
		
		//return group default
		addGroup("My Group", null);
		close();
		waitForElementNotPresent(ELEMENT_GROUP_NAME_INPUT);
		quickAddCalendar("Default", null, "My Group", null);
	}
	
	//Case02: Add new calendar into the default group
	@Test
	public void test02_AddCalendarWithDefaultGroup(){
		String calendar = "CS_Calendar_Add_02";
		String desc = "CS_Calendar_Add_description_02";
		String color = "OrangeRed ColorCell";
		By element_calendar = By.xpath(ELEMENT_CALENDAR_IN_GROUP.replace("${groupName}", "My Group").replace("${calendarName}", calendar));
		
		info("Add new calendar with default group");
		quickAddCalendar(calendar, desc, "My Group", color);
		waitForElementPresent(element_calendar);
		assert getColorOfCalendar(calendar).equalsIgnoreCase(color.split(" ")[0]);
		
		info("Delete calendar");
		deleteCalendar(calendar);
	}
	
	//Case03: Add new calendar into specific  group
	@Test
	public void test03_AddCalendarWithSpecificGroup(){
		String groupName = "CS_Calendar_Add_group_03";
		String calendar = "CS_Calendar_Add_03";
		String desc = "CS_Calendar_Add_description_03";
		String color = "OrangeRed ColorCell";
		By element_calendar = By.xpath(ELEMENT_CALENDAR_IN_GROUP.replace("${groupName}", "My Group").replace("${calendarName}", calendar));
		
		info("Add new calendar in group" + groupName);
		goToAddCalendarForm();
		waitForAndGetElement(ELEMENT_GROUP_CAL_INPUT_OPTION_DEFAULT).getText().equalsIgnoreCase("My Group");
		inputFormCalendarDetail(calendar, desc, groupName, color);
		save();
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
		waitForElementPresent(element_calendar);
		assert getColorOfCalendar(calendar).equalsIgnoreCase(color.split(" ")[0]);
		
		info("Delete group");
		deleteGroup(groupName);
	}
	
	//Case04: Add new calendar with invalid value
	@Test
	public void test04_AddCalendarWithInvalidValue(){		
		goToAddCalendarForm();
		for (int i = 0; i < DATA_SPECIAL_CHARACTER_NOT_FULL.length; i ++){
			type(ELEMENT_NAME_CAL_INPUT, DATA_SPECIAL_CHARACTER_NOT_FULL[i], true);
			save();
			checkAlertWarning(MESSAGE_ADD_CALENDAR_INVALID_NAME);
		}
		cancel();
	}
	
	//Case06: Add new calendar with duplicated name
	@Test
	public void test06_AddCalendarWithDuplicatedName(){
		String groupName = "CS_Calendar_Add_group_06";
		String calendar = "CS_Calendar_Add_06";
		String desc = "CS_Calendar_Add_description_06";
		String color = "Orchid ColorCell";
		By element_calendar = By.xpath(ELEMENT_CALENDAR_IN_GROUP.replace("${groupName}", "My Group").replace("${calendarName}", calendar));
		
		info("Add new calendar in this group with name: " + calendar);
		quickAddCalendar(calendar, desc, groupName, color);
		waitForElementPresent(element_calendar); 
		
		info("Add other calendar in My Group with same name");
		quickAddCalendar(calendar, desc, groupName, color, false);
		checkAlertWarning(MESSAGE_ADD_CALENDAR_DUPLICATE_NAME.replace("${calendarName}", calendar));
		cancel();
		
		info("Delete Group");
		deleteGroup(groupName);
	}
}
