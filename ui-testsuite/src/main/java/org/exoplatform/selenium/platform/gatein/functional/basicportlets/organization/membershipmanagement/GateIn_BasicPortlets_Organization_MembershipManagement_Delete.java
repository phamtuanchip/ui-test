package org.exoplatform.selenium.platform.gatein.functional.basicportlets.organization.membershipmanagement;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.UserGroupManagement.*;

public class GateIn_BasicPortlets_Organization_MembershipManagement_Delete extends GateInBase {
	String GROUP_NAME1 = "Platform"; 
	String USER_NAME = "demo";
	String MEMBERSHIP = "member";
	boolean SELECT = true; 
	public String CAN_NOT_DELETE_MEMBERSHIP_MANDATORY ="You can not delete this membership because it is mandatory";
	@BeforeMethod()
	public void beforeTest(){
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
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
	@AfterMethod()
	public void afterTest()
	{
		signOut();
		driver.quit();
	}
}
