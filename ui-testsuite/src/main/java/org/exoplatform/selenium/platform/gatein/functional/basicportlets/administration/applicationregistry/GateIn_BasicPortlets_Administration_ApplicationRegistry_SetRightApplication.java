package org.exoplatform.selenium.platform.gatein.functional.basicportlets.administration.applicationregistry;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.gatein.ManageAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.gatein.ManageApplications.*;
import static org.exoplatform.selenium.gatein.NavigationManagement.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PageManagement.*;
import static org.exoplatform.selenium.gatein.UserGroupManagement.*;
import static org.exoplatform.selenium.TestLogger.*;

/**
 * @author thaopth
 * date: 26/09/2012
 */
public class GateIn_BasicPortlets_Administration_ApplicationRegistry_SetRightApplication extends ManageAccount {
	
	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}
	
	@AfterMethod()
	public void afterTest()
	{
		driver.quit();
	}
	
	/*
	 * case No 39: Limit access right for application
	 * Create new user, add user into platform/administration group with membership is "member"
	 * Go to application registry, create new category with access permission is: Platform/Administration, member is is "member"
	 * Add application into new category with same access right as category
	 * Login as new user, add page with portlet in new category --> display "protected content"
	 * Login as user belongs to "Platform/Administration (manager), add page with portlet in new category --> Can user this portlet
	 */
	@Test
	public void test03_LimitAccessRightForApplication () {
		//Data
		String DATA_USER_NAME = "puca";
		String DATA_PASS = "123456";
		String DATA_FIRST_NAME = "puca";
		String DATA_LAST_NAME = "test";
		String DATA_EMAIL = ""+DATA_USER_NAME+"@exoplatform.com";
		String DATA_LANGUAGE = "English";
		String DATA_CATEGORY_NAME = "Categorytest";
		By DATA_CATEGORY_PATH = By.xpath("//a[@title='"+DATA_CATEGORY_NAME+"']");
		String DATA_APPLICATION_TYPE = "Portlet";
		String DATA_APP_DISPLAY_NAME = "Administration Toolbar Portlet";
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Administrators", "member");
		String DATA_GROUP = "Platform/Administrators";
		String DATA_MEMBER_SHIP = "manager";
		String DATA_NODENAME1 = "Case03_01";
		String DATA_NODENAME2 = "Case03_02"; 
		Map<String, String> DATA_PORTLET_ID = new HashMap<String, String>();
		DATA_PORTLET_ID.put("Categorytest/AdminToolbarPortlet", "");
		By ELEMENT_CONTAINER = By.className("PortletLayoutDecorator");

		/*----- Step 1: Add new user and add user to group-----*/	
		signIn("root", "gtn");
		goToNewStaff();
		addNewUserAccount(DATA_USER_NAME, DATA_PASS, DATA_PASS, DATA_FIRST_NAME, DATA_LAST_NAME, DATA_EMAIL, DATA_FIRST_NAME, DATA_LANGUAGE, true);

		//Go to user and group management page
		debug("Go to user and group management page");
		goToUsersAndGroupsManagement();

		//Select group tab
		debug("Select group tab");
		chooseGroupTab();

		//Select group
		debug("Select group");
		selectGroup("Platform");
		selectGroup("Administrators");

		//Add user to group
		debug("Add user to group");
		addUsersToGroup(DATA_USER_NAME, "member", false, true);

		/*----------Step 2: Add new application-------*/
		debug("Go to application registry");
		goToApplicationRegistry();

		debug("Add category");
		addNewCategoryAtManageApplications(DATA_CATEGORY_NAME, DATA_CATEGORY_NAME, DATA_CATEGORY_NAME, false, permissions, true);

		debug("Add application into category");
		addApplicationToCategory(DATA_CATEGORY_NAME, false, null, DATA_APPLICATION_TYPE, DATA_APP_DISPLAY_NAME, false, DATA_GROUP, DATA_MEMBER_SHIP);

		/*-------Step 3: Add new page by user who has access right on portlet-----*/ 
		debug("Go to Page editor");
		goToAddPageGroupEditor();

		addNewPageEditor(DATA_NODENAME1, DATA_NODENAME1, DATA_LANGUAGE, DATA_CATEGORY_NAME, DATA_PORTLET_ID, true);
		goToEditPageGroupEditor();

		//Verify portlet is displayed
		mouseOver(ELEMENT_CONTAINER, true);
		waitForElementPresent(By.xpath("//div[text()='Administration Toolbar Portlet']"));
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent("Page Editor");

		signOut();

		/* Step 4: Sign in as new user and add page with portlet which user does not have access right*/
		signIn(DATA_USER_NAME, DATA_PASS);

		debug("Go to page editor");
		goToAddPageEditor();

		debug("Add new page");
		type(ELEMENT_INPUT_NODE_NAME, DATA_NODENAME2, true);
		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
		waitForTextPresent("Empty Layout");
		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
		
		String category = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", DATA_CATEGORY_NAME);
		click(category);
		waitForElementNotPresent("//div[@id='" +DATA_CATEGORY_NAME + "/AdminToolbarPortlet']//img");
		
		click(ELEMENT_ABORTEDIT_BUTTON);
		click("//a[contains(text(),'Yes')]");
		signOut();

		/*-----------------Delete data------------*/
		signIn("root", "gtn");

		debug("Go to application registry");
		goToApplicationRegistry();
		waitForElementPresent(DATA_CATEGORY_PATH);
		click(DATA_CATEGORY_PATH);

		debug("Delete category");
		deleteCategoryAtManageApplications(DATA_CATEGORY_NAME, true);

		debug("Go to manage page");
		goToManagePages();

		debug("Delete page");
		deletePage(PageType.GROUP, DATA_NODENAME1);
		
		debug("Go to group site");
		goToGroupSites();

		debug("Delete node");
		deleteNode("Administrators", "Application Registry",DATA_NODENAME1 , false);

		debug("Go to user and group management");
		goToUsersAndGroupsManagement();

		debug("Delete user");
		deleteUser(DATA_USER_NAME);
	}
	
	/*Case 40: Limit access right for application not in public category
	 * 
	 */
	@Test
	public void test04_LimitAccessRightForApplicationInNotPublicCategory () {
		String DATA_USER_NAME = "sandra";
		String DATA_PASS = "123456";
		String DATA_FIRST_NAME = "sandra";
		String DATA_LAST_NAME = "bullock";
		String DATA_EMAIL = ""+DATA_USER_NAME+"@exoplatform.com";
		String DATA_LANGUAGE = "English";
		String DATA_CATEGORY_NAME = "Category04";
		By DATA_CATEGORY_PATH = By.xpath("//a[@title='"+DATA_CATEGORY_NAME+"']");
		String DATA_APPLICATION_TYPE = "Portlet";
		String DATA_APP_DISPLAY_NAME = "Group Navigation Portlet";
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Administrators", "manager");
		String DATA_GROUP = "Platform/Administrators";
		String DATA_MEMBER_SHIP = "manager";
		String DATA_NODENAME = "Case04";
		By ELEMENT_PAGE_DISPLAY_NAME = By.id("i18nizedLabel");
		By ELEMENT_YES_BUTTON = By.xpath("//a[contains(text(),'Yes')]");


		/*----- Step 1: Add new user and add user to group-----*/	
		signIn("root", "gtn");
		goToNewStaff();
		addNewUserAccount(DATA_USER_NAME, DATA_PASS, DATA_PASS, DATA_FIRST_NAME, DATA_LAST_NAME, DATA_EMAIL, DATA_FIRST_NAME, DATA_LANGUAGE, true);

		//Go to user and group management page
		debug("Go to user and group management page");
		goToUsersAndGroupsManagement();

		//Select group tab
		debug("Select group tab");
		chooseGroupTab();

		//Select group
		debug("Select group");
		selectGroup("Platform");
		selectGroup("Administrators");

		//Add user to group
		debug("Add user to group");
		addUsersToGroup(DATA_USER_NAME, "member", false, true);

		/*----------Step 2: Add new application-------*/
		debug("Go to application registry");
		goToApplicationRegistry();

		debug("Add category");
		addNewCategoryAtManageApplications(DATA_CATEGORY_NAME, DATA_CATEGORY_NAME, DATA_CATEGORY_NAME, false, permissions, true);

		debug("Add application into category");
		addApplicationToCategory(DATA_CATEGORY_NAME, false, null, DATA_APPLICATION_TYPE, DATA_APP_DISPLAY_NAME, false, DATA_GROUP, DATA_MEMBER_SHIP);

		signOut();

		/*----Step 3: Add new page when user does not have access right on category----*/
		signIn(DATA_USER_NAME, DATA_PASS);

		debug("Go to page editor");
		goToAddPageEditor();

		debug("Add new page");
		type(ELEMENT_INPUT_NODE_NAME, DATA_NODENAME, true);
		type(ELEMENT_PAGE_DISPLAY_NAME, DATA_NODENAME, true);

		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
		waitForTextPresent("Empty Layout");
		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
		
		debug("Verify new user has not access right on category created above");
		waitForElementNotPresent(DATA_CATEGORY_PATH);
		click(ELEMENT_ABORTEDIT_BUTTON);
		waitForMessage("Modifications have been made. Are you sure you want to close without saving ?");
		click(ELEMENT_YES_BUTTON);
		waitForTextPresent(DATA_FIRST_NAME);
		signOut();
		
		//Delete data
		signIn("root", "gtn");
		goToUsersAndGroupsManagement();
		deleteUser(DATA_USER_NAME);
		goToApplicationRegistry();
		click(DATA_CATEGORY_PATH);
		deleteCategoryAtManageApplications(DATA_CATEGORY_NAME, true);
	}
}