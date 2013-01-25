package org.exoplatform.selenium.platform.gatein.functional.groupnavigation.managenode;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.gatein.GroupNavigation.*;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationManagement.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PageManagement.*;

/**
 *@author NhungVT
 *@date: 24/09/2012
 */

public class GateIn_GroupNavigation_ManageNode_Delete extends GateInBase
{
	//Define data
	public By ADMIN_EDIT_NAVIGATION_LINK = By.xpath("//td/div[text()='Administrators']/ancestor::tr/td/a[text()='Edit Navigation']");
	public By ELEMENT_APPLICATION_REGIS = By.xpath("//a[@title='Application Registry']");
	public By NODE_ADDED = By.xpath("//a[text()='POR_GRNAVIGATION_25_06']");
	public String NODE_NAME = "POR_GRNAVIGATION_25_06";
	public String PAGE_SELECTOR_NAME = "POR_GRNAVIGATION_25_06_PAGE";
	
	@BeforeMethod
	public void beforeMethods() 
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("root", "gtn");
	}
	
	//Delete node with deleting confirmation
	@Test()
	public void test01_DeleteNodeWithConfirmation()
	{
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		
		//Goto Setting > Portal > Group Sites
		goToGroupSites();
		
		//Click Edit Navigation of Employees
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Add Node for test
		addNodeForGroup("Administrators", "Application Registry", false, NODE_NAME, true, languages, NODE_NAME, PAGE_SELECTOR_NAME, PAGE_SELECTOR_NAME, true, true);
		
		//Verify added data
		waitForElementPresent(ADMIN_EDIT_NAVIGATION_LINK);
		click(ADMIN_EDIT_NAVIGATION_LINK);
		click(ELEMENT_APPLICATION_REGIS);
		waitForElementPresent(NODE_ADDED);
		
		//Delete data
		deleteNode("Administrators", "Application Registry", NODE_NAME, false);
		
		//Verify page selector still exists
		goToManagePages();
		searchPageByTitle(PageType.GROUP, PAGE_SELECTOR_NAME);
		
		//Delete data
		deletePage(PageType.GROUP, PAGE_SELECTOR_NAME);
	}
	
	@AfterMethod()
	public void afterTest()
	{
		signOut();
		driver.quit();
	}
}