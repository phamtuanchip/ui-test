package org.exoplatform.selenium.platform.gatein.functional.groupnavigation;

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

/**
 * @author NhungVT
 * @date: 24/09/2012	
 */

public class GateIn_GroupNavigation_Node_CutPaste extends GateInBase
{
	//Define data
	public By ADMINISTRATION_LINK = By.xpath("//a[@title='Administration']");
	public By ELEMENT_APPLICATION_REGIS = By.xpath("//a[@title='Application Registry']");
	public By IDE_LINK = By.xpath("//a[@title='IDE']");
	public By CHILD_NODE = By.xpath("(//a[contains(text(),'POR_GRNAVIGATION_25_05_002')])");
	public By CLOSE_NAVIGATION_ICON = By.xpath("//a[contains(@title,'Close Window')]");
	public String NODE_NAME = "POR_GRNAVIGATION_25_05_002";
	public By EXECUTIVE_EDIT_NAVI_LINK = By.xpath("//td/div[text()='Executive Board']/ancestor::tr/td/a[text()='Edit Navigation']");
	public By ELEMENT_NEW_STAFF = By.linkText("New Staff");

	//Product Messages

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
	
	//Cut/Paste node to same place
	@Test()
	public void test01_CutPasteNodeInSamePlace()
	{
		//Goto Setting > Portal > Group Sites
		goToGroupSites();
		
		//Click Edit navigation of Administration 
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Copy Node Portal Administration 
		cutNode(ADMINISTRATION_LINK);
		
		//Paste to Portal Administration
		pasteNode(ADMINISTRATION_LINK);
		
		//Verify message confirmation
		waitForMessage(MSG_SAME_SOURCE);
		click(ELEMENT_OK_BUTTON);
	}
	
	//Cut/Paste a node to new place in the same navigation
	@Test()
	public void test02_CutPasteNodeInSameNavigation()
	{
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		
		//Goto Setting > Portal > Group Sites
		goToGroupSites();
		
		//Click Edit navigation of Administration 
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Add child node for Group Navigation
		addNodeForGroup("Administrators", "Administration", false, NODE_NAME, true, languages, NODE_NAME, null, null, true, true);
		
		//Click Edit navigation of Administration
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Copy added child node
		if (waitForElementPresent(By.xpath("//a[@title='"+NODE_NAME+"']"),5000,0) == null )
		click(ADMINISTRATION_LINK);
		
		cutNode(By.xpath("//a[@title='"+NODE_NAME+"']"));
		
		//Paste to Node Sites Management 
		click(ELEMENT_APPLICATION_REGIS);
		pasteNode(ELEMENT_APPLICATION_REGIS);
		
		//Save
		save();
		pause(1000);
		
		//Click Edit navigation of Administration
		click(ADMIN_EDIT_NAVIGATION_LINK);
		waitForElementPresent(ELEMENT_APPLICATION_REGIS);
		click(ELEMENT_APPLICATION_REGIS);
		waitForElementPresent(CHILD_NODE);
		
		//Delete test data
		deleteNode("Administrators", "Application Registry", NODE_NAME, false);
	}
	
	//Cut/Paste a node to new place in different navigation
	@Test()
	public void test03_CutPasteNodeInDiffNavigation()
	{
		//Goto Setting > Portal > Group Sites
		goToGroupSites();
		
		//Click Edit navigation of Administration 
		waitForElementPresent(ADMIN_EDIT_NAVIGATION_LINK);
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Copy Node Portal Administration 
		cutNode(ADMINISTRATION_LINK);
		waitForElementPresent(CLOSE_NAVIGATION_ICON);
		click(CLOSE_NAVIGATION_ICON);
		
		//Click Edit navigation of Development
		click(EXECUTIVE_EDIT_NAVI_LINK);
		rightClickOnElement(ELEMENT_NEW_STAFF);
		waitForElementNotPresent(ELEMENT_PASTE_NODE);
		click(CLOSE_NAVIGATION_ICON);
	}
	
	@AfterMethod()
	public void afterTest()
	{
		signOut();
		driver.quit();
	}
}