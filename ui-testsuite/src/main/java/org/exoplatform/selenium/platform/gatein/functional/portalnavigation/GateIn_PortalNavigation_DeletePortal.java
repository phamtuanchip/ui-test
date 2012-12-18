package org.exoplatform.selenium.platform.gatein.functional.portalnavigation;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.gatein.GateInBase;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PortalManagement.*;

/**
 *@author VuNA2
 *@date: 18/09/2012
 **/
public class GateIn_PortalNavigation_DeletePortal extends GateInBase{

	public static final By ELEMENT_PORTAL_TOP_CONTAINER = By.id("PortalNavigationTopContainer");
	public static final By ELEMENT_HOME_LINK = By.xpath("//img[@alt='Home']");
	String portalName = "demoPortal"; 
	
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
	
	/*--Case 02 Portal\Portal Navigation\Delete
	 *  Delete portal by legal user
	 * --*/
	@Test
	public void test02_DeletePortalByLegalUser(){
		/*-- Data for test case --*/	
		String portalLocale = "English";
		String portalSkin = "Default"; 
		String portalSession = "On Demand";
		//boolean publicMode = true;
		Map<String, String> permission = new HashMap<String, String>();
	    permission.put("Platform/Administration", "member");  
	    String editGroupId = "Platform/Administration"; 
	    String editMembership = "manager" ;
		
	    info("-- Case 02: Delete portal by legal user --");
		signIn("john", "gtn");
		
		info("-- Go to the portal sites --");
		goToPortalSites();
		
		info("-- Step 1: create a new portal --");
		addNewPortal(portalName, portalLocale, portalSkin,portalSession, 
						false, permission, editGroupId, editMembership);
		
		info("-- Step 2: Check existing portals use list");
		waitForTextPresent(portalName);
		hoverMySites();
		signOut();
		
		info("-- Step 3: Delete portal --");
		driver.get(baseUrl);
		signIn("root", "gtn");
		goToPortalSites();
		deletePortal(portalName);
//		waitForTextNotPresent(portalName);
		
		info("-- End of test case 02: SignOut --");
		signOut();
	}
	
	/*-- Auxiliary functions --*/
	public void hoverMySites(){
		mouseOver(ELEMENT_PORTAL_TOP_CONTAINER, true);
		mouseOver(ELEMENT_HOME_LINK, true);
		click(ELEMENT_HOME_LINK);
		waitForTextPresent("John Smith");
		mouseOver(ELEMENT_MYSITE, true);
	    waitForTextPresent(portalName);
	}
}