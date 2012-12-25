package org.exoplatform.selenium.platform.gatein.functional.groupnavigation;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;

/**
 *@author NhungVT
 *@date: 20/09/2012
 */

public class GateIn_GroupNavigation_Navigation_EditProperties extends GateInBase
{
	//Define data
	public By ADMIN_EDIT_PROPERTIES_LINK = By.xpath("//td/div[text()='Administrators']/ancestor::tr/td/a[text()='Edit Properties']");
	public By PRIORITY_SELECT = By.xpath("//select[@name='priority']");
	public By OWNER_TYPE_INPUT = By.xpath("//input[@id='ownerType' and @readonly='' or @readonly ='readonly']");
	public By OWNER_ID_INPUT = By.xpath("//input[@id='ownerId' and @readonly='' or @readonly ='readonly']");
	public By PRIORITY_OPTION_1 = By.xpath("//option[@value='2' and @selected='selected']");
	public By PRIORITY_OPTION_2 = By.xpath("//option[@value='6' and @selected='selected']");
	public By ADMINISTRATION_OLD_POSTION = By.xpath("//div[@id='UIGroupNavigationGrid']//table[1]//div[@title='/platform/administrators']");
	public By ADMINISTRATION_NEW_POSTION = By.xpath("//div[@id='UIGroupNavigationGrid']//table[2]//div[@title='/platform/administrators']");

	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("root", "gtn");
	}

	//Edit properties of group navigation
	@Test()
	public void test01_EditPropertiesOfGroupnavigation()
	{
		//Goto Group Sites
		goToGroupSites();

		//Verify position of Administration before change order
		waitForElementPresent(ADMINISTRATION_OLD_POSTION);
		captureScreen("POR_GRNAVIGATION_24_03_GroupSites_BeforeChangeOrder");

		//Select Edit Properties of Administration
		waitForElementPresent(ADMIN_EDIT_PROPERTIES_LINK);
		click(ADMIN_EDIT_PROPERTIES_LINK);

		//Verify OwnerType & OwnerId can not be changed
		waitForElementPresent(OWNER_TYPE_INPUT);
		waitForElementPresent(OWNER_ID_INPUT);
		waitForElementPresent(PRIORITY_OPTION_1);

		//Change number of priority
		select(PRIORITY_SELECT, "6");
		save();

		//Verify position of Administration after changing order
		waitForElementNotPresent(ADMINISTRATION_OLD_POSTION);
		waitForElementPresent(ADMINISTRATION_NEW_POSTION);
		captureScreen("POR_GRNAVIGATION_24_03_GroupSites_AfterChangeOrder");

		//Verify priority is changed
		waitForElementPresent(ADMIN_EDIT_PROPERTIES_LINK);
		click(ADMIN_EDIT_PROPERTIES_LINK);
		waitForElementPresent(PRIORITY_OPTION_2);

		//Sign out and Sign in again
		signOut();
		signIn("root", "gtn");

		//Goto Group Sites
		goToGroupSites();

		//Verify position of Administration after Signing out and Signing in again  
		waitForElementNotPresent(ADMINISTRATION_OLD_POSTION);
		waitForElementPresent(ADMINISTRATION_NEW_POSTION);

		//Reset order of navigation list
		waitForElementPresent(ADMIN_EDIT_PROPERTIES_LINK);
		click(ADMIN_EDIT_PROPERTIES_LINK);
		waitForElementPresent(PRIORITY_OPTION_2);
		select(PRIORITY_SELECT, "1");
		save();

		//Verify data is reset
		waitForElementPresent(ADMINISTRATION_OLD_POSTION);
		waitForElementNotPresent(ADMINISTRATION_NEW_POSTION);
	}

	@AfterMethod()
	public void afterTest()
	{
		signOut();
		driver.quit();
	}
}