package org.exoplatform.selenium.platform.gatein.functional.others;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.gatein.ManageAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.gatein.ManageApplications.*;
import static org.exoplatform.selenium.gatein.NavigationManagement.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PageManagement.*;
import static org.exoplatform.selenium.TestLogger.*;

/**
 * 
 * @author thaopth
 * date: 26/09/2012
 */

public class GateIn_Others_Sitemap extends ManageAccount {
	
	@BeforeMethod
	public void beforeMethods(){
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest(){
		//signOut();
		driver.quit();
	}
	
	/* Case 01: Check site map portlet
	 * Import all applications
	 * Add page with site map portlet
	 * Check site map portlet
	 */
	@Test
	public void test01_CheckSiteMapPortlet () {
		String DATA_NODE_NAME = "Sitemap1";
		String DATA_LANGUAGE = "English";
		String DATA_CATEGORY_TITLE = "Navigation";
		Map<String, String> portletIds = new HashMap<String, String>();
		portletIds.put("Navigation/local._web.SiteMapPortlet", "");
		By SITEMAP_PORTLET = By.className("UIRowContainer");
		By ELEMNT_FORUMS_LINK = By.linkText("Forums");
		By ELEMENT_FORUM_ACTION_BAR = By.id("UIForumActionBar");
		
		signIn("john", "gtn");
		showImportApplication(true);
			
		info("Import all apps");
		click(ELEMENT_IMPORT_APPLICATION);
		waitForConfirmation("This will automatically import all gadgets and portlets into new categories.");
		pause(2000);
		
		info("Add page");
		goToAddPageEditor();
		
		info("Add new page");
		addNewPageEditor(DATA_NODE_NAME, DATA_NODE_NAME, DATA_LANGUAGE, DATA_CATEGORY_TITLE, portletIds, true);
		waitForElementPresent(SITEMAP_PORTLET);
		
		info("Click on a page");
		click(ELEMNT_FORUMS_LINK);
		pause(1000);
		
		info("Verify home page is displayed");
		waitForElementPresent(ELEMENT_FORUM_ACTION_BAR);
		
		/*----Reset data-------*/
		
		info("Delete page");
		goToManagePages();
		deletePage(PageType.GROUP,DATA_NODE_NAME);
		goToGroupSites();
		deleteNode("Administration", "Application Manager", DATA_NODE_NAME, false);
		showImportApplication(false);
		
	}
}