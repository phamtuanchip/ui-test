package org.exoplatform.selenium.platform.cs;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CsBaseTest extends CsBase{

	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		actions = new Actions(driver);
		signIn(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("-- Logout --");
		signOut();
		driver.quit();
		actions = null;
	}
	
	@Test
	public void test01_CsBaseTest(){
		goToCalendarPage();
		goToEvent();
		cancel();
		goToTask();
		cancel();
		goToSettings();
		cancel();
		//goToDayViewTab();
		//goToWeekViewTab();
//		goToMonthViewTab();
//		goToYearViewTab();
//		goToListViewTab();
//		goToWorkWeekViewTab();	
//		goToSettingsByDropDownMenu();
//		cancel();
//		goToRSSTab();
//		cancel();
		goToAddGroupForm();
		close();
		goToAddCalendarForm();
		cancel();
		goToAddEventCategoryByHeaderBar();
		close();
		goToAddEventCategoryFormInCalendarActions();
		close();
	}
}
