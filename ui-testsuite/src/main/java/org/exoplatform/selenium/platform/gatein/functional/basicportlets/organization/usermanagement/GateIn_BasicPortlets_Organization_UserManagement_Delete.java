package org.exoplatform.selenium.platform.gatein.functional.basicportlets.organization.usermanagement;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.gatein.ManageAccount.addNewUserAccount;
import static org.exoplatform.selenium.gatein.ManageAccount.signIn;
import static org.exoplatform.selenium.gatein.ManageAccount.signOut;
import static org.exoplatform.selenium.gatein.NavigationToolbar.goToNewStaff;
import static org.exoplatform.selenium.gatein.NavigationToolbar.goToUsersAndGroupsManagement;
import static org.exoplatform.selenium.gatein.UserGroupManagement.addUsersToGroup;
import static org.exoplatform.selenium.gatein.UserGroupManagement.chooseGroupTab;
import static org.exoplatform.selenium.gatein.UserGroupManagement.chooseUserTab;
import static org.exoplatform.selenium.gatein.UserGroupManagement.deleteUser;
import static org.exoplatform.selenium.gatein.UserGroupManagement.searchUser;
import static org.exoplatform.selenium.gatein.UserGroupManagement.selectGroup;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GateIn_BasicPortlets_Organization_UserManagement_Delete extends GateInBase {
	boolean SELECT = true; 
	public String SIGN_IN_FAILED ="Sign in failed. Wrong username or password.";

	@BeforeMethod()
	public void beforeTest(){
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
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
