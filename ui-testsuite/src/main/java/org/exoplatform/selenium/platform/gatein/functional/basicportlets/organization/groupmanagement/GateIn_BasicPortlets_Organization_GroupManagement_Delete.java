package org.exoplatform.selenium.platform.gatein.functional.basicportlets.organization.groupmanagement;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.UserGroupManagement.*;

/**
 *@author HangNTT
 *@date: 20/09/2012
 */
public class GateIn_BasicPortlets_Organization_GroupManagement_Delete extends GateInBase {

	String GROUP_NAME = "FNC_GTN_PLT_ORG_03_04_004"; 
	String GROUP_LABEL= "FNC_GTN_PLT_ORG_03_04_004"; 
	String GROUP_DESC = "FNC_GTN_PLT_ORG_03_04_004"; 

	String GROUP_NAME1 = "Platform"; 
	String USER_NAME = "demo";
	String MEMBERSHIP = "member"; 
	boolean SELECT = true; 
	By upLevel = By.xpath("//a[@title='Up Level']");

	public String CAN_NOT_DELETE_GROUP_MANDATORY = "You can't delete this group because it (or its child) is mandatory.";
	
	
	@BeforeMethod()
	public void beforeTest(){
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}
	
	@Test
	public void test04_checkExistingOfUserInDeteledGroup () {
		info("--login portal--");
		signIn("root", "gtn");
		
		info("--Go to User and group--");
		//Go to user and group management page
		goToUsersAndGroupsManagement();
		
		//Choose group tab
		info("--Choose group tab--");
		chooseGroupTab();
		
		//Add new group
		info("--Add new group --");
		addGroup(GROUP_NAME, GROUP_LABEL, GROUP_DESC, true);
		waitForTextPresent(GROUP_NAME);
		
		//Add new user into group
		info("--Add user into group--");
		addUsersToGroup(USER_NAME, MEMBERSHIP, SELECT, true);
		waitForTextPresent(USER_NAME);
		waitForTextPresent(MEMBERSHIP);
		
		// Go to User Management Tab
		info("--Check membership of user in a group--");
		chooseUserTab();
		
		//Search new user
		info("--Search user--");
		searchUser(USER_NAME, "User Name");
		
		//Edit new user
		info("--Click edit user--");
		editUser(USER_NAME);
		click(ELEMENT_USER_MEMBERSHIP_TAB);
		click(ELEMENT_CANCEL_BUTTON);
		
		//Back to Group Management
		info("--back to Group management--");
		chooseGroupTab();
		
		//Delete group
		info("--Delete group--");
		selectGroup(GROUP_NAME);
		deleteGroup(GROUP_NAME, true, 120000);
		
		//Back to User management tab
		info("--back to User management--");
		chooseUserTab();
		
		//Check after delete group
		info("--Search user--");
		searchUser(USER_NAME, "User Name");
		info("--Click edit user--");
		editUser(USER_NAME);
		click(ELEMENT_USER_MEMBERSHIP_TAB);
		info("--verify groupname--");
		waitForElementNotPresent(GROUP_NAME);
		click(ELEMENT_CANCEL_BUTTON);
	}
	
	@Test
	public void test10_deleteMandatoryGroup () {
		info("--login portal--");
		signIn("root", "gtn");
		
		info("--Go to User and group--");
		goToUsersAndGroupsManagement();
		//goToGroupsManagement();
		
		info("--Choose group tab--");
		chooseGroupTab();
		selectGroup(GROUP_NAME1);
		
		//Delete group is mandatory
		deleteGroup(GROUP_NAME1, false);
		waitForMessage(CAN_NOT_DELETE_GROUP_MANDATORY);
		closeMessageDialog();
	}
	
	@AfterMethod()
	public void afterTest()
	{
		signOut();
		driver.quit();
	}
}