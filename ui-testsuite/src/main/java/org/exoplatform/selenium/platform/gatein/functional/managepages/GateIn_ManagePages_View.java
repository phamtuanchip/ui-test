package org.exoplatform.selenium.platform.gatein.functional.managepages;

import java.util.Map;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PageManagement.*;

/**
 *@author VuNA2
 *@date: 18/09/2012
 **/
public class GateIn_ManagePages_View extends GateInBase{
	/*-- Data for test case --*/
    By ELEMENT_PORTAL_TOP_CONTAINER = By.id("PortalNavigationTopContainer");
	String ELEMENT_CURRENT_NAVIGATION = "intranet";
	
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest(){
		driver.quit();
	}
	
	/*--Case 02 Portal\Manage Pages\View
	 *  Check displaying page created in pages management
	 * --*/
	@Test
	public void test09_CheckDisplayingPageCreatedInPagesManagement(){
		PageType type = PageType.PORTAL; 
		String pageName = "demoPage"; 
		String pageTitle = "demoPage";
	   	 boolean publicMode = true;
	    	Map<String, String> permissions = null;   
	    	String groupId = "Platform/Administration"; 
	    	String membership = "manager";
	    
		info("-- Starting Case 09: Check displaying page created in pages management --");
		
		signIn("john", "gtn");
		
		info("-- Step 1: Show pages list --");
		goToManagePages();
		
		info("-- Step 2: Create new page for portal --");
		addNewPageAtManagePages(type, pageName, pageTitle, publicMode, 
				                permissions, groupId, membership);
		
		info("-- Step 3: View created page from navigation --");
		signOut();
		driver.get(baseUrl);
		signIn("root", "gtn");
		goToMySitesAndVerifyPage(pageName);
		
		info("-- Step 4: View created page in Edit Page & Navigation --");
		goToPortalSites();
		editNavigation(ELEMENT_CURRENT_NAVIGATION);
		waitForTextNotPresent(pageName);
		save();
		waitForTextPresent(ELEMENT_CURRENT_NAVIGATION);
		
		info("-- Delete date after testing--");
		goToManagePages();
		deletePage(type, pageTitle);
		waitForTextNotPresent(pageTitle);
		
		info("-- End of test case 09: SignOut --");
		signOut();
	}
	
	/*-- Auxiliary functions --*/
	public boolean goToMySitesAndVerifyPage(String pageName){
		WebElement element = driver.findElement(ELEMENT_PORTAL_TOP_CONTAINER);
		actions.moveToElement(element).build().perform();
		mouseOver(ELEMENT_MYSITE, true);
	    	waitForTextPresent(ELEMENT_CURRENT_NAVIGATION);
	    	mouseOver(By.linkText(ELEMENT_CURRENT_NAVIGATION), true);
	    	mouseOver(By.linkText("Home"), true);
	    	waitForTextNotPresent(pageName);
	    	mouseOver(By.linkText("Forums"), true);
	    	waitForTextNotPresent(pageName);
	    	mouseOver(By.linkText("Calendar"), true);
	    	waitForTextNotPresent(pageName);
	    	mouseOver(By.linkText("Documents"), true);
	    	waitForTextNotPresent(pageName);
	    	mouseOver(By.linkText("Wiki"), true);
	    	waitForTextNotPresent(pageName);
	    	return false; 
	}
}