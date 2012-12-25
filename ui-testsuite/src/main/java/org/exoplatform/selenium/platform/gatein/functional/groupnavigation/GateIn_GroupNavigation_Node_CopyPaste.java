package org.exoplatform.selenium.platform.gatein.functional.groupnavigation;

import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.NavigationManagement.*;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author NhungVT
 * @date: 20/09/2012	
 */
public class GateIn_GroupNavigation_Node_CopyPaste extends GateInBase
{
	//Define data
	public By ADMIN_EDIT_NAVI_LINK = By.xpath("//td/div[text()='Administrators']/ancestor::tr/td/a[text()='Edit Navigation']");
	public By EXECUTIVE_EDIT_NAVI_LINK = By.xpath("//td/div[text()='Executive Board']/ancestor::tr/td/a[text()='Edit Navigation']");
	public By UP_LEVEL = By.xpath("//a[contains(@title,'Up Level')]");
	public By ELEMENT_PAGE_MANAGEMENT = By.xpath("//a[@title='Page Management']");
	public By ELEMENT_APPLICATION_REGIS = By.xpath("//a[@title='Application Registry']");
	public By PORTAL_ADMINISTRATION = By.xpath("//a[@title='Portal Administration']");
	public String CHILD_NODE = "//div[@class='ChildrenContainer']//a[@class = 'NodeIcon DefaultPageIcon' and @title='Page Management']";
	public By CLOSE_NAVIGATION = By.xpath("//a[contains(@title,'Close Window')]");
	public By ELEMENT_NEW_STAFF = By.linkText("New Staff");
	
	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("root", "gtn");
	}
	
	//Copy/Paste a node into another node in the same navigation
	@Test()
	public void test01_CopyPasteNodeInSameNavigation()
	{
		//Goto Group Sites
		goToGroupSites();
		
		//Select Edit Navigation of Content Management
		click(ADMIN_EDIT_NAVI_LINK);
		
		//Copy Node on Content Explorer
		copyNode(ELEMENT_PAGE_MANAGEMENT);
		
		//Paste Node on Content Administration
		pasteNode(ELEMENT_APPLICATION_REGIS);
		
		//Save
		save();
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
		//Select Edit Navigation of Content Management
		click(ADMIN_EDIT_NAVI_LINK);
		click(ELEMENT_APPLICATION_REGIS);
		
		//Verify Copy/Paste result and Reset data
		waitForElementPresent(CHILD_NODE);
		rightClickOnElement(CHILD_NODE);
		click(ELEMENT_NODE_DELETE);
		waitForConfirmation(MSG_DELETE_NODE);
		waitForElementNotPresent(CHILD_NODE);
		save();
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
	}
	
	//Copy/Paste a node into another node in different navigation
	@Test()
	public void test02_CopyPasteNodeInDiffirentNavigation()
	{
		//Goto Group Sites
		goToGroupSites();
		
		//Select Edit Navigation of Administration
		waitForElementPresent(ADMIN_EDIT_NAVI_LINK);
		click(ADMIN_EDIT_NAVI_LINK);
		
		//Right click and select Copy Node on Administration
		copyNode(ELEMENT_PAGE_MANAGEMENT);
		
		//Close Content Management Navigation
		waitForElementPresent(CLOSE_NAVIGATION);
		click(CLOSE_NAVIGATION);
		
		//Select Edit Navigation of Administration
		click(EXECUTIVE_EDIT_NAVI_LINK);
		
		//Right click and confirm Paste Node not exist on Node Portal Administration
		rightClickOnElement(ELEMENT_NEW_STAFF);
		waitForElementNotPresent(ELEMENT_PASTE_NODE);
		
		//Close Content Management Navigation
		waitForElementPresent(CLOSE_NAVIGATION);
		click(CLOSE_NAVIGATION);
	}
	
	//Copy/Paste a node into the same place
	@Test()
	public void test03_CopyPasteNodesInSamePlace()
	{
		//Goto Group Sites
		goToGroupSites();
		
		//Select Edit Navigation of Administrators
		waitForElementPresent(ADMIN_EDIT_NAVI_LINK);
		click(ADMIN_EDIT_NAVI_LINK);
		
		//Copy Node on Page management
		copyNode(ELEMENT_PAGE_MANAGEMENT);
		
		//Paste Node on Application registry
		pasteNode(ELEMENT_APPLICATION_REGIS);
		
		//Save
		save();
		waitForElementNotPresent(ELEMENT_PAGE_MANAGEMENT);
		
		//Select Edit Navigation of Administrators
		waitForElementPresent(ADMIN_EDIT_NAVI_LINK);
		click(ADMIN_EDIT_NAVI_LINK);
		
		click(ELEMENT_APPLICATION_REGIS);
		copyNode(By.xpath(CHILD_NODE));
		pasteNode(ELEMENT_APPLICATION_REGIS);
		
		//Verify display message to notice that Node already existed
		waitForTextPresent(MSG_ADD_SAME_NODE);
		click(ELEMENT_OK_BUTTON);
		
		rightClickOnElement(CHILD_NODE);
		click(ELEMENT_NODE_DELETE);
		waitForConfirmation(MSG_DELETE_NODE);
		waitForElementNotPresent(CHILD_NODE);
		save();
		waitForElementNotPresent(ELEMENT_APPLICATION_REGIS);
	}
	
	@AfterMethod
	public void afterTest(){
		signOut();
		driver.quit();
	}
}