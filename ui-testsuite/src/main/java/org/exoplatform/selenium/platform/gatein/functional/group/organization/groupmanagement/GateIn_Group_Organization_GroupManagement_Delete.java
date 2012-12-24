package org.exoplatform.selenium.platform.gatein.functional.group.organization.groupmanagement;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.Alert;
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
public class GateIn_Group_Organization_GroupManagement_Delete extends GateInBase {

	String GROUP_NAME = "FNC_GTN_PLT_ORG_03_04_004"; 
	String GROUP_LABEL= "FNC_GTN_PLT_ORG_03_04_004"; 
	String GROUP_DESC = "FNC_GTN_PLT_ORG_03_04_004"; 

	String GROUP_NAME1 = "Platform"; 
	String USER_NAME = "demo";
	String MEMBERSHIP = "member"; 
	boolean SELECT = true; 
	By upLevel = By.xpath("//a[@title='Up Level']");

	public String CAN_NOT_DELETE_GROUP_MANDATORY = "You can't delete this group because it (or its child) is mandatory.";
	public String CAN_NOT_DELETE_MEMBERSHIP_MANDATORY ="You can not delete this membership because it is mandatory";
	public String SIGN_IN_FAILED ="Sign in failed. Wrong username or password.";
	
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

	@Test
	public void test05_checkExistingOfUserInGroupAfterHisRoleInThatGroupWasDeleted(){
		info("--login portal--");
		signIn("root", "gtn");
		
		info("--Go to User and group--");
		goToUsersAndGroupsManagement();
		
		info("Go to Memebrship tab");
		chooseMembershipTab();
		
		info("Add new membership");
		addMembership("Test_POR_04_04_005", "test membership type", true);
		
		info("Go to Group management");
		chooseGroupTab();
		
		info("Add user into group");
		selectGroup(GROUP_NAME1);
		addUsersToGroup("demo", "Test_POR_04_04_005", SELECT, true);
		
		info("Go to Memebrship tab");
		chooseMembershipTab();
		
		info("Delete membership");
		deleteMembership("Test_POR_04_04_005", true);
		
		info("Back to Group Management");
		chooseGroupTab();
		selectGroup(GROUP_NAME1);
		waitForElementNotPresent(USER_NAME);
		waitForElementNotPresent("Test_POR_04_04_005");
	}
	
	@Test
	public void test06_checkMembershipInformationOfUserAfterRoleOfHimInAGroupWasDeleted(){
		info("--login portal--");
		signIn("root", "gtn");
		
		info("--Go to User and group--");
		goToUsersAndGroupsManagement();
		
		info("Go to Memebrship tab");
		chooseMembershipTab();
		
		info("Add new membership");
		addMembership("test", "test membership type", true);
		
		info("Go to Group management");
		chooseGroupTab();
		
		info("Add user into group");
		selectGroup(GROUP_NAME1);
		addUsersToGroup("demo", "test", SELECT, true);
		
		info("Go to User Management");
		chooseUserTab();
		
		info("--Search user--");
		searchUser("demo", "User Name");
		
		info("--Click edit user--");
		editUser(USER_NAME);
		click(ELEMENT_USER_MEMBERSHIP_TAB);
		isElementPresent(USER_NAME);
		isElementPresent("test");
		click(ELEMENT_CANCEL_BUTTON);
		
		info("Go to Memebrship tab");
		chooseMembershipTab();
		
		info("Delete membership");
		deleteMembership("test", true);
		
		info("Check membership information of user");
		chooseUserTab();
		
		info("--Search user--");
		searchUser("demo", "User Name");
		
		info("--Click edit user--");
		editUser(USER_NAME);
		click(ELEMENT_USER_MEMBERSHIP_TAB);
		waitForElementNotPresent(USER_NAME);
		waitForElementNotPresent("test");
		click(ELEMENT_CANCEL_BUTTON);
	}
	
	@Test
	public void test07_deleteMandatoryMembership(){
		info("--login portal--");
		signIn("root", "gtn");
		
		info("--Go to User and group--");
		goToUsersAndGroupsManagement();
		
		info("Go to Memebrship tab");
		chooseMembershipTab();
		String deleteIcon = ELEMENT_MEMBERSHIP_DELETE_ICON.replace("${membership}", "member");
		click(deleteIcon);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		waitForTextPresent(CAN_NOT_DELETE_MEMBERSHIP_MANDATORY);
		closeMessageDialog();
	}
	
	@Test
	public void test01_detelteSpecificUserFromExistingUsersListInCommunityManagement(){

		String USER_NAME = "exoseaadministrator";
		String PASSWORD	= "test_por_02_04_001";
		String CONFIRM_PASSWORD = "test_por_02_04_001";
		String FIRST_NAME = "first name";
		String LAST_NAME = "last name";
		String EMAIL = "exoseatestaccount@exo.com";
		String USER_NAME_GIVEN = "";
		String LANGUAGE = "English";

		String searchOption = "User Name";
		By ELEMENT_USER_NAME = By.name("username");
		By ELEMENT_PWD = By.name("password");
		By ELEMENT_SIGNIN = By.name("signIn");

		info("--login portal--");
		signIn("root", "gtn");
		
		// Go to New Staff
		info("Go to New Staff");
		goToNewStaff();
		
		// Add new user
		info("Input value");
		addNewUserAccount(USER_NAME,PASSWORD,CONFIRM_PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,USER_NAME_GIVEN,LANGUAGE,true);
		
		//Go to user and group management page
		info("Go to users and group management");
		goToUsersAndGroupsManagement();
		
		//Choose Group Management
		info("Choose Group Management");
		chooseGroupTab();
		
		//Add new user into group
		info("Add new user into group");
		selectGroup("Platform/Administrators");
		info("Add user into group");
		addUsersToGroup(USER_NAME, "member", SELECT, true);
		isElementPresent(USER_NAME);
		isElementPresent("member");
		
		//Choose User Management tab
		info("Choose User Management");
		chooseUserTab();
		
		//Search new user
		info("Search user");
		searchUser(USER_NAME, searchOption);
		
		//Delete new user
		info("Delete user");
		deleteUser(USER_NAME);
		//closeMessageDialog();
		
		info("Check group after delete user");
		info("Choose Group Management");
		chooseGroupTab();
		selectGroup("Platform/Administrators");
		waitForElementNotPresent(USER_NAME);
		waitForElementNotPresent("member");
		signOut();
		driver.get(baseUrl);
		
		info("Sign in by deleted account");
		//click(ELEMENT_GO_TO_PORTAL);
		signIn(USER_NAME, "test_por_02_04_001");
		waitForTextPresent(SIGN_IN_FAILED);
		type(ELEMENT_USER_NAME, "root", true);
		waitForElementPresent(ELEMENT_PWD);
		type(ELEMENT_PWD, "gtn", true);
		click(ELEMENT_SIGNIN);		
	}
	
	@AfterMethod()
	public void afterTest()
	{
		signOut();
		driver.quit();
	}
}