package org.exoplatform.selenium.platform.gatein.functional.group.administration;

import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.ManageApplications.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author NhungVT
 *@date: 25/09/2012
 */

public class GateIn_Group_Administration_ApplicationRegistry_Display extends GateInBase
{
	
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
	
	//Show Application portlet when check or un-check Change show import
	@Test()
	public void test01_ShowImportApplication()
	{
		//goto Application
		goToApplicationRegistry();
		
		//Verify Categories display as default
		waitForElementPresent(ELEMENT_CATEGORIES_AREA_TITLE);
		
		//Import Application is shown as default
		waitForElementPresent(ELEMENT_IMPORT_APPLICATION);
		
		//goto Edit Portlet
		goToEditPageGroupEditor();
		
		//Click on Edit Portlet icon
		mouseOver(ELEMENT_APPS_REG_PORTLET, false);
		click(ELEMENT_EDIT_PORTLET_ICON);
		
		//Verify Change Show Import checkbox is uncheck
		waitForElementPresent(SHOW_IMPORT_CHECKED);
		
		//Select checkbox
		uncheck(ELEMENT_SHOW_IMPORT_CHECKBOX);
		save();
		close();
		
		//Verify Change Show Import checkbox is checked
		mouseOver(ELEMENT_APPS_REG_PORTLET, false);
		click(ELEMENT_EDIT_PORTLET_ICON);
		waitForElementNotPresent(SHOW_IMPORT_CHECKED);
		close();
		
		//Click Finish
		click(ELEMENT_FINISH_ICON);
		
		//Verify Import Applications is shown
		waitForElementNotPresent(ELEMENT_IMPORT_APPLICATION);
		
		//Reset data
		goToEditPageGroupEditor();
		mouseOver(ELEMENT_APPS_REG_PORTLET, false);
		click(ELEMENT_EDIT_PORTLET_ICON);
		waitForElementNotPresent(SHOW_IMPORT_CHECKED);
		check(ELEMENT_SHOW_IMPORT_CHECKBOX);
		save();
		close();
		click(ELEMENT_FINISH_ICON);
		waitForElementPresent(ELEMENT_IMPORT_APPLICATION);
	}
	
	@AfterMethod()
	public void afterTest()
	{
		driver.quit();
	}
}