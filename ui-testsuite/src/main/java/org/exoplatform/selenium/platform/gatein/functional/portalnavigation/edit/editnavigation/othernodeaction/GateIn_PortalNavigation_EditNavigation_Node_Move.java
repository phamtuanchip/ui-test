package org.exoplatform.selenium.platform.gatein.functional.portalnavigation.edit.editnavigation.othernodeaction;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.NavigationManagement.*;

public class GateIn_PortalNavigation_EditNavigation_Node_Move extends GateInBase
{
	public String EDIT_classic_NAVIGATION = "//div[text()='classic']/following::a[text()='Edit Navigation']";
	public String MOVE_UP_LINK = "Move Up";
	public String MOVE_DOWN_LINK = "Move Down";
	public String HOME_POSITION = "//div[2]/div/a";
	public String NOT_FOUND_POSITION = "//div[7]/div/a";
	public String SITEMAP_OLD_POSITION = "//div[3]/div/a[contains(text(),'SiteMap')]";
	public String SITEMAP_NEW_POSITION = "//div[2]/div/a";
	public String REGISTER_OLD_POSITION = "//div[6]/div/a";
	public String REGISTER_NEW_POSITION = "//div[7]/div/a";
	
	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		signIn("root", "gtn");
	}
	
	//Change order of node in case Move Up or Move Down
	@Test()
	public void test01_ChangeNodeOrder()
	{	
		String nodeHome = ELEMENT_NODE_LINK.replace("${nodeLabel}", "Home");
		String nodeSite = ELEMENT_NODE_LINK.replace("${nodeLabel}", "SiteMap");
		String nodeNotFound = ELEMENT_NODE_LINK.replace("${nodeLabel}", "NotFound");
		String nodeRegister = ELEMENT_NODE_LINK.replace("${nodeLabel}", "Register");
		
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		info("-START test01_ChangeNodeOrder");
		
		//Goto Edit Navigation
		info("---Goto Edit Navigation");
		goToPortalSites();
		
		//Click on Edit Navigation of classic
		info("---Click on Edit Navigation of Classic");
		click(EDIT_CLASSIC_NAVIGATION);
		
		info("---Verify can't Move Up order of first node");
		
		//Verify position of node Home 
		info("-----Verify position of node Home before Move Up");
		waitForElementPresent(HOME_POSITION);
		
		info("-----Click on Move Up item of node Home");
		//Right click on node Home
		rightClickOnElement(nodeHome);
		
		//Click on Move Up item
		click(By.linkText(MOVE_UP_LINK));
		
		//Verify nothing happens ~ position of node Home is not changed
		info("-----Verify position of node Home is not changed after Move Up");
		waitForElementPresent(HOME_POSITION);
				
		info("---Verify can't Move Down order of last node");
		
		//Verify position of node ACCOUNT 
		info("-----Verify position of node NEW ACCOUNT before Move Down");
		waitForElementPresent(NOT_FOUND_POSITION);
		
		info("-----Click on Move Down of node NEW ACCOUNT");
		//Right click on node
		rightClickOnElement(nodeNotFound);
		
		//Click on Move Down item
		click(By.linkText(MOVE_DOWN_LINK));
		
		//Verify nothing happens ~ position of node New ACCOUNT is not changed
		info("-----Verify position of node site map is not changed after Moving Down");
		waitForElementPresent(NOT_FOUND_POSITION);
		
		info("---Verify can Move Up node is not first node");
		
		//Verify position of node SiteMap
		info("-----Verify position of node SiteMap before Moving Up");
		waitForElementPresent(SITEMAP_OLD_POSITION);
		
		info("-----Click on Move Up item of node SiteMap");
		//Right click on node SiteMap
		rightClickOnElement(nodeSite);
		
		//Click on Move Up item of node SiteMap
		click(By.linkText(MOVE_UP_LINK));
		
		//Verify position of SiteMap is changed
		info("-----Verify position of node SiteMap is changed after Moving Up");
		waitForElementPresent(SITEMAP_NEW_POSITION);
		
		info("---Verify can Move Down node which is not last node");
		
		//Verify position of node Register
		info("-----Verify position of node Register before Moving Down");
		waitForElementPresent(REGISTER_OLD_POSITION);
		
		info("-----Click on Move Down item of node Register");
		//Right click on node Register
		rightClickOnElement(nodeRegister);
		
		//Click on Move Down item of node Register
		click(By.linkText(MOVE_DOWN_LINK));
		
		//Verify position of Register is changed
		info("-----Verify position of node Register is changed after Moving Down");
		waitForElementPresent(REGISTER_NEW_POSITION);
		
		info("-END test01_ChangeNodeOrder");
	}
	
	@AfterMethod()
	public void afterTest()
	{
		signOut();
		driver.quit();
	}
}