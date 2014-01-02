package org.exoplatform.selenium.cloud.signup;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author vuna
 *
 */
public class Cloud_Create_Users extends PlatformBase{

	ManageAccount magAccount;
	NavigationToolbar navBar;
	UserGroupManagement userGroup;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		magAccount = new ManageAccount(driver);
		navBar = new NavigationToolbar(driver);
		userGroup = new UserGroupManagement(driver);
		info("== Creating users to eXo Cloud ==");
	}

	@AfterMethod
	public void afterMethods() {
		info("== Creating users: successful ==");
		driver.quit();
	}

	@Test
	public void test01_CreateUser(){
		info("== Signin to eXo Cloud with user... fqa-exo");
		magAccount.signIn("root", "12345");
		navBar.goToNewStaff();                
		//Publisher
		magAccount.addNewUserAccount("mary", DATA_PASS, DATA_PASS, "Mary", "Williams", "", "mary@acme.com", "", "", true);
		//Admin
		magAccount.addNewUserAccount("john", DATA_PASS, DATA_PASS, "John", "Smith", "", "john@acme.com", "", "", true);
		//Redactor
		magAccount.addNewUserAccount("james", DATA_PASS, DATA_PASS, "James", "Davis", "", "james@acme.com", "", "", true);
		//Developer
		magAccount.addNewUserAccount("demo", DATA_PASS, DATA_PASS, "Jack", "Miller", "", "jack@acme.com", "", "", true);

		//Group Management
		navBar.goToUsersAndGroupsManagement();
		userGroup.chooseGroupTab();
		//Admin
		userGroup.selectGroup("Platform/Administration", true);
		userGroup.addUsersToGroup("john", "*", false, false);
		userGroup.addUsersToGroup("john", "manager", false, false);
		click(ELEMENT_UP_LEVEL);
	
		//platform/users
		userGroup.selectGroup("Platform/Users", true);
		userGroup.addUsersToGroup("john", "*", false, false);
		click(ELEMENT_UP_LEVEL);

		//Publisher
		userGroup.selectGroup("Platform/Content Management", true);
		userGroup.addUsersToGroup("john", "*", false, true);
		userGroup.addUsersToGroup("mary", "manager", false, true);
		userGroup.addUsersToGroup("mary", "editor", false, true);
		userGroup.addUsersToGroup("james", "author", false, false);
		userGroup.addUsersToGroup("james", "redactor", false, false);

		click(ELEMENT_UP_LEVEL);
		//Organization/Employees
		userGroup.selectGroup("Organization/Employees", true);
		userGroup.addUsersToGroup("john", "*", false, false);
		userGroup.addUsersToGroup("mary", "member", true, true);
		userGroup.addUsersToGroup("james", "member", true, true);
		userGroup.addUsersToGroup("demo", "member", true, true);

		click(ELEMENT_UP_LEVEL);
		//Organization/Management/Executive Board
		userGroup.selectGroup("Organization/Management/Executive Board", true);
		userGroup.addUsersToGroup("john", "*", true, true);

		click(ELEMENT_UP_LEVEL);
		waitForAndGetElement(userGroup.ELEMENT_GROUP_PERMISSION.replace("${groupName}", "Management"));
		click(ELEMENT_UP_LEVEL);
		waitForAndGetElement(userGroup.ELEMENT_GROUP_PERMISSION.replace("${groupName}", "Development"));

		//Developer
		userGroup.selectGroup("Development", true);
		userGroup.addUsersToGroup("demo", "member", true, true);
		userGroup.addUsersToGroup("john", "member", true, true);
	}
}